package org.sample.news.application.service;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.sample.news.application.ArticleModules;
import org.sample.news.application.command.CreateArticleCommand;
import org.sample.news.domain.exception.AuthorNotFound;
import org.sample.news.domain.exception.ValidationError;
import org.sample.news.domain.model.Article;
import org.sample.news.domain.service.ArticleService;

import java.util.ArrayList;

public class ArticleApplicationServiceTest {
    @Test

    public void testCreateArticle() {
        // by using the injector instance this became an integration test as the injected components are not mocked but
        // the actual implementation is injected
        Injector injector = Guice.createInjector(new ArticleModules());
        ArticleService service = injector.getInstance(ArticleService.class);

        try {
            // this need to cover more use cases and use data provider for it
            Article article = service.createArticle(new CreateArticleCommand(
                    "header",
                    "desc",
                    "body",
                    1,
                    new ArrayList<String>()
            ));
        } catch (Exception e) {
            assertEquals(null, e);
        } catch (AuthorNotFound authorNotFound) {
            assertEquals(null, authorNotFound);
        } catch (ValidationError validationError) {
            assertEquals(null, validationError);
        }
    }
}
