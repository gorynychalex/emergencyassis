package ru.popovich.emergencyassist.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Example from
 * https://www.baeldung.com/spring-security-multiple-auth-providers
 *
 */

public class CustomAuthProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String name = authentication.getName();
        String pass = authentication.getCredentials().toString();


        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
