package com.github.reap.application.guice;

import com.github.reap.ApplicationFormPresenter;
import com.github.reap.ApplicationFormView;
import com.github.reap.ApplicationFormViewImpl;
import com.google.inject.AbstractModule;

public class UiModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ApplicationFormView.class).to(ApplicationFormViewImpl.class);
        bind(ApplicationFormPresenter.class);
    }


}
