package com.github.reap.application.ui.listing;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class ApplicationStoredViewImpl implements ApplicationStoredView {

    private Label nameField;
    private Label genderField;
    private Label reasonForApplyingField;
    private Window dialog;

    public ApplicationStoredViewImpl() {
        dialog = new Window("Application succesfully submitted");
        
        VerticalLayout layout = new VerticalLayout();
        nameField = new Label();
        genderField = new Label();
        reasonForApplyingField = new Label();
        layout.addComponent(nameField);
        layout.addComponent(genderField);
        layout.addComponent(reasonForApplyingField);
        layout.addComponent(reasonForApplyingField);
        layout.addComponent(createCloseButton());
        dialog.setContent(layout);
    }

    private Button createCloseButton() {
        Button closeButton = new Button("Close");
        closeButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                UI.getCurrent().removeWindow(dialog);
            }
        });
        return closeButton;
    }

    @Override
    public void setName(String string) {
        nameField.setCaption("Name: " + string);
    }

    @Override
    public void setGender(String gender) {
        genderField.setCaption("Gender: " + gender);
    }

    @Override
    public void setReasonForApplying(String reasonForApplying) {
        reasonForApplyingField.setCaption("Reason for applying: " + reasonForApplying);
    }

    @Override
    public void show() {
        UI.getCurrent().addWindow(dialog);        
    }
    
}
