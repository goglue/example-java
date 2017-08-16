package org.sample.news.application.query;

/**
 * Created by moath.almallahi on 22/06/17.
 */
public class GetArticleById {
    private int articleId;

    public GetArticleById(int articleId) {
        this.articleId = articleId;
    }

    public int getArticleId() {
        return articleId;
    }

}
