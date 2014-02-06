package com.github.reap.application.guice;

import com.github.reap.application.ui.creation.ApplicationFormPresenter;
import com.github.reap.application.ui.creation.ApplicationFormView;
import com.github.reap.application.ui.creation.ApplicationFormViewImpl;
import com.google.inject.AbstractModule;

public class UiModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ApplicationFormView.class).to(ApplicationFormViewImpl.class);
        bind(ApplicationFormPresenter.class);
    }


}
