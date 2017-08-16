package org.sample.news.application;

import com.google.inject.AbstractModule;
import org.sample.news.application.service.ArticleApplicationService;
import org.sample.news.domain.model.Factory;
import org.sample.news.domain.repository.ArticleRepository;
import org.sample.news.domain.service.ArticleService;
import org.sample.news.infrastructure.persistence.adapter.AdapterDAO;
import org.sample.news.infrastructure.persistence.adapter.Memory;
import org.sample.news.infrastructure.persistence.repository.ArticleRepositoryImp;

/**
 * Created by moath on 26.06.17.
 */
public class ArticleModules extends AbstractModule {
    @Override
    protected void configure() {
        bind(ArticleService.class).to(ArticleApplicationService.class);
        bind(ArticleRepository.class).to(ArticleRepositoryImp.class);
        bind(AdapterDAO.class).to(Memory.class);
        bind(Factory.class).toInstance(new Factory());
    }
}
