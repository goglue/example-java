package org.sample.news.infrastructure.persistence.adapter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.sample.news.domain.exception.ArticleNotFound;
import org.sample.news.domain.model.Article;
import org.sample.news.domain.model.Author;
import org.sample.news.domain.model.Factory;
import org.sample.news.domain.model.article.Content;
import org.sample.news.domain.model.article.Id;
import org.sample.news.domain.model.author.Credentials;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * MemoryTest tests the memory DAO layer instance
 */
public class MemoryTest {
    /**
     * Factory method for the memory class
     *
     * @return an instance of the memory DAO
     */
    private Memory getMemory() {
        return new Memory(new Factory());
    }

    /**
     * Return a testing instance of an article model
     *
     * @param id     The unique identifier object of the article
     * @param header The header string of the content
     * @return A testing instance of an article model
     */
    private Article getTestArticle(Id id, String header) {
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

    /**
     * Testing create functionality
     */
    @Test
    public void testCreateArticle() {
        Memory memory = this.getMemory();
        Article article = this.getTestArticle(new Id(0), "content-header");

        try {
            article = memory.createArticle(article);
            assertEquals(1, article.getId().getId());
        } catch (Exception e) {
            assertEquals(null, e);
        }
    }

    /**
     * Testing get and set methods of the memory
     */
    @Test
    public void testGetAndUpdateArticle() {
        Memory memory = this.getMemory();
        Article article = this.getTestArticle(new Id(0), "some-content");

        try {
            article = memory.createArticle(article);
            assertEquals(article.getId().getId(), memory.getArticleById(article.getId()).getId().getId());

            article = this.getTestArticle(article.getId(), "123-content");
            article = memory.updateArticle(article);
            assertEquals("123-content", article.getContent().getHeader());

        } catch (Exception e) {
            assertEquals(null, e);
        } catch (ArticleNotFound notFound) {
            assertEquals(null, notFound);
        }
    }

    /**
     * Testing the addition of an author to an article
     */
    @Test
    public void testAddAuthorToArticle() {
        Memory memory = this.getMemory();
        Article article = this.getTestArticle(new Id(0), "header");

        try {
            article = memory.createArticle(article);
            article = memory.addAuthorToArticle(
                    article.getId(),
                    new Author(2, "SomeAuthor", "Some", null)
            );
            assertEquals(2, article.getAuthors().size());
        } catch (Exception e) {
            assertEquals(null, e);
        }
    }

    /**
     * Testing the addition of an author to an article
     */
    @Test
    public void testGetArticlesByAuthor() {
        Memory memory = this.getMemory();
        Article article = this.getTestArticle(new Id(0), "header");

        try {
            article = memory.createArticle(article);
            List<Article> articles = memory.getArticlesByAuthor(article.getAuthors().get(0).getId(), 0, 0);
            Article actual = articles.get(0);
            assertEquals(article.getId().getId(), actual.getId().getId());

        } catch (Exception e) {
            assertEquals(null, e);
        }
    }
}
