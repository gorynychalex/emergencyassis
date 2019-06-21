package ru.popovich.emergencyassist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.popovich.emergencyassist.dbtest.UserGenerator;
import ru.popovich.emergencyassist.model.User;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    List<User> users = UserGenerator.getInstance().getUsers();

    @GetMapping
    public List<User> getUsers() {
        return users;
    }

}
