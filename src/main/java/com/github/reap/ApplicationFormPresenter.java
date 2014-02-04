package com.github.reap;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class ApplicationFormPresenter {

    private final ApplicationFormView view;
    private ApplicationFormModel model;
    private ApplicationStorage storage;

    public ApplicationFormPresenter(ApplicationFormView view, ApplicationStorage storage) {
        this.view = view;
        this.storage = storage;
        bindPresenterToView();
        this.model = createNewModel();
    }

    protected ApplicationFormModel createNewModel() {
        return model;
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
                storage.save(model);
            }
        });
    }

    public ApplicationFormView getView() {
        return view;
    }

}
