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

import com.github.reap.application.storage.hibernate.ApplicationEntity;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class HibernateSessionFactoryProvider implements Provider<SessionFactory> {

    private static final String APPLICATION_TABLE_CREATION_STATEMENT = 
            "CREATE TABLE application (id IDENTITY, firstname VARCHAR(64) NOT NULL, lastname VARCHAR(64) NOT NULL, gender VARCHAR(10) NOT NULL, reason_for_applying VARCHAR(255))";
    
    private DataSource dataSource;

    @Inject
    public HibernateSessionFactoryProvider(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Override
    public SessionFactory get() {
        ensureDatabaseSchemaIsCreated(dataSource);
        return createHibernateSessionFactory(dataSource);
    }
    
    private SessionFactory createHibernateSessionFactory(DataSource dataSource) {
        ConnectionProvider connectionProvider = createDataSourceConnectionProvider(dataSource);
        ServiceRegistry registry = buildServiceRegistry(connectionProvider);
        Configuration configuration = buildHibernateConfiguration();
        SessionFactory instance = configuration.buildSessionFactory(registry);
        return instance;
    }


    private void ensureDatabaseSchemaIsCreated(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            if (isDatabaseSchemaMissing(connection)) {
                createDatabaseSchema(connection);
            }
        } catch (SQLException e) {
            throw new Error("Unable to ensure that database schema is created", e);
        } 
    }


    private void createDatabaseSchema(Connection connection) throws SQLException {
        System.out.println("\n\n                 creating application table!!\n\n");
        connection.createStatement().executeUpdate(APPLICATION_TABLE_CREATION_STATEMENT);
    }


    private boolean isDatabaseSchemaMissing(Connection connection) throws SQLException {
        // check if "application" table exists in database schema 
        ResultSet resultSet = connection.getMetaData().getTables(null, null, "application", null);
        return !resultSet.next();
    }



    
    protected DatasourceConnectionProviderImpl createDataSourceConnectionProvider(DataSource datasource) {
        DatasourceConnectionProviderImpl connectionProvider = new DatasourceConnectionProviderImpl();
        Map<String, Object> connectionProviderConfiguration = new HashMap<String, Object>();
        connectionProviderConfiguration.put(Environment.DATASOURCE, datasource);
        connectionProvider.configure(connectionProviderConfiguration);
        return connectionProvider;
    }
    
    protected ServiceRegistry buildServiceRegistry(ConnectionProvider connectionProvider) {
        ServiceRegistry registry = new ServiceRegistryBuilder()
                .addService(ConnectionProvider.class, connectionProvider).buildServiceRegistry();
        return registry;
    }

    protected Configuration buildHibernateConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.current_session_context_class", ThreadLocalSessionContext.class.getName());

        configuration.addAnnotatedClass(ApplicationEntity.class);
        
        return configuration;
    }

}
