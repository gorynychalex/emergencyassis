package ru.popovich.emergencyassist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${activedirectory.domain}")
    private String AD_DOMAIN;

    @Value("${activedirectory.url}")
    private String AD_URL;

    @Autowired
    private CustomAuthProvider customAuthProvider;

    @Autowired
    private SsoAuthenticationProvider ssoAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(activeDirectoryAuthenticationProvider());
        auth
                .authenticationProvider(customAuthProvider);
        auth
                .authenticationProvider(ssoAuthenticationProvider);
    }

    /**
     *
     * https://medium.com/@dmarko484/spring-boot-active-directory-authentication-5ea04969f220
     * (https://medium.com/@viraj.rajaguru/how-to-use-spring-security-to-authenticate-with-microsoft-active-directory-1caff11c57f2)
     *
     * @return
     */
    public AuthenticationManager authenticationManager(){
        return new ProviderManager(Arrays.asList(activeDirectoryAuthenticationProvider()));
    }

    public AuthenticationProvider activeDirectoryAuthenticationProvider(){
        ActiveDirectoryLdapAuthenticationProvider provider =
                new ActiveDirectoryLdapAuthenticationProvider(AD_DOMAIN, AD_URL);
        provider.setConvertSubErrorCodesToExceptions(true);
        provider.setUseAuthenticationRequestCredentials(true);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
