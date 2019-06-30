package ru.popovich.emergencyassist.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import ru.popovich.emergencyassist.model.User;
import ru.popovich.emergencyassist.repository.UserDao;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/","/login**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    /**
     * Added from
     * https://spring.io/guides/gs/authenticating-ldap/
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .ldapAuthentication()
                .userDnPatterns("cn={0},ou=ОФФИС,dc=pcson,dc=ru")
                .groupSearchBase("dc=pcson,dc=ru")
                .contextSource()
                .url("ldap://srv-dc.pcson.ru")
                .and()
                .passwordCompare()
                .passwordEncoder(new LdapShaPasswordEncoder())
                .passwordAttribute("samaccountname");
    }

    @Bean
    public PrincipalExtractor principalExtractor(UserDao userDao){
        return map -> {
            String name = (String) map.get("name");
            User user1 = userDao.findById(name).orElseGet(()->{

                User newUser = new User();

                newUser.setSub((String)map.get("sub"));
                newUser.setNickname((String)map.get("name"));
                newUser.setEmail((String)map.get("email"));


                return newUser;
            });

            userDao.save(user1);
            return user1;
        };

    }
}
