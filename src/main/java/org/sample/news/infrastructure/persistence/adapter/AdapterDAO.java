package org.sample.news.infrastructure.persistence.adapter;

import org.sample.news.domain.exception.ArticleNotFound;
import org.sample.news.domain.model.Article;
import org.sample.news.domain.model.Author;
import org.sample.news.domain.model.article.Id;

import java.util.List;

/**
 * Abstract the connection initialization logic/strategy behind an interface
 */
public interface AdapterDAO {
    public Article createArticle(Article article) throws Exception;

    public Article updateArticle(Article article) throws Exception, ArticleNotFound;

    public Article getArticleById(Id articleId) throws Exception, ArticleNotFound;

    public Article addAuthorToArticle(Id articleId, Author author) throws Exception;

    public List<Article> getArticlesByAuthor(int authorId, int cursor, int limit);

    public void removeArticle(Id articleId) throws Exception;
}
