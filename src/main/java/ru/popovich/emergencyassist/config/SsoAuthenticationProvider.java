package ru.popovich.emergencyassist.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ru.popovich.emergencyassist.model.User;
import ru.popovich.emergencyassist.repository.UserDao;

@Component
public class SsoAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDao userDao;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
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
