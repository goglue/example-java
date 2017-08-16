package org.sample.news.domain.exception;

/**
 * ArticleNotFound exception is the domain exception where it get thrown when an article is not found
 */
public class ArticleNotFound extends Throwable {
    public ArticleNotFound() {
        super("Article not found");
    }
}
