package ru.popovich.emergencyassist.dto;

import ru.popovich.emergencyassist.model.User;

public class UserDto {

    private User user;

    public UserDto() {}

    public UserDto(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
