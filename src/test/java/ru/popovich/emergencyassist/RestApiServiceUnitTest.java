package ru.popovich.emergencyassist;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.popovich.emergencyassist.controller.ServiceController;
import ru.popovich.emergencyassist.model.SocialService;
import ru.popovich.emergencyassist.repository.SocialServiceDao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class RestApiServiceUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SocialServiceDao socialServiceDao;

    @InjectMocks
    private ServiceController serviceController;

    @MockBean
    private SocialService socialService;

    @Before
    public void init(){

//        mockMvc = MockMvcBuilders.standaloneSetup(serviceController).build();

        socialService = new SocialService("Test service", 143F, 153.5F);
        when(socialServiceDao.findByTitle("Test service")).thenReturn(socialService);
    }

    @Test
    public void checkService() throws Exception {
        assertNotNull(socialServiceDao.findByTitle(socialService.getTitle()));
        assertEquals(socialServiceDao.findByTitle(socialService.getTitle()).getTitle(), socialService.getTitle());
    }

    @Test
    public void givenService_whenGetService_thenReturnJson() throws Exception {

        SocialService socialService1 = socialServiceDao.findByTitle("Test service");

        mockMvc.perform(get("/api/v1/service").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                ;

    }

}
