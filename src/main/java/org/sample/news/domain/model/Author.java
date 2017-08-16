package org.sample.news.domain.model;

import org.sample.news.domain.model.author.Credentials;

/**
 * @author Moath Almallahi
 */
public class Author implements Cloneable {
    private final String firstName;
    private final String lastName;
    private final Credentials credentials;
    private final int id;

    public Author(int id, String firstName, String lastName, Credentials credentials) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.credentials = credentials;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Author copy() throws CloneNotSupportedException {
        return (Author) this.clone();
    }
}
