package ru.popovich.emergencyassist;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
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
import ru.popovich.emergencyassist.controller.ServiceController;
import ru.popovich.emergencyassist.model.Organization;
import ru.popovich.emergencyassist.repository.OrganizationDao;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@WebMvcTest
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class RestApiOrganizationTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrganizationDao organizationDao;

    @InjectMocks
    private ServiceController serviceController;

    @LocalServerPort
    private int port;

    Organization organization;

    private String getRootUrl() {
        return "/api/v1/organization";
    }

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(serviceController).build();

        organization = new Organization();
        organization.setId("10");
        organization.setName("ПЦСОН");

//        doReturn(organization).when(organizationDao).findById("10");


    }

    @Test
    public void givenOrganization_whenGetUser_thenReturnJsonArray() throws Exception {

        when(organizationDao.findById("10")).thenReturn(Optional.of(organization));

        BDDMockito.given(organizationDao.getOne("10")).willReturn(organization);


        mockMvc.perform(MockMvcRequestBuilders.get(getRootUrl() + "/" + "10")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.name", is(organization.getName())))
        ;

        verify(organizationDao, times(1)).findById(organization.getId());
    }
}