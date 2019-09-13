package ru.popovich.emergencyassist;

/**
 * https://reflectoring.io/spring-boot-data-jpa-test/
 *
 * Spring Data JPA Repository Testing using Spring Boot @DataJpaTest
 * https://www.javaguides.net/2018/09/spring-data-jpa-repository-testing-using-spring-boot-datajpatest.html
 *
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.popovich.emergencyassist.model.User;
import ru.popovich.emergencyassist.model.UserRole;
import ru.popovich.emergencyassist.repository.UserDao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserEntityRepositoryTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testSaveUser(){

        // First user
        User admin = new User("admin","13245678", UserRole.ADMIN);
        admin.setLastname("Admin");
        userDao.save(admin);

        // Second user
        User hardup = new User("hardupuser","12345678", UserRole.HARDUP);
        hardup.setLastname("Hardeup");
        userDao.save(hardup);

        // Find user from Repository
        User admin1 = userDao.findByNickname("admin");
        User hardup1 = userDao.findByNickname("hardupuser");

        // Test Repository NotNull
        assertThat(userDao).isNotNull();

        // Test User Objects NotNull
        assertNotNull(admin); assertNotNull(admin1);
        assertNotNull(hardup); assertNotNull(hardup1);

        // Test Equals Result
        assertEquals(admin1.getNickname(), admin.getNickname());
        assertEquals(admin1.getLastname(), admin.getLastname());
        assertEquals(admin1.getRole(), UserRole.ADMIN);
        assertEquals(hardup1.getNickname(), hardup.getNickname());
        assertEquals(hardup1.getLastname(), hardup.getLastname());
        assertEquals(hardup1.getRole(), UserRole.HARDUP);
    }

    @Test
    public void testDeleteUser(){
        User admin = new User("admin","13245678", UserRole.ADMIN);
        userDao.save(admin);
        userDao.delete(admin);
    }
}
