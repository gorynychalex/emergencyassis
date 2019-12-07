package ru.popovich.emergencyassist.model;

public enum Status {

    NEW("Новый"),

    PROCESSING("Выполняется"),

    PENDING("В ожидании"),

    SOLVED("Решено"),

    CLOSED("Закрыто");

    private String s;

    Status(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }
}
