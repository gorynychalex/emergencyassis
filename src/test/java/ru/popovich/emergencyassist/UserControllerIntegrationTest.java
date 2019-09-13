package ru.popovich.emergencyassist;
/**
 * https://www.javaguides.net/2018/09/spring-boot-2-rest-apis-integration-testing.html
 *
 * https://sysout.ru/testirovanie-spring-boot-prilozheniya-s-testresttemplate/
 */

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

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl(){
        return "http://localhost:" + port + "/api/v1/user";
    }

    @Test
    public void whenCreateUser_thenStatus201(){

        User hardup = new User("hardup1","12345678", UserRole.HARDUP);
        hardup.setLastname("Hardeup");
//        userDao.save(hardup);

        ResponseEntity<User> userResponseEntity = testRestTemplate.postForEntity(getRootUrl(), hardup, User.class);

        assertThat(userResponseEntity.getStatusCode(), is(HttpStatus.CREATED));
        assertEquals(userResponseEntity.getBody().getNickname(), hardup.getNickname());
        assertThat(userResponseEntity.getBody().getLastname(), is(hardup.getLastname()));

    }

    @Test
    public void testGetAllUsers(){

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = testRestTemplate.exchange(getRootUrl(), HttpMethod.GET, entity, String.class );

        assertNotNull(responseEntity.getBody());

        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));

    }

    @Test
    public void testGetUserByName(){
        String nickname = "popovich";
        User user = testRestTemplate.getForObject(getRootUrl()+"/" + nickname, User.class);
        System.out.println(user.getNickname());
        assertNotNull(user);
        assertEquals(user.getNickname(),nickname);
    }
}
