package ru.popovich.emergencyassist.model;

public enum UserEmployeeRole {

    EMPLOYEE("Социальный работник"),

    SPECIALIST("Специалист"),

    ANALYST("Аналитик"),

    CHIEF("Начальник"),

    DIRECTOR("Директор"),

    ACCOUNTANT("Бухгалтер"),

    ADMIN("Администратор");

    private String description;

    UserEmployeeRole() {
    }

    UserEmployeeRole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
