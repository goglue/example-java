package org.sample.news.application.command;

import org.sample.news.domain.exception.ValidationError;

import java.util.List;

/**
 * Created by moath on 26.06.17.
 */
public class CommandFactory {
    public CreateArticleCommand createArticleCommand(
            String header,
            String description,
            String body,
            int authorId,
            List<String> keywords
    ) throws ValidationError {
        return new CreateArticleCommand(
                header,
                description,
                body,
                authorId,
                keywords
        );
    }
}
