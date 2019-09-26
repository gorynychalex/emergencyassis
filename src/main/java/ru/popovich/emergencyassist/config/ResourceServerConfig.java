package ru.popovich.emergencyassist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Map;

@Configuration
@EnableResourceServer
//@Order(2)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


    private static final String RESOURCE_ID = "resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID)
                .stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
//                .anonymous().disable()
//                .cors()
//                .configurationSource(corsConfigurationSource())
//                .and()
//                .addFilterAfter(simpleCorsFilter, CorsFilter.class)
//                .csrf()
//                .disable()
                .authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll()
//                .antMatchers( "/oauth/token").permitAll()
//                .and()
                .antMatchers("/api/v1/**").permitAll()
                .antMatchers("/api/v1/organization/**","/*","/js/**").permitAll()
                .antMatchers("/api/v1/task/**").permitAll()
//                .antMatchers("/api/**").authenticated()
                .anyRequest()
                .authenticated()
//                .fullyAuthenticated()
//                .and().httpBasic().realmName("myrealm")
                .and()
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
                .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler())
        ;
    }

    @Bean
//    @Order(1)
    protected CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.applyPermitDefaultValues();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

//    @Bean
//    public FilterRegistrationBean corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//
//        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//        bean.setOrder(0);
//        return bean;
//    }

//    @Autowired
//    @Bean
//    // used only because I want to setCookiePath to /, otherwise I can simply use
//    // http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//    public CsrfTokenRepository getCsrfTokenRepository() {
//        CookieCsrfTokenRepository tokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
//        tokenRepository.setHeaderName("X-XSRF-TOKEN"); // has already this name --> comment
//        tokenRepository.setCookiePath("/");
//        return tokenRepository;
//    }
}
