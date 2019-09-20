package ru.popovich.emergencyassist;

import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.cors.CorsConfigurationSource;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = EmergencyassistApplication.class)
@ActiveProfiles("mvc")
public class OAuthMvcTest {

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private CorsConfigurationSource corsConfigurationSource;

    private MockMvc mockMvc;

    private static final String CLIENT_ID = "client";
    private static final String CLIENT_SECRET = "secret";

    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    private static final String EMAIL = "popovich@pcson.ru";
    private static final String NAME = "popovich";

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
    }

    private String obtainAccessToken(String username, String password) throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", CLIENT_ID);
        params.add("username", username);
        params.add("password", password);

        // @formatter:off

        ResultActions result = mockMvc.perform(post("/oauth/token")
                               .params(params)
                .contentType(CONTENT_TYPE)
                               .with(httpBasic(CLIENT_ID, CLIENT_SECRET))
                               .accept(CONTENT_TYPE))
                               .andExpect(status().isOk())
                               .andExpect(content().contentType(CONTENT_TYPE))
                               ;
        
        // @formatter:on

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }

    @Test
    public void givenNoToken_whenGetSecureRequest_thenUnauthorized() throws Exception {
        mockMvc.perform(get("/api/v1/user").param("email", EMAIL)).andExpect(status().isUnauthorized());
    }

    @Test
    public void givenInvalidRole_whenGetSecureRequest_thenForbidden() throws Exception {
        final String accessToken = obtainAccessToken("popovich", "12345678");
        System.out.println("token:" + accessToken);
        mockMvc.perform(get("/api/v1/user").header("Authorization", "Bearer " + accessToken).param("email", EMAIL)).andExpect(status().isForbidden());
    }

    @Test
    public void givenToken_whenPostGetSecureRequest_thenOk() throws Exception {
        final String accessToken = obtainAccessToken("popovich", "12345678");

        String employeeString = "{\"email\":\"" + EMAIL + "\",\"name\":\"" + NAME + "\",\"age\":30}";

        // @formatter:off
        
        mockMvc.perform(post("/user")
                .header("Authorization", "Bearer " + accessToken)
                .contentType(CONTENT_TYPE)
                .content(employeeString)
                .accept(CONTENT_TYPE))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/user")
                .param("email", EMAIL)
                .header("Authorization", "Bearer " + accessToken)
                .accept(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(jsonPath("$.name", is(NAME)));
        
        // @formatter:on
    }

}