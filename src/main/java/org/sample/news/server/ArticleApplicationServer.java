package org.sample.news.server;

import com.google.inject.Inject;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.sample.news.application.command.CommandFactory;
import org.sample.news.domain.exception.AuthorNotFound;
import org.sample.news.domain.exception.ValidationError;
import org.sample.news.domain.model.Article;
import org.sample.news.domain.service.ArticleService;
import org.sample.news.protocol.ArticleProtoService;
import org.sample.news.protocol.ArticleProtoType;
import org.sample.news.protocol.ArticlesGrpc;

import java.io.IOException;

/**
 * @author Moath Almallahi
 */
public class ArticleApplicationServer extends ArticlesGrpc.ArticlesImplBase implements ApplicationServer {
    /**
     * Article service injected in the server, this multi layering to abstract the types of the server from the types of
     * the service
     */
    private ArticleService articleService;

    /**
     * Service command factory to generate service commands in an isolated manner
     */
    private CommandFactory commandFactory;

    /**
     * Server response factory to generate server responses in an isolated manner
     */
    private ResponseFactory responseFactory;

    /**
     * The gRPC server instance
     */
    private Server server;

    /**
     * ArticleApplicationService constructor loads the article service and the command factory
     *
     * @param articleService  The article domain application service implementation
     * @param commandFactory  The service command factory
     * @param responseFactory The server response factory
     */
    @Inject
    public ArticleApplicationServer(
            ArticleService articleService,
            CommandFactory commandFactory,
            ResponseFactory responseFactory,
            int port
    ) {
        this.articleService = articleService;
        this.commandFactory = commandFactory;
        this.responseFactory = responseFactory;

        this.server = ServerBuilder.forPort(port).addService(this).build();
    }

    /**
     * The server edge for creating an article
     *
     * @param request          The protobuf request object
     * @param responseObserver Response observer since gRPC relies on netty
     */
    @Override
    public void createArticle(
            ArticleProtoService.CreateArticleCommand request,
            StreamObserver<ArticleProtoType.Article> responseObserver
    ) {
        if (!request.hasContent()) {
            responseObserver.onError(
                    Status.
                            fromCode(Status.Code.INVALID_ARGUMENT).
                            withDescription("article.content-empty").
                            asException()
            );
            responseObserver.onCompleted();
        }

        try {
            Article article = this.articleService.createArticle(
                    this.commandFactory.createArticleCommand(
                            request.getContent().getContentHeader(),
                            request.getContent().getContentDescription(),
                            request.getContent().getContentBody(),
                            request.getAuthorId(),
                            request.getKeywordList()
                    )
            );
            responseObserver.onNext(
                    this.responseFactory.createArticleResponse(
                            article.getId().getId(),
                            this.responseFactory.createContentResponse(
                                    article.getContent().getHeader(),
                                    article.getContent().getDescription(),
                                    article.getContent().getBody()
                            ),
                            (int) article.getPublishDate().getTime() / 1000,
                            article.getKeywords()
                    )
            );
        } catch (AuthorNotFound authorNotFound) {
            responseObserver.onError(
                    Status.
                            fromCode(Status.Code.NOT_FOUND).
                            withDescription(authorNotFound.getMessage()).
                            asException()
            );
        } catch (ValidationError validationError) {
            responseObserver.onError(
                    Status.
                            fromCode(Status.Code.INVALID_ARGUMENT).
                            withDescription(validationError.getMessage()).
                            asException()
            );
        } catch (Exception e) {
            responseObserver.onError(
                    Status.
                            fromCode(Status.Code.INTERNAL).
                            withDescription(e.getMessage()).
                            asException()
            );
        } finally {
            responseObserver.onCompleted();
        }
    }

    public void start() throws IOException {
        this.server.start();
    }

    public void waitForSignal() throws InterruptedException {
        this.server.awaitTermination();
    }

    public void stop() {
        this.server.shutdown();
    }

    public void gracefullyStop() {
        if (!this.server.isShutdown()) {
            this.server.shutdownNow();
        }
    }
}
