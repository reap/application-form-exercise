package com.github.reap.application.model;


public class ApplicationFormModel {

    private String firstName;
    private String lastName;
    private Gender gender;
    private String reasonForApplying;
    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public String getReasonForApplying() {
        return reasonForApplying;
    }
    public void setReasonForApplying(String reasonForApplying) {
        this.reasonForApplying = reasonForApplying;
    }


}
