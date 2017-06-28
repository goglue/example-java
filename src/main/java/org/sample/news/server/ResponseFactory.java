package org.sample.news.server;

import org.sample.news.protocol.ArticleProtoType;

import java.util.List;

/**
 * Created by moath on 26.06.17.
 */
public class ResponseFactory {
    public ArticleProtoType.Article createArticleResponse(
            int id,
            ArticleProtoType.Content content,
            int publishDate,
            List<String> keywords
    ) {
        return ArticleProtoType.
                Article.newBuilder().
                setContent(content).
                setId(id).
                setPublishDate(publishDate).
                addAllKeywords(keywords).build();
    }

    public ArticleProtoType.Content createContentResponse(String header, String description, String body) {
        return ArticleProtoType.Content.newBuilder().
                setContentHeader(header).
                setContentDescription(description).
                setContentBody(body)
                .build();
    }
}
