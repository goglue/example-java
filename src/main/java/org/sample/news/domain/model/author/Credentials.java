package org.sample.news.domain.model.author;

/**
 * @author Moath Almallahi
 */
public class Credentials {
    private final String username;
    private final String secret;

    public Credentials(String username, String secret) {
        this.username = username;
        this.secret = secret;
    }

    public String getUsername() {
        return this.username;
    }

    public String getSecret() {
        return this.secret;
    }
}
