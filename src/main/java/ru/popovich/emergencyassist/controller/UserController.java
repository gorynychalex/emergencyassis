package ru.popovich.emergencyassist.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/add/{user}")
    public void addUser(@PathVariable String user){
        userDao.save(new User(user, "123", UserRole.HARDUP));
    }

    @GetMapping("{name}")
    public User getUserByName(@PathVariable("name") User user) { return user; }

    @PostMapping
    public User add(@RequestBody User user){
        return userDao.save(user);
    }

    @PutMapping("{nickname}")
    public User update(@PathVariable("nickname") User userOriginal,
                       @RequestBody User user){
        BeanUtils.copyProperties(user, userOriginal, "nickname");

        User user1 = userDao.save(userOriginal);

        return user1;
    }

    @DeleteMapping("{nickname}")
    public void delete(@PathVariable("nickname") User user){

        user.getNickname();

        userDao.delete(user);
    }
}
