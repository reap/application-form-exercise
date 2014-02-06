package com.github.reap.application.ui.listing;

import com.github.reap.application.model.ApplicationFormModel;
import com.github.reap.application.storage.ApplicationStorage;
import com.github.reap.application.ui.ApplicationStoredListener;
import com.google.inject.Inject;

public class ApplicationStoredPresenter implements ApplicationStoredListener {

    private ApplicationStoredView view;
    private ApplicationStorage storage;

    @Inject
    public ApplicationStoredPresenter(ApplicationStoredView view, ApplicationStorage storage) {
        this.view = view;
        this.storage = storage;
    }
    
    @Override
    public void notifyApplicationStored(int applicationId) {
        ApplicationFormModel model = storage.load(applicationId);
        view.setName(model.getFirstName() + " " + model.getLastName());
        view.setGender(model.getGender().getName());
        view.setReasonForApplying(model.getReasonForApplying());
        
        view.show();
    }

}
