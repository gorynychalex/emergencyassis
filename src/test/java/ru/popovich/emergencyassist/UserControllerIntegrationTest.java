package ru.popovich.emergencyassist;
/**
 * Integration Test
 * User Controller
 *
 * https://www.javaguides.net/2018/09/spring-boot-2-rest-apis-integration-testing.html
 *
 * https://sysout.ru/testirovanie-spring-boot-prilozheniya-s-testresttemplate/
 */

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import ru.popovich.emergencyassist.model.User;
import ru.popovich.emergencyassist.model.UserRole;
import ru.popovich.emergencyassist.repository.UserDao;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserDao userDao;

    @LocalServerPort
    private int port;

    String nickname = "hardup";

    User hardup, getUser;

    private String getRootUrl(){
        return "http://localhost:" + port + "/api/v1/user";
    }

    @After
    public void resetDb() {
        if(userDao.findByNickname(nickname) != null)
            userDao.delete(userDao.findByNickname(nickname));
    }

    public void createTestUser(){

        if(userDao.findByNickname(nickname) == null) {
            hardup = new User("hardup", "12345678", UserRole.HARDUP);
            hardup.setLastname("Hardeup");
            userDao.save(hardup);
        }
    }

    @Test
    public void whenCreateUser_thenStatus201(){

        createTestUser();

        ResponseEntity<User> userResponseEntity = testRestTemplate.postForEntity(getRootUrl(), hardup, User.class);

        assertThat(userResponseEntity.getStatusCode(), is(HttpStatus.CREATED));
        assertEquals(userResponseEntity.getBody().getNickname(), hardup.getNickname());
        assertThat(userResponseEntity.getBody().getLastname(), is(hardup.getLastname()));

    }

    @Test
    public void testGetAllUsers(){

        createTestUser();

//        HttpHeaders headers = new HttpHeaders();
//
//        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<List<User>> responseEntity = testRestTemplate.exchange(getRootUrl(), HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>(){});

        assertNotNull(responseEntity.getBody());

        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));

    }

    @Test
    public void testGetAllUsersToString(){

        createTestUser();

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = testRestTemplate.exchange(getRootUrl(), HttpMethod.GET, entity, String.class );

        responseEntity.getBody();

        assertNotNull(responseEntity.getBody());

        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
    }


    @Test
    public void testGetUserByName(){

        createTestUser();

//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<User> entityUser = new HttpEntity<>(null, headers);

        User getUserDao = userDao.findByNickname(nickname);

        User getUser = testRestTemplate.getForObject(getRootUrl()+"/{id}", User.class, getUserDao.getId());

        assertNotNull(getUser);

        assertEquals(getUser.getNickname(),nickname);
    }

    @Test
    public void testDeleteUserByName(){

        createTestUser();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> entityUser = new HttpEntity<>(null, headers);

        User getUserDao = userDao.findByNickname(nickname);

        ResponseEntity<User> responseEntity = testRestTemplate.exchange(getRootUrl()+"/{id}", HttpMethod.DELETE, entityUser, User.class, getUserDao.getId());

        assertNotNull(responseEntity);

    }
}
