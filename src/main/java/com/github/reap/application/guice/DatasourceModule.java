package com.github.reap.application.guice;

import javax.sql.DataSource;

import org.hsqldb.jdbc.JDBCDataSource;

import com.google.inject.AbstractModule;

public class DatasourceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DataSource.class).toInstance(createDatasource());
    }

    private JDBCDataSource createDatasource() {
        JDBCDataSource dataSource = new JDBCDataSource();
        dataSource.setUrl("jdbc:hsqldb:mem:.");
        dataSource.setUser("sa");
        dataSource.setPassword("");
        return dataSource;
    }

}
