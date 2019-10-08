package ru.popovich.emergencyassist;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.popovich.emergencyassist.model.User;
import ru.popovich.emergencyassist.model.UserRole;
import ru.popovich.emergencyassist.repository.UserDao;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserEntityRepositoryTestFewUsers {

    @Autowired
    private UserDao userDao;

    @Test
    public void testSaveUser(){

        // First Hardup user
        User hardup1 = new User("hardup1","13245678", UserRole.HARDUP);
        hardup1.setLastname("Hardup1");
        User saveHardup1 = userDao.save(hardup1);

        // Second Hardup user
        User hardup2 = new User("hardup2","12345678", UserRole.HARDUP);
        hardup2.setLastname("Hardeup2");
        User saveHardup2 = userDao.save(hardup2);

        // First Employee user
        User employee1 = new User("employee1","13245678", UserRole.EMPLOYEE);
        employee1.setLastname("Employee1");
        employee1.setUsers(new ArrayList<User>(){{ add(hardup1); add(hardup2); }});
        User saveEmployee1 = userDao.save(employee1);

        // Second Employee user
        User employee2 = new User("employee2","12345678", UserRole.EMPLOYEE);
        employee2.setLastname("Employee2");
        employee2.setUsers(new ArrayList<User>(){{ add(hardup1); add(hardup2); }});
        User saveEmployee2 = userDao.save(employee2);

        // Test Repository NotNull
        assertThat(userDao).isNotNull();

        // Find user from Repository

        User getHardup1ById = userDao.findById(saveHardup1.getId()).get();
        User getHardup2ById = userDao.findById(saveHardup2.getId()).get();
        User getEmployee1ById = userDao.findById(saveEmployee1.getId()).get();
        User getEmployee2ById = userDao.findById(saveEmployee2.getId()).get();
//
//        User getEmployee1 = userDao.findByNickname(employee1.getNickname());
//        User getEmployee2 = userDao.findByNickname(employee2.getNickname());
//        User getHardup1 = userDao.findByNickname(hardup1.getNickname());
//        User getHardup2 = userDao.findByNickname(hardup2.getNickname());

        // Test User Objects NotNull
        assertNotNull(getEmployee1ById); assertNotNull(getEmployee2ById);
        assertNotNull(getHardup1ById); assertNotNull(getHardup2ById);

        // Test Equals Result
        assertEquals(getEmployee1ById.getNickname(), employee1.getNickname());
        assertEquals(getEmployee2ById.getLastname(), employee2.getLastname());
        assertEquals(getHardup1ById.getNickname(), hardup1.getNickname());
        assertEquals(getHardup2ById.getLastname(), hardup2.getLastname());

        assertEquals(getEmployee1ById.getUsers().get(0).getNickname(),employee1.getUsers().get(0).getNickname());
        assertEquals(getEmployee1ById.getUsers().get(1).getNickname(),employee1.getUsers().get(1).getNickname());
        assertEquals(getEmployee2ById.getUsers().get(0).getNickname(),employee2.getUsers().get(0).getNickname());
        assertEquals(getEmployee2ById.getUsers().get(1).getNickname(),employee2.getUsers().get(1).getNickname());

    }


}
