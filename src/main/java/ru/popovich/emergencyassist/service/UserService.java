package ru.popovich.emergencyassist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.popovich.emergencyassist.dbtest.UserGenerator;
import ru.popovich.emergencyassist.model.User;
import ru.popovich.emergencyassist.repository.UserDao;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    List<User> users = UserGenerator.getInstance().getUsers();


//    @PostConstruct
    public void init() {

        users.forEach(userDao::save);

    }

}
