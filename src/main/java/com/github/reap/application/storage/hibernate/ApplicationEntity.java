package com.github.reap.application.storage.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.github.reap.Gender;

@Entity
@Table(name = "application")
public class ApplicationEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "firstname")
    private String firstName;
    
    @Column(name = "lastname")
    private String lastName;
    
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    @Column(name = "reason_for_applying")
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
