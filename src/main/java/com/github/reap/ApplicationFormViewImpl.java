package com.github.reap;

import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class ApplicationFormViewImpl extends CustomComponent implements ApplicationFormView {

    private TextField firstNameField;
    private TextField lastNameField;
    private ComboBox genderField;
    private TextArea reasonForApplyingField;
    private Button submitButton;

    public ApplicationFormViewImpl() {
        Panel rootPanel = new Panel();
        FormLayout layout = new FormLayout();
        layout.setSpacing(true);
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
        
        submitButton = new Button("Send application");
        layout.addComponent(submitButton);

        
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
    
    @Override
    public void addSubmitButtonClickListener(ClickListener listener) {
        submitButton.addClickListener(listener);
    }

}
