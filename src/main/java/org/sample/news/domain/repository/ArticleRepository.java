package org.sample.news.domain.repository;

import org.sample.news.domain.exception.ArticleNotFound;
import org.sample.news.domain.model.Article;
import org.sample.news.domain.model.article.Id;

/**
 * Created by moath.almallahi on 22/06/17.
 */
public interface ArticleRepository {
    public Article persist(Article article) throws Exception, ArticleNotFound;

    public Article getArticleById(Id article) throws Exception, ArticleNotFound;

    public void remove(Id id) throws Exception;
}
