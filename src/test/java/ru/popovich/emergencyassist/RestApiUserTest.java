package ru.popovich.emergencyassist;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.popovich.emergencyassist.config.CustomAuthorizationServerConfigurer;
import ru.popovich.emergencyassist.config.SecurityConfig;
import ru.popovich.emergencyassist.controller.UserController;
import ru.popovich.emergencyassist.model.User;
import ru.popovich.emergencyassist.model.UserRole;
import ru.popovich.emergencyassist.repository.UserDao;
import ru.popovich.emergencyassist.service.UserService;

import java.util.Optional;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

//@AutoConfigureMockMvc
//@WebMvcTest(UserController.class)
@WebMvcTest(UserController.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@SpringBootTest
@RunWith(SpringRunner.class)
@Import(SecurityConfig.class) //https://springbootdev.com/2018/02/22/spring-boot-test-writing-unit-tests-for-the-controller-layers-with-webmvctest/
public class RestApiUserTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDao userDao;

    @InjectMocks
    private UserController userController;

    @MockBean
    private UserService userService;
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;

//    @LocalServerPort
//    private int port;

    User getUser;


    private String getRootUrl(){
        return "/api/v1/user";
//        return "http://localhost:" + port + "/api/v1/user";
    }

    @Before
    public void init(){
//        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
//        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        getUser = new User("hardup","12345678", UserRole.HARDUP);
        getUser.setLastname("Hardup");

        when(userDao.findById("hardup")).thenReturn(Optional.of(getUser));

        //building the mock response
        //https://springbootdev.com/2018/02/22/spring-boot-test-writing-unit-tests-for-the-controller-layers-with-webmvctest/


    }

    @Test
    public void addUser() throws Exception{

        assertNotNull(userDao.findById("hardup"));
        assertEquals(getUser.getNickname(), userDao.findById("hardup").get().getNickname());
        assertEquals(getUser.getLastname(), userDao.findById("hardup").get().getLastname());
        verify(userDao,times(3)).findById("hardup");

//        userDao.save(getUser);
//        verify(userDao, times(1)).save(getUser);

    }

    @Test
    public void addUser_thenReturnJson() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post(getRootUrl())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void givenUser_whenGetUser_thenReturnJsonArray() throws Exception {

        BDDMockito.given(userDao.findById("hardup")).willReturn(Optional.of(getUser));

        this.mockMvc.perform(MockMvcRequestBuilders.get(getRootUrl() + "/" + getUser.getNickname())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .with(httpBasic(getUser.getNickname(),getUser.getPassword()))
        )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.nickname", is(getUser.getNickname())))
                .andDo(print())
        ;

        verify(userDao, times(1)).findByNickname(getUser.getNickname());
    }
}

