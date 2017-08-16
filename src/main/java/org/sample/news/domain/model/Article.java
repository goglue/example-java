package org.sample.news.domain.model;

import org.sample.news.domain.model.article.Content;
import org.sample.news.domain.model.article.Id;

import java.util.Date;
import java.util.List;

/**
 * Article class is the aggregate root of the domain, as include other entities
 * and sustain integrity of the whole
 *
 * @author moath
 */
public class Article implements Cloneable {

    /**
     * The unique identifier of an Article *
     */
    private final Id id;

    /**
     * The content of a single Article *
     */
    private final Content content;

    /**
     * The date where the article has been published *
     */
    private final Date publishDate;

    /**
     * The list of authors who created this Article *
     */
    private final List<Author> authors;

    /**
     * The list of keywords associated within the article *
     */
    private final List<String> keywords;

    /**
     * A flag to identify deleted articles, as articles are being deleted softly.
     */
    private final boolean isDeleted;

    /**
     * Article Constructor
     *
     * @param id          The unique identifier of the Article
     * @param content     The content object of the Article
     * @param publishDate The publishing date
     * @param authors     The authors of the article
     * @param keywords    The array of keywords associated within the article
     * @param isDeleted   Flag determines whether the article is deleted or not
     */
    public Article(
            Id id,
            Content content,
            Date publishDate,
            List<Author> authors,
            List<String> keywords,
            boolean isDeleted) {
        Id articleId = id;
        if (null == articleId) {
            articleId = new Id(0);
        }

        this.id = articleId;
        this.content = content;
        this.publishDate = publishDate;
        this.authors = authors;
        this.keywords = keywords;
        this.isDeleted = isDeleted;
    }

    /**
     * @return Object identifier of the article
     */
    public Id getId() {
        return this.id;
    }

    /**
     * @return Content of the article, could be null
     */
    public Content getContent() {
        return this.content;
    }

    /**
     * @return When the article was published
     */
    public Date getPublishDate() {
        return this.publishDate;
    }

    /**
     * @return The list of authors contributed to this article
     */
    public List<Author> getAuthors() {
        return this.authors;
    }

    /**
     * @return Array of the keywords associated with the article
     */
    public List<String> getKeywords() {
        return this.keywords;
    }

    /**
     * @return Boolean value, true if the article is deleted.
     */
    public boolean isDeleted() {
        return isDeleted;
    }

    /**
     * Allow object copying as the author list and keywords are modifiable within the instance
     *
     * @return A copy of the instance
     * @throws CloneNotSupportedException If the clone method is not implemented in the native runtime compiler
     */
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Article copy() throws CloneNotSupportedException {
        return (Article) this.clone();
    }
}
