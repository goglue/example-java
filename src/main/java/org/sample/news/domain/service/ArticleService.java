package org.sample.news.domain.service;

import org.sample.news.application.command.CreateArticleCommand;
import org.sample.news.application.query.GetArticleById;
import org.sample.news.domain.exception.ArticleNotFound;
import org.sample.news.domain.exception.AuthorNotFound;
import org.sample.news.domain.model.Article;

/**
 * The ArticleApplicationService interface represent the edges (API) of the
 * service
 */
public interface ArticleService {

    /**
     * Creates an Article within the given object and return the newly created
     * article object
     *
     * @param createArticle Create an article command
     * @return The newly created article
     */
    public Article createArticle(CreateArticleCommand createArticle) throws Exception, AuthorNotFound;

    /**
     * Reads the article by the given id
     *
     * @return Article
     * @throws ArticleNotFound If the article is not found
     */
    public Article getArticleById(GetArticleById articleById) throws ArticleNotFound;
}
