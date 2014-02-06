package com.github.reap.application.ui.listing;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class ApplicationStoredViewImpl implements ApplicationStoredView {

    private String name;
    private String gender;
    private String reasonForApplying;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public void setReasonForApplying(String reasonForApplying) {
        this.reasonForApplying = reasonForApplying;
    }

    @Override
    public void show() {
        StringBuilder modelInformationBuilder = new StringBuilder();
        modelInformationBuilder.append("<br />");
        modelInformationBuilder.append("Name: ");
        modelInformationBuilder.append(name);

        modelInformationBuilder.append("<br />");
        modelInformationBuilder.append("Gender: ");
        modelInformationBuilder.append(gender);

        modelInformationBuilder.append("<br />");
        modelInformationBuilder.append("Reason for applying: ");
        modelInformationBuilder.append(reasonForApplying);

        Notification notification = new Notification("Application succesfully submitted", modelInformationBuilder.toString(), Type.HUMANIZED_MESSAGE, true);
        notification.setDelayMsec(2000);
        notification.show(Page.getCurrent());
    }
    
}
