package com.github.reap;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ApplicationFormViewImpl extends CustomComponent implements ApplicationFormView {

    private TextField firstNameField;
    private TextField lastNameField;
    private ComboBox genderField;
    private TextArea reasonForApplyingField;

    public ApplicationFormViewImpl() {
        Panel rootPanel = new Panel();
        FormLayout layout = new FormLayout();
        rootPanel.setContent(layout);
        
        firstNameField = new TextField("First name");
        firstNameField.setImmediate(true);

        lastNameField = new TextField("Last name");
        lastNameField.setImmediate(true);
        
        genderField = new ComboBox("Gender");
        for (Gender gender : Gender.values()) {
            genderField.addItem(gender);
            genderField.setItemCaption(gender, gender.getName());
        }
        genderField.setNullSelectionAllowed(false);
        genderField.setImmediate(true);
        
        reasonForApplyingField = new TextArea("Why are you applying to this job?");
        reasonForApplyingField.setImmediate(true);
        
        layout.addComponent(firstNameField);
        layout.addComponent(lastNameField);
        layout.addComponent(genderField);
        layout.addComponent(reasonForApplyingField);
        
        setCompositionRoot(rootPanel);
    }

    @Override
    public Component getComponent() {
        return this;
    }
    
    @Override
    public void addFirstNameFieldChangeListener(ValueChangeListener listener) {
        firstNameField.addValueChangeListener(listener);
    }

    @Override
    public void addLastNameFieldChangeListener(ValueChangeListener listener) {
        lastNameField.addValueChangeListener(listener);
    }
    
    @Override
    public void addGenderFieldChangeListener(ValueChangeListener listener) {
        genderField.addValueChangeListener(listener);
    }

    @Override
    public void addReasonForApplyingFieldChangeListener(ValueChangeListener listener) {
        reasonForApplyingField.addValueChangeListener(listener);
    }
}
