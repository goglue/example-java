package org.sample.news.server;

import static org.junit.Assert.assertEquals;

import io.grpc.stub.StreamObserver;
import org.junit.Test;
import org.sample.news.application.command.CommandFactory;
import org.sample.news.application.command.CreateArticleCommand;
import org.sample.news.domain.exception.AuthorNotFound;
import org.sample.news.domain.model.Article;
import org.sample.news.domain.service.ArticleService;
import org.sample.news.protocol.ArticleProtoService;

import static org.mockito.Mockito.*;

import java.io.IOException;

/**
 * Unit test for the application server functionality
 */
public class ArticleApplicationServerTest {
    @Test
    public void testStartStop() {
        ArticleApplicationServer server = new ArticleApplicationServer(
                null,
                new CommandFactory(),
                new ResponseFactory(),
                8090
        );

        try {
            server.start();
        } catch (IOException e) {
            assertEquals(null, e);
        } finally {
            server.stop();
        }

        server.gracefullyStop();
    }
}
