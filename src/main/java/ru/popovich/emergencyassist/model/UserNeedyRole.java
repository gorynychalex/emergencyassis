package ru.popovich.emergencyassist.model;

public enum UserNeedyRole {

    HARDUP("Срочное облуживание"),

    HOME("Надомное обслуживание"),

    INVALID("Надомное обслуживание");

    private String description;

    UserNeedyRole() {
    }

    UserNeedyRole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
