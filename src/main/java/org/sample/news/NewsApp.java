package org.sample.news;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.sample.news.application.ArticleModules;
import org.sample.news.application.command.CommandFactory;
import org.sample.news.domain.service.ArticleService;
import org.sample.news.server.ApplicationServer;
import org.sample.news.server.ArticleApplicationServer;
import org.sample.news.server.ResponseFactory;

import java.io.IOException;

/**
 * Created by moath on 28.06.17.
 */
public class NewsApp {
    public static void main(String []args) {
        Injector injector = Guice.createInjector(new ArticleModules());
        ArticleService articleService = injector.getInstance(ArticleService.class);

        ApplicationServer server = new ArticleApplicationServer(
                articleService,
                new CommandFactory(),
                new ResponseFactory(),
                8090
        );

        try {
            server.start();
        } catch (IOException e) {
            System.out.println("Could not start server");
            System.exit(-1);
        }

        try {
            server.waitForSignal();
        } catch (InterruptedException e) {
            System.out.println("Signal received, exiting");
            server.stop();
        }
    }
}
