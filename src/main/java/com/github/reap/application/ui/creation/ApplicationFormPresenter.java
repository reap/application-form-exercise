package com.github.reap.application.ui.creation;

import java.util.ArrayList;
import java.util.Collection;

import com.github.reap.application.model.ApplicationFormModel;
import com.github.reap.application.model.Gender;
import com.github.reap.application.storage.ApplicationStorage;
import com.github.reap.application.ui.ApplicationStoredListener;
import com.google.inject.Inject;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class ApplicationFormPresenter {

    private final ApplicationFormView view;
    private ApplicationFormModel model;
    private ApplicationStorage storage;
    private Collection<ApplicationStoredListener> applicationStoredListeners = new ArrayList<>();;

    @Inject
    public ApplicationFormPresenter(ApplicationFormView view, ApplicationStorage storage) {
        this.view = view;
        this.storage = storage;
        bindPresenterToView();
        this.model = createNewModel();
    }

    protected ApplicationFormModel createNewModel() {
        return new ApplicationFormModel();
    }

    private void bindPresenterToView() {
        this.view.addFirstNameFieldChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                String firstName = (String) event.getProperty().getValue();
                model.setFirstName(firstName);
            }
        });
        this.view.addLastNameFieldChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                String lastName = (String) event.getProperty().getValue();
                model.setLastName(lastName);
            }
        });
        this.view.addGenderFieldChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                Gender gender = (Gender) event.getProperty().getValue();
                model.setGender(gender);
            }
        });
        this.view.addReasonForApplyingFieldChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                String reasonForApplying =  (String) event.getProperty().getValue();
                model.setReasonForApplying(reasonForApplying);
            }
        });
        this.view.addSubmitButtonClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                ArrayList<String> modelErrors = getModelErrors(model);
                if (modelErrors.isEmpty()) {
                    saveModel();
                } else {
                    view.showErrors(modelErrors);
                }
            }
        });
    }

    protected ArrayList<String> getModelErrors(ApplicationFormModel model) {
        ArrayList<String> errors = new ArrayList<>();
        if (model.getFirstName() == null || model.getFirstName().equals("")) {
            errors.add("First name must be given");
        }
        if (model.getLastName() == null || model.getLastName().equals("")) {
            errors.add("Last name must be given");
        }
        if (model.getGender() == null) {
            errors.add("Gender must be given");
        }
        return errors;
    }

    public ApplicationFormView getView() {
        return view;
    }
    
    @Inject
    public void addApplicationStoredListener(ApplicationStoredListener listener) {
        applicationStoredListeners.add(listener);
    }

    private void saveModel() {
        Integer id = storage.save(model);
        for (ApplicationStoredListener listener : applicationStoredListeners) {
            listener.notifyApplicationStored(id);
        }
    }

}
