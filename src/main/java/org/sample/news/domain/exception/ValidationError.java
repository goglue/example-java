package org.sample.news.domain.exception;

/**
 * Created by moath on 27.06.17.
 */
public class ValidationError extends Throwable {
    public ValidationError(String error) {
        super(error);
    }
}
