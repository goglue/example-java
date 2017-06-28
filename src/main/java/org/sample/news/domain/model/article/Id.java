package org.sample.news.domain.model.article;

/**
 * @author Moath Almallahi
 */
public class Id {
    private int id;

    public Id(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isValid() {
        return 0 < id;
    }
}
