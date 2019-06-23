package ru.popovich.emergencyassist.controller;

import org.springframework.web.bind.annotation.*;
import ru.popovich.emergencyassist.dbtest.UserGenerator;
import ru.popovich.emergencyassist.model.TaskSocialService;
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


    @GetMapping("{id}")
    public User getUserById(@PathVariable String id) { return getUserByIdPriv(id); }

    private User getUserByIdPriv(String id){
        return users.stream()
                .filter(t->t.getId().equals(id))
                .findFirst().get();
    }

    @PostMapping
    public User addTask(@RequestBody User user){
        users.add(user);
        return user;
    }
}
