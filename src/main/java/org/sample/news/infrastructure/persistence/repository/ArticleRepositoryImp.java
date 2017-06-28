package org.sample.news.infrastructure.persistence.repository;

import com.google.inject.Inject;
import org.sample.news.domain.exception.ArticleNotFound;
import org.sample.news.domain.model.Article;
import org.sample.news.domain.model.Factory;
import org.sample.news.domain.model.article.Id;
import org.sample.news.domain.repository.ArticleRepository;
import org.sample.news.infrastructure.persistence.adapter.AdapterDAO;


/**
 * @author Moath Almallahi
 */
public class ArticleRepositoryImp implements ArticleRepository {
    /**
     * The connection abstraction
     */
    private AdapterDAO articlesDAO;

    @Inject
    public ArticleRepositoryImp(
            AdapterDAO dao
    ) {
        this.articlesDAO = dao;
    }

    /**
     * Persist responsible to createArticle/update a given entity by keeping in mind a transactional state in case of having
     * sub-entities
     *
     * @param article The article entity need to be persisted
     * @return A new fully-immutable instance of the persisted article
     * @throws Exception In case of an error while executing
     */
    public Article persist(Article article) throws Exception, ArticleNotFound {
        if (!article.getId().isValid()) {
            return this.articlesDAO.createArticle(article);
        }

        return this.articlesDAO.updateArticle(article);
    }

    /**
     * Load an article by the given ID from the persistence layer
     *
     * @param id The unique identifier of the article
     * @return An new fully-immutable instance of the article found
     * @throws Exception When a DB error or the article not found
     */
    public Article getArticleById(Id id) throws Exception, ArticleNotFound {
        return this.articlesDAO.getArticleById(id);
    }

    /**
     * Removing an article from the persistence layer by assigning the is_deleted flag to true
     *
     * @param id The unique identifier of the article need to be deleted
     * @throws Exception In case of error while trying to execute the query
     */
    public void remove(Id id) throws Exception {
        this.articlesDAO.removeArticle(id);
    }
}
