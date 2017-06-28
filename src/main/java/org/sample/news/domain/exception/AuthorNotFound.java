package org.sample.news.domain.exception;

/**
 * Created by moath on 27.06.17.
 */
public class AuthorNotFound extends Throwable {
    public AuthorNotFound() {
        super("Author not found");
    }
}
