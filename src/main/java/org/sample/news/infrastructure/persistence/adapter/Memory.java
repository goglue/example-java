package org.sample.news.infrastructure.persistence.adapter;

import com.google.inject.Inject;
import org.sample.news.domain.exception.ArticleNotFound;
import org.sample.news.domain.model.Article;
import org.sample.news.domain.model.Author;
import org.sample.news.domain.model.Factory;
import org.sample.news.domain.model.article.Id;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Moath Almallahi
 */
public class Memory implements AdapterDAO {
    private int currentIncrement;

    /**
     * The 2D-Array represent the index by author ID to multiple articles
     */
    private HashMap<Integer, List<Id>> authorsArticlesIndex;

    /**
     * Model Factory to generate the stored entities
     */
    private Factory modelFactory;

    /**
     * The In-Ram memory
     */
    private List<Article> storage;

    @Inject
    public Memory(Factory modelFactory) {
        this.currentIncrement = 0;
        this.modelFactory = modelFactory;
        this.storage = new ArrayList<Article>();
        this.authorsArticlesIndex = new HashMap<Integer, List<Id>>();
    }

    public Article createArticle(Article article) throws Exception {
        Article newArticle = this.modelFactory.createExistingArticle(
                this.modelFactory.createArticleId(1 + this.currentIncrement),
                article.getContent(),
                article.getPublishDate(),
                article.getAuthors(),
                article.getKeywords(),
                article.isDeleted()
        );
        synchronized (this) {
            this.storage.add(this.currentIncrement, newArticle);
        }
        this.currentIncrement++;

        //index the author article
        for (int i = 0; i < article.getAuthors().size(); i++) {
            this.indexAuthorArticle(article.getAuthors().get(i), newArticle.getId());
        }

        return newArticle.copy();
    }

    private void indexAuthorArticle(Author author, Id id) {
        List<Id> ids;
        if (!this.authorsArticlesIndex.containsKey(author.getId())) {
            ids = new ArrayList<Id>();
        } else {
            ids = this.authorsArticlesIndex.get(author.getId());
        }

        ids.add(id);
        this.authorsArticlesIndex.put(author.getId(), ids);
    }

    public Article updateArticle(Article article) throws Exception, ArticleNotFound {
        if (!article.getId().isValid()) {
            throw new ArticleNotFound();
        }

        synchronized (this) {
            this.storage.set((article.getId().getId() - 1), article.copy());
        }

        for (int i = 0; i < article.getAuthors().size(); i++) {
            this.indexAuthorArticle(article.getAuthors().get(i), article.getId());
        }

        return article;
    }

    public Article getArticleById(Id articleId) throws Exception, ArticleNotFound {
        if (!articleId.isValid()) {
            throw new ArticleNotFound();
        }

        synchronized (this) {
            return this.storage.get(articleId.getId() - 1).copy();
        }
    }

    public Article addAuthorToArticle(Id articleId, Author author) throws Exception {
        synchronized (this) {
            Article article = this.storage.get(articleId.getId() - 1);
            article.getAuthors().add(author.copy());
            this.storage.set(articleId.getId() - 1, article);
            this.indexAuthorArticle(author, articleId);

            return article.copy();
        }
    }

    public List<Article> getArticlesByAuthor(int authorId, int cursor, int limit) {
        List<Id> indexedAuthorArticles = this.authorsArticlesIndex.get(authorId);
        List<Article> authorArticles = new ArrayList<Article>(indexedAuthorArticles.size());
        for (Id indexedAuthorArticle : indexedAuthorArticles) {
            int articleId = indexedAuthorArticle.getId();
            synchronized (this) {
                Article article = this.storage.get(articleId - 1);
                authorArticles.add(article);
            }
        }

        return authorArticles;
    }

    public void removeArticle(Id articleId) throws Exception {
        synchronized (this) {
            Article updated = this.storage.get(articleId.getId() - 1);
            this.storage.set(articleId.getId() - 1, this.modelFactory.createExistingArticle(
                    updated.getId(),
                    updated.getContent(),
                    updated.getPublishDate(),
                    updated.getAuthors(),
                    updated.getKeywords(),
                    true
            ));
        }
    }
}
