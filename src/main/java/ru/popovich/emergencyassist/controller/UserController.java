package ru.popovich.emergencyassist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/add/usertestallpriv")
    public void addALLPRIVUser(){
        User user = new User("popovich","12345678");
        List<UserRole> roles = new ArrayList<>();
        roles.add(UserRole.EMPLOYEE); roles.add(UserRole.ADMIN);
        user.setRoles(roles);
        user.setEmail("gorynychalex@gmail.com");
        userDao.save(user);
    }

    @GetMapping("{name}")
    public User getUserByName(@PathVariable("name") User user) {
        return user; }


    @PostMapping
    public void add(@RequestBody User user){
        userDao.save(user);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") User user){
        userDao.delete(user);
    }
}
