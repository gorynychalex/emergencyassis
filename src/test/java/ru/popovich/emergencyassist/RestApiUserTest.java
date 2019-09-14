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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.popovich.emergencyassist.controller.UserController;
import ru.popovich.emergencyassist.model.User;
import ru.popovich.emergencyassist.model.UserRole;
import ru.popovich.emergencyassist.repository.UserDao;

import java.util.Optional;


import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@AutoConfigureMockMvc
//@WebMvcTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class RestApiUserTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDao userDao;

    @InjectMocks
    private UserController userController;

    @LocalServerPort
    private int port;

    User getUser;

    private String getRootUrl(){
        return "http://localhost:" + port + "/api/v1/user";
    }

    @Before
    public void init(){
//        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        getUser = new User("hardup","12345678", UserRole.HARDUP);
        getUser.setLastname("Hardup");
//        userDao.save(getUser);

        when(userDao.findById("hardup")).thenReturn(Optional.of(getUser));
    }

    @Test
    public void givenUser_whenGetUser_thenReturnJsonArray() throws Exception {

//        BDDMockito.given(userDao.getOne("hardup")).willReturn(getUser);

        mockMvc.perform(MockMvcRequestBuilders.get(getRootUrl() + "/" + getUser.getNickname())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.nickname", is(getUser.getNickname())))
                .andDo(print())
        ;
//
//        verify(userDao, times(1)).findByNickname(getUser.getNickname());

    }


}

