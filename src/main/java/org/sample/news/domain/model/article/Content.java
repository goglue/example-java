package org.sample.news.domain.model.article;

/**
 * Content class which encapsulate the content properties of an Article
 * @author Moath Almallahi
 */
public class Content {
    private final String header;
    private final String description;
    private final String body;


    public Content(String header, String description, String body) {
        this.header = header;
        this.description = description;
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public String getDescription() {
        return description;
    }

    public String getHeader() {
        return header;
    }
}
