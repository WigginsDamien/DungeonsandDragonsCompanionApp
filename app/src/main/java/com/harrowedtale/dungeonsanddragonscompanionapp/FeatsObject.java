package com.harrowedtale.dungeonsanddragonscompanionapp;

public class FeatsObject {


    private String Prerequisite;
    private String description;

    public FeatsObject() {

    }

    public FeatsObject(String Prerequisite, String description) {
        this.Prerequisite = Prerequisite;
        this.description = description;
    }

    public String getPrerequisite() {
        return Prerequisite;
    }

    public String getDescription() {
        return description;
    }
}
