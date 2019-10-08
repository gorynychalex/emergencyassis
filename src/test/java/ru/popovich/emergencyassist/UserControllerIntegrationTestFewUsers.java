package ru.popovich.emergencyassist;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import ru.popovich.emergencyassist.model.User;
import ru.popovich.emergencyassist.model.UserRole;
import ru.popovich.emergencyassist.repository.UserDao;

import java.util.ArrayList;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;


/**
 * Create 2 users
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTestFewUsers {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserDao userDao;

    @LocalServerPort
    private int port;

    String userHardup1Nick = "hardup1", userHardup2Nick = "hardup2";
    String userEmployee1Nick = "employee1", userEmployee2Nick = "employee2";

    User userHardup1, saveHardup1, getUserHardup1, userHardup2, saveHardup2, getUserHardup2;
    User userEmployee1, saveEmployee1, getUserEmployee1, userEmployee2, saveEmployee2, getUserEmployee2;

    private String getRootUrl(){
        return "http://localhost:" + port + "/api/v1/user";
    }

    public void createTestUsers(){

        if(userDao.findByNickname(userHardup1Nick) == null) {
            userHardup1 = new User(userHardup1Nick, "12345678", UserRole.HARDUP);
            userHardup1.setLastname("Hardup1");
            saveHardup1 = userDao.save(userHardup1);
            saveHardup1.getNickname();
        }

        if(userDao.findByNickname(userHardup2Nick) == null) {
            userHardup2 = new User(userHardup2Nick, "12345678", UserRole.HARDUP);
            userHardup2.setLastname("Hardup2");
            saveHardup2 = userDao.save(userHardup2);
        }

        if(userDao.findByNickname(userEmployee1Nick) == null) {
            userEmployee1 = new User(userEmployee1Nick, "12345678", UserRole.EMPLOYEE);
            userEmployee1.setLastname("Employee1");
            userEmployee1.setUsers(new ArrayList<User>(){{ add(userHardup1); add(userHardup2); }});
            userDao.save(userEmployee1);
        }

        if(userDao.findByNickname(userEmployee2Nick) == null) {
            userEmployee2 = new User(userEmployee2Nick, "12345678", UserRole.EMPLOYEE);
            userEmployee2.setLastname("Employee2");
            userEmployee2.setUsers(new ArrayList<User>(){{ add(userHardup1); add(userHardup2); }});
            userDao.save(userEmployee2);
            userEmployee2.getUsers();
        }

    }

    @After
    public void resetDb() {
        if(userDao.findByNickname(userEmployee1Nick) != null) userDao.delete(userDao.findByNickname(userEmployee1Nick));
        if(userDao.findByNickname(userEmployee2Nick) != null) userDao.delete(userDao.findByNickname(userEmployee2Nick));
        if(userDao.findByNickname(userHardup1Nick) != null) userDao.delete(userDao.findByNickname(userHardup1Nick));
        if(userDao.findByNickname(userHardup2Nick) != null) userDao.delete(userDao.findByNickname(userHardup2Nick));
    }



    @Test
    public void testGetUserByName(){

        createTestUsers();

//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<User> entityUser = new HttpEntity<>(null, headers);

        User getDaoUserHardup1 = userDao.findByNickname(userHardup1Nick);
        User getDaoUserHardup2 = userDao.findByNickname(userHardup2Nick);
        User getDaoUserEmployee1 = userDao.findByNickname(userEmployee1Nick);
        User getDaoUserEmployee2 = userDao.findByNickname(userEmployee2Nick);

        User getRestUserHardup1 = testRestTemplate.getForObject(getRootUrl()+"/{id}", User.class, getDaoUserHardup1.getId());
        User getRestUserHardup2 = testRestTemplate.getForObject(getRootUrl()+"/{id}", User.class, getDaoUserHardup2.getId());
        User getRestUserEmployee1 = testRestTemplate.getForObject(getRootUrl()+"/{id}", User.class, getDaoUserEmployee1.getId());
        User getRestUserEmployee2 = testRestTemplate.getForObject(getRootUrl()+"/{id}", User.class, getDaoUserEmployee2.getId());

        assertNotNull(getRestUserHardup1);
        assertNotNull(getRestUserHardup2);
        assertNotNull(getRestUserEmployee1);
        assertNotNull(getRestUserEmployee2);

        assertNotNull(getRestUserEmployee1.getUsers());
        assertNotNull(getRestUserEmployee2.getUsers());

        assertEquals(getRestUserHardup1.getNickname(),userHardup1Nick);
        assertEquals(getRestUserHardup2.getNickname(),userHardup2Nick);
        assertEquals(getRestUserEmployee1.getNickname(),userEmployee1Nick);
        assertEquals(getRestUserEmployee2.getNickname(),userEmployee2Nick);


        assertEquals(getRestUserEmployee1.getUsers().get(0).getNickname(), userEmployee1.getUsers().get(0).getNickname());
        assertEquals(getRestUserEmployee2.getUsers().get(0).getNickname(), userEmployee2.getUsers().get(0).getNickname());
    }

}
