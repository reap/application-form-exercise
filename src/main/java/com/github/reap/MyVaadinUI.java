package com.github.reap;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
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

import com.github.reap.application.guice.DatasourceModule;
import com.github.reap.application.guice.HibernateStorageModule;
import com.github.reap.application.guice.UiModule;
import com.github.reap.application.storage.hibernate.ApplicationEntity;
import com.github.reap.application.storage.hibernate.HibernateApplicationStorage;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("reindeer")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI
{

 
    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "com.github.reap.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        
        Injector injector = Guice.createInjector(new DatasourceModule(), new HibernateStorageModule(), new UiModule());
        ApplicationFormPresenter applicationPresenter = injector.getInstance(ApplicationFormPresenter.class);
        
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);

        layout.addComponent(applicationPresenter.getView().getComponent());
    }



}
