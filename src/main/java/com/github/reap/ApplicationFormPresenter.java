package com.github.reap;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;

public class ApplicationFormPresenter {

    private final ApplicationFormView view;
    private ApplicationFormModel model;

    public ApplicationFormPresenter(ApplicationFormView view, ApplicationFormModel model) {
        this.view = view;
        bindPresenterToView();
        this.model = model;
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
    }

    public ApplicationFormView getView() {
        return view;
    }

}
