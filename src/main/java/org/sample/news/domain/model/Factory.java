package org.sample.news.domain.model;

import org.sample.news.domain.model.article.Content;
import org.sample.news.domain.model.article.Id;
import org.sample.news.domain.model.author.Credentials;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * CommandFactory responsibility is to create Entities taking into consideration two contexts for each entity, createNew which
 * is for a totally new entity, and createExistingArticle
 *
 * @author Moath Almallahi
 */
public class Factory {
    private SimpleDateFormat dateFormatter;

    /**
     * CommandFactory constructor preload the class with the default string format for parsing the incoming dates
     */
    public Factory() {
        this.dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
    }

    /**
     * CommandFactory method to create new article for handy usage
     *
     * @param content  The article content object
     * @param authors  The list of authors who collaborated in creating the article
     * @param keywords The list of words to tag the article
     * @return The new object of the article, with publish date set to NOW
     */
    public Article createNewArticle(Content content, List<Author> authors, List<String> keywords) {
        return new Article(
                null,
                content,
                new Date(),
                authors,
                keywords,
                false
        );
    }

    /**
     * Parses the given string into the default format of the domain
     *
     * @param date The date in string format to be parsed
     * @return The new Date object
     */
    private Date getDateFromString(String date) {
        Date d;
        try {
            d = this.dateFormatter.parse(date);
        } catch (ParseException e) {
            return null;
        }

        return d;
    }

    /**
     * Return an instance of the article from the given parts
     *
     * @param id          The id object
     * @param content     The content object
     * @param publishDate The publishing date
     * @param authors     The list of authors
     * @param keywords    The list of keywords
     * @param isDeleted   The flag whether the article is deleted or not
     * @return Article instance from the parts
     */
    public Article createExistingArticle(
            Id id, Content content, Date publishDate, List<Author> authors, List<String> keywords, boolean isDeleted
    ) {
        return new Article(
                id,
                content,
                publishDate,
                authors,
                keywords,
                isDeleted
        );
    }

    /**
     * Create an Author by the given values
     *
     * @param id          The unique identifier for the author object
     * @param firstName   The first name of the author
     * @param lastName    The last name of the author
     * @param credentials The credentials object of the author
     * @return New instance of the author that could be attached within the article object
     */
    public Author createExistingAuthor(int id, String firstName, String lastName, Credentials credentials) {
        return new Author(id, firstName, lastName, credentials);
    }

    /**
     * CommandFactory method for the credentials object of the author
     *
     * @param id       The unique identifier
     * @param username The username of the credentials, could be an email or a username
     * @param secret   The secret key of the credentials
     * @return New instance of the credentials object where it can be used with the article object
     */
    public Credentials createExistingCredentials(int id, String username, String secret) {
        return new Credentials(username, secret);
    }

    public Id createArticleId(int id) {
        return new Id(id);
    }

    public Content createContent(String header, String description, String body) {
        return new Content(
                header, description, body
        );
    }
}
