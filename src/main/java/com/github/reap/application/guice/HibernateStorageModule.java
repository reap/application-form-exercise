package com.github.reap.application.guice;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.service.jdbc.connections.internal.DatasourceConnectionProviderImpl;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
import org.hsqldb.jdbc.JDBCDataSource;

import com.github.reap.ApplicationStorage;
import com.github.reap.application.storage.hibernate.ApplicationEntity;
import com.github.reap.application.storage.hibernate.HibernateApplicationStorage;
import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;

public class HibernateStorageModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(SessionFactory.class).toProvider(HibernateSessionFactoryProvider.class).in(Singleton.class);
        bind(ApplicationStorage.class).to(HibernateApplicationStorage.class);
    }
        
        
}
