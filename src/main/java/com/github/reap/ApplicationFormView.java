package com.github.reap;

import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Component;

public interface ApplicationFormView {

    Component getComponent();

    void addFirstNameFieldChangeListener(ValueChangeListener listener);
    void addLastNameFieldChangeListener(ValueChangeListener listener);
    void addGenderFieldChangeListener(ValueChangeListener listener);
    void addReasonForApplyingFieldChangeListener(ValueChangeListener listener);

}
