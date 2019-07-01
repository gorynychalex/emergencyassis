package ru.popovich.emergencyassist.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${activedirectory.domain}")
    private String AD_DOMAIN;

    @Value("${activedirectory.url}")
    private String AD_URL;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
        ;
//        http
//                .authorizeRequests()
//                .mvcMatchers("/","/login**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(activeDirectoryAuthenticationProvider());
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
        ActiveDirectoryLdapAuthenticationProvider provider = new ActiveDirectoryLdapAuthenticationProvider(AD_DOMAIN, AD_URL);
        provider.setConvertSubErrorCodesToExceptions(true);
        provider.setUseAuthenticationRequestCredentials(true);
        return provider;
    }

    /**
     * Added from
     * https://spring.io/guides/gs/authenticating-ldap/
     * @param auth
     * @throws Exception
     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .ldapAuthentication()
//                .userDnPatterns("cn={0},ou=ОФФИС,dc=pcson,dc=ru")
//                .groupSearchBase("dc=pcson,dc=ru")
//                .contextSource()
//                .url("ldap://srv-dc.pcson.ru")
//                .and()
//                .passwordCompare()
//                .passwordEncoder(new LdapShaPasswordEncoder())
//                .passwordAttribute("samaccountname");
//    }



//    @Bean
//    public PrincipalExtractor principalExtractor(UserDao userDao){
//        return map -> {
//            String name = (String) map.get("name");
//            User user1 = userDao.findById(name).orElseGet(()->{
//
//                User newUser = new User();
//
//                newUser.setSub((String)map.get("sub"));
//                newUser.setNickname((String)map.get("name"));
//                newUser.setEmail((String)map.get("email"));
//
//
//                return newUser;
//            });
//
//            userDao.save(user1);
//            return user1;
//        };
//
//    }
}
