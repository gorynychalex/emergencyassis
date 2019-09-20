package ru.popovich.emergencyassist.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
import ru.popovich.emergencyassist.dbtest.UserGenerator;
import ru.popovich.emergencyassist.model.TaskSocialService;
import ru.popovich.emergencyassist.model.User;
import ru.popovich.emergencyassist.model.UserRole;
import ru.popovich.emergencyassist.repository.UserDao;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserByName(@PathVariable("name") User user) { return user; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User add(@RequestBody User user){
        return userDao.save(user);
    }

    @PutMapping("{nickname}")
    public User update(@PathVariable("nickname") User userOriginal,
                       @RequestBody User user){
        BeanUtils.copyProperties(user, userOriginal, "nickname");

        return userDao.save(userOriginal);
    }

    @DeleteMapping("{nickname}")
    public void delete(@PathVariable("nickname") User user){ userDao.delete(user); }
}
