package org.sample.news.application.service;

import com.google.inject.Inject;
import org.sample.news.application.command.CreateArticleCommand;
import org.sample.news.application.query.GetArticleById;
import org.sample.news.domain.exception.ArticleNotFound;
import org.sample.news.domain.exception.AuthorNotFound;
import org.sample.news.domain.model.Article;
import org.sample.news.domain.model.Author;
import org.sample.news.domain.model.Factory;
import org.sample.news.domain.model.author.Credentials;
import org.sample.news.domain.repository.ArticleRepository;
import org.sample.news.domain.service.ArticleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Article service implementation, encapsulate the handlers and acting as a
 * bridge between the application-user (e.g. web-server, cli worker) and the
 * handlers of the domain. It also handles the service level functionality (e.g.
 * calling another service) which is not related for the handler of the domain.
 *
 * @author Moath Almallahi
 */
public class ArticleApplicationService implements ArticleService {
    /**
     * ArticleRepository injected to be used in an isolated manner also abstracts the persistence layer
     */
    private ArticleRepository repository;

    /**
     * The model factory is injected to isolate the strategy of building a model also to extract the functionality as it
     * could be used in many different places
     */
    private Factory modelFactory;

    /**
     * Mock for the authors domain as it should be standalone domain completely split from the articles
     */
    private List<Author> authors;

    /**
     * Mock for the authors domain as it should be standalone domain completely split from the articles
     */
    private Observable articleCreatedEvent;

    /**
     * ArticleApplicationService constructor that loads the instance with the injected repo and factory
     *
     * @param repository          The article repository
     * @param modelFactory        The article model factory
     * @param articleCreatedEvent The notifier for the article creation listeners
     */
    @Inject
    public ArticleApplicationService(ArticleRepository repository, Factory modelFactory, Observable articleCreatedEvent) {
        this.repository = repository;
        this.modelFactory = modelFactory;
        this.articleCreatedEvent = articleCreatedEvent;

        this.authors = new ArrayList<Author>();
        this.authors.add(
                new Author(
                        1,
                        "author-1",
                        "last-1",
                        new Credentials("user-1", "pass-1")
                )
        );
        this.authors.add(
                new Author(
                        2,
                        "author-2",
                        "last-2",
                        new Credentials("user-2", "pass-2")
                )
        );
        this.authors.add(
                new Author(
                        3,
                        "author-3",
                        "last-3",
                        new Credentials("user-3", "pass-3")
                )
        );
    }

    /**
     * The service edge where it is responsible to create an article
     *
     * @param createArticle The application command responsible for creating a new article
     * @return The newly created article with the new identity
     * @throws Exception When the validation failed or persisting to the DB
     */
    public Article createArticle(CreateArticleCommand createArticle) throws Exception, AuthorNotFound {
        Author author = this.getAuthorById(createArticle.getAuthorId());
        if (null == author) {
            throw new AuthorNotFound();
        }

        List<Author> authors = new ArrayList<Author>();
        authors.add(author);
        Article article = this.modelFactory.createNewArticle(
                this.modelFactory.createContent(
                        createArticle.getHeader(),
                        createArticle.getDescription(),
                        createArticle.getBody()
                ),
                authors,
                createArticle.getKeywords()
        );
        try {
            article = this.repository.persist(article);
        } catch (ArticleNotFound notFound) {
            // not a case in create
        }
        // todo: change the passed value to be a value object (proto type for example)
        this.articleCreatedEvent.notifyObservers(article);

        return article;
    }

    /**
     * Return the author by the given ID
     *
     * @param authorId The author id
     * @return The found author instance, null in case nothing found
     */
    private Author getAuthorById(int authorId) {
        for (Author author :
                this.authors) {
            if (author.getId() == authorId) {
                return author;
            }
        }

        return null;
    }

    /**
     * Return an Article by the given ID
     *
     * @return Article instance
     */
    public Article getArticleById(GetArticleById articleById) throws ArticleNotFound {
        try {
            return this.repository.getArticleById(this.modelFactory.createArticleId(articleById.getArticleId()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
