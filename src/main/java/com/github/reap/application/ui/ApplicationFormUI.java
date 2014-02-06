package com.github.reap.application.ui;

import javax.servlet.annotation.WebServlet;

import com.github.reap.application.guice.DatasourceModule;
import com.github.reap.application.guice.HibernateStorageModule;
import com.github.reap.application.guice.UiModule;
import com.github.reap.application.ui.creation.ApplicationFormPresenter;
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
public class ApplicationFormUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = ApplicationFormUI.class, widgetset = "com.github.reap.AppWidgetSet")
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
