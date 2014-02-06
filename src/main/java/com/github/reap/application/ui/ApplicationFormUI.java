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
public class ApplicationFormUI extends UI implements ApplicationStoredListener{

    private VerticalLayout rootLayout;
    private Injector injector;

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = ApplicationFormUI.class, widgetset = "com.github.reap.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    public ApplicationFormUI() {
        injector = Guice.createInjector(new DatasourceModule(), new HibernateStorageModule(), new UiModule());
    }
    
    @Override
    protected void init(VaadinRequest request) {
        rootLayout = new VerticalLayout();
        rootLayout.setMargin(true);
        setContent(rootLayout);
        repaintUi();
    }

    @Override
    public void notifyApplicationStored(int applicationId) {
        repaintUi();
    }

    private void repaintUi() {
        rootLayout.removeAllComponents();
        ApplicationFormPresenter applicationPresenter = injector.getInstance(ApplicationFormPresenter.class);
        applicationPresenter.addApplicationStoredListener(this);
        rootLayout.addComponent(applicationPresenter.getView().getComponent());
    }

}
