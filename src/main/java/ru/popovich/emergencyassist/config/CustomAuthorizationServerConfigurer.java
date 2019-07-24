package ru.popovich.emergencyassist.config;

//https://docs.spring.io/spring-security-oauth2-boot/docs/current/reference/html/boot-features-security-oauth2-authorization-server.html

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableAuthorizationServer
public class CustomAuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CorsConfigurationSource corsConfigurationSource;

    AuthenticationManager authenticationManager;

    public CustomAuthorizationServerConfigurer(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient("client")
                .authorizedGrantTypes("password")
                .secret(passwordEncoder.encode("secret"))
                .scopes("all")
                ;
    }

    //https://stackoverflow.com/questions/44625488/spring-security-cors-error-when-enable-oauth2
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .addTokenEndpointAuthenticationFilter(new CorsFilter(corsConfigurationSource)) // FIX 401 ERROR!!!
//                .checkTokenAccess("isAuthenticated()")
//                .allowFormAuthenticationForClients()
        ;
    }

    @Bean
    public TokenStore tokenStore(){
        return new InMemoryTokenStore();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
        ;


        //https://stackoverflow.com/questions/25136532/allow-options-http-method-for-oauth-token-request
//        if (corsProperties.getAllowedOrigins() != null) {
//            Map<String, CorsConfiguration> corsConfigMap = new HashMap<>();
//            Arrays.asList(corsProperties.getAllowedOrigins().split(",")).stream()
//                    .filter(StringUtils::isNotBlank).forEach(s -> {
//                CorsConfiguration config = new CorsConfiguration();
//                config.setAllowCredentials(true);
//                config.addAllowedOrigin(s.trim());
//                if (corsProperties.getAllowedMethods() != null) {
//                    config.setAllowedMethods(Arrays.asList(corsProperties.getAllowedMethods().split(",")));
//                }
//                if (corsProperties.getAllowedHeaders() != null) {
//                    config.setAllowedHeaders(Arrays.asList(corsProperties.getAllowedHeaders().split(",")));
//                }
//                // here the /oauth/token is used
//                corsConfigMap.put("/oauth/token", config);
//            });
//            endpoints.getFrameworkEndpointHandlerMapping()
//                    .setCorsConfigurationSource(corsConfigurationSource);
    }


}
