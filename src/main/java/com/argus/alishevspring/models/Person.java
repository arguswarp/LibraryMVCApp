package com.argus.alishevspring.models;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int personId;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String fullName;
    @Min(value = 1901, message = "Age of birth should be greater than 1900")
    private int ageOfBirth;

    public Person(int personId, String fullName, int ageOfBirth) {
        this.personId = personId;
        this.fullName = fullName;
        this.ageOfBirth = ageOfBirth;
    }

    public Person() {

    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAgeOfBirth() {
        return ageOfBirth;
    }

    public void setAgeOfBirth(int ageOfBirth) {
        this.ageOfBirth = ageOfBirth;
    }

}
