package com.github.reap.application.guice;

import org.hibernate.SessionFactory;

import com.github.reap.application.storage.ApplicationStorage;
import com.github.reap.application.storage.hibernate.HibernateApplicationStorage;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class HibernateStorageModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(SessionFactory.class).toProvider(HibernateSessionFactoryProvider.class).in(Singleton.class);
        bind(ApplicationStorage.class).to(HibernateApplicationStorage.class);
    }
        
        
}
