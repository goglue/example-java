package org.sample.news.infrastructure.persistence.adapter;

import org.sample.news.domain.exception.ArticleNotFound;
import org.sample.news.domain.model.Article;
import org.sample.news.domain.model.Author;
import org.sample.news.domain.model.article.Id;

import java.util.List;

/**
 * @author Moath Almallahi
 */
public class Postegres implements AdapterDAO {
    public Article createArticle(Article article) throws Exception {
        return null;
    }

    public Article updateArticle(Article article) throws Exception, ArticleNotFound {
        return null;
    }

    public Article getArticleById(Id articleId) throws Exception, ArticleNotFound {
        return null;
    }

    public Article addAuthorToArticle(Id articleId, Author author) throws Exception {
        return null;
    }

    public List<Article> getArticlesByAuthor(int authorId, int cursor, int limit) {
        return null;
    }

    public void removeArticle(Id articleId) throws Exception {

    }
}
