package org.sample.news.application.command;

import org.sample.news.domain.exception.ValidationError;

import java.util.List;

/**
 * Create article command class, represents the data needed for the
 */
public class CreateArticleCommand implements ApplicationCommand {

    /**
     * The author ID who is sending this command *
     */
    private final int authorId;

    /**
     * The article header text *
     */
    private final String header;

    /**
     * The description of the article *
     */
    private final String description;

    /**
     * The body of the article *
     */
    private final String body;

    private final List<String> keywords;

    /**
     * CreateArticleCommand constructor
     *
     * @param header      The text of the article heading
     * @param body        The text of the article body
     * @param description The description about the article
     * @param authorId    The author ID who wrote this article
     * @param keywords    The array of keywords tagged with an article
     */
    public CreateArticleCommand(
            String header,
            String description,
            String body,
            int authorId,
            List<String> keywords) throws ValidationError {
        if ((header.equals("") || body.equals("") || description.equals("") || 0 == authorId)) {
            throw new ValidationError("All values are requied");
        }
        this.header = header;
        this.body = body;
        this.description = description;
        this.authorId = authorId;
        this.keywords = keywords;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    /**
     * @return The header text
     */
    public String getHeader() {
        return header;
    }

    /**
     * @return The body text
     */
    public String getBody() {
        return body;
    }

    /**
     * @return The description text
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return The author ID
     */
    public int getAuthorId() {
        return authorId;
    }
}
