package ru.popovich.emergencyassist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import ru.popovich.emergencyassist.model.User;
import ru.popovich.emergencyassist.service.UserService;

import java.util.Arrays;
import java.util.Map;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Order(SecurityProperties.IGNORED_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {




    @Value("${activedirectory.domain}")
    private String AD_DOMAIN;

    @Value("${activedirectory.url}")
    private String AD_URL;

    @Autowired
    private UserService userService;

//        @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//            http
//                    .httpBasic().disable()
//                    .anonymous().disable()
//                    .cors()
//                    .disable()
//                    .configurationSource(corsConfigurationSource())
//                    .and()
//                    .addFilterAfter(simpleCorsFilter, SimpleCorsFilter.class)
//                    .csrf().disable()
//                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                    .and()
//                    .authorizeRequests()
//                    .antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll()
//                    .antMatchers("/organization/**","/index.html","/js/**").permitAll()
//                .antMatchers("/admin")
//                .hasRole("ADMIN")
//                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
//                    .anyRequest()
//                    .authenticated()
//                    .and()
//                    .csrf().disable()
//                    .httpBasic()
//                    .realmName("myrealm")
//                    .and()
//                .formLogin()
//                .permitAll()
//                .and()
//                .csrf().disable()

//            .and()
//                    .csrf().disable()
//            .logout()
//            .permitAll()
//            ;
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .authenticationProvider(userDaoAuthenticationProvider());
//        auth
//                .userDetailsService(userService);
    }

    @Bean
    public DaoAuthenticationProvider userDaoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider =
                new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    /**
     *
     * https://medium.com/@dmarko484/spring-boot-active-directory-authentication-5ea04969f220
     * (https://medium.com/@viraj.rajaguru/how-to-use-spring-security-to-authenticate-with-microsoft-active-directory-1caff11c57f2)
     *
     * @return
     */
//    public AuthenticationManager authenticationManager(){
//        return new ProviderManager(Arrays.asList(activeDirectoryAuthenticationProvider()));
//    }
//
//    public AuthenticationProvider activeDirectoryAuthenticationProvider(){
//        ActiveDirectoryLdapAuthenticationProvider provider =
//                new ActiveDirectoryLdapAuthenticationProvider(AD_DOMAIN, AD_URL);
//        provider.setConvertSubErrorCodesToExceptions(true);
//        provider.setUseAuthenticationRequestCredentials(true);
//        return provider;
//    }

//
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

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .antMatchers(HttpMethod.OPTIONS, "/**");
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
