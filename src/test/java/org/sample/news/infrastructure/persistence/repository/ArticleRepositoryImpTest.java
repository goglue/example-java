package org.sample.news.infrastructure.persistence.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.sample.news.domain.exception.ArticleNotFound;
import org.sample.news.domain.model.Article;
import org.sample.news.domain.model.Author;
import org.sample.news.domain.model.article.Content;
import org.sample.news.domain.model.article.Id;
import org.sample.news.domain.model.author.Credentials;
import org.sample.news.domain.repository.ArticleRepository;
import org.sample.news.infrastructure.persistence.adapter.AdapterDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * ArticleRepositoryImpTest class to test the repository functionality
 */
public class ArticleRepositoryImpTest {

    /**
     * Return a testing instance of an article model
     *
     * @param id     The unique identifier object of the article
     * @param header The header string of the content
     * @return A testing instance of an article model
     */
    public static Article getTestArticle(Id id, String header) {
        List<Author> authors = new ArrayList<Author>();
        authors.add(
                new Author(
                        1,
                        "first-test",
                        "last-test",
                        new Credentials(
                                "user-test",
                                "secret-test"
                        )
                )
        );
        return new Article(
                id,
                new Content(header, "test-description", "test-body"),
                new Date(),
                authors,
                new ArrayList<String>(),
                false
        );
    }

    @Test
    public void testPersistCreate() {
        AdapterDAO mocked = mock(AdapterDAO.class);
        Article article = this.getTestArticle(new Id(0), "header");
        try {
            when(mocked.createArticle(article)).thenReturn(article);
        } catch (Exception e) {
            assertEquals(null, e);
        }

        ArticleRepository articleRepository = new ArticleRepositoryImp(mocked);
        try {
            articleRepository.persist(article);
        } catch (Exception e) {
            assertEquals(null, e);
        } catch (ArticleNotFound notFound) {
            assertEquals(null, notFound);
        }
    }

    @Test
    public void testPersistUpdate() {
        AdapterDAO mocked = mock(AdapterDAO.class);
        Article article = this.getTestArticle(new Id(2), "header-1");
        try {
            when(mocked.updateArticle(article)).thenReturn(article);
        } catch (Exception e) {
            assertEquals(null, e);
        } catch (ArticleNotFound notFound) {
            assertEquals(null, notFound);
        }

        ArticleRepository articleRepository = new ArticleRepositoryImp(mocked);
        try {
            articleRepository.persist(article);
        } catch (Exception e) {
            assertEquals(null, e);
        } catch (ArticleNotFound notFound) {
            assertEquals(null, notFound);
        }
    }

    @Test
    public void testGetArticleById() {
        AdapterDAO mocked = mock(AdapterDAO.class);
        Article article = this.getTestArticle(new Id(2), "edited");
        try {
            when(mocked.getArticleById(article.getId())).thenReturn(article);
        } catch (Exception e) {
            assertEquals(null, e);
        } catch (ArticleNotFound notFound) {
            assertEquals(null, notFound);
        }

        ArticleRepository articleRepository = new ArticleRepositoryImp(mocked);
        try {
            articleRepository.getArticleById(article.getId());
        } catch (Exception e) {
            assertEquals(null, e);
        } catch (ArticleNotFound notFound) {
            assertEquals(null, notFound);
        }
    }
}
