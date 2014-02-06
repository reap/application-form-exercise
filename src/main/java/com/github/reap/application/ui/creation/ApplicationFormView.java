package com.github.reap.application.ui.creation;

import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

public interface ApplicationFormView {

    Component getComponent();

    void addFirstNameFieldChangeListener(ValueChangeListener listener);
    void addLastNameFieldChangeListener(ValueChangeListener listener);
    void addGenderFieldChangeListener(ValueChangeListener listener);
    void addReasonForApplyingFieldChangeListener(ValueChangeListener listener);
    void addSubmitButtonClickListener(ClickListener listener);

}
