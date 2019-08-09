package ru.popovich.emergencyassist;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.popovich.emergencyassist.controller.UserController;
import ru.popovich.emergencyassist.service.UserService;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController = new UserController();

    MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getUsers() throws Exception {

        userController.getSimpleUsers();

        mockMvc
                .perform(get("/api/v1/user/simple")
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .string("{\"nickname\":\"pupkin\",\"password\":\"12345678\",\"firstname\":null,\"middlename\":null,\"lastname\":null,\"enable\":true,\"email\":null,\"locale\":null,\"dateCreation\":null,\"sub\":null,\"dateEnable\":null,\"personal\":null,\"role\":\"HARDUP\",\"roles\":null,\"organization\":null,\"organizations\":null,\"authDateTime\":null,\"descriptions\":null,\"notes\":null,\"users\":null}")
//                        .string("{'nickname':'pupkin','password':'12345678','firstname':null,'middlename':null,'lastname':null,'enable':true,'email':null,'locale':null,'dateCreation':null,'sub':null,'dateEnable':null,'personal':null,'role':'HARDUP','roles':null,'organization':null,'organizations':null,'authDateTime':null,'descriptions':null,'notes':null,'users':null}")
                );
        verify(userService, times(2)).getSimpleUser();

    }
}
