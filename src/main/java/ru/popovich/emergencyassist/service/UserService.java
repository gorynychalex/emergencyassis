package ru.popovich.emergencyassist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.popovich.emergencyassist.dbtest.UserGenerator;
import ru.popovich.emergencyassist.model.User;
import ru.popovich.emergencyassist.model.UserRole;
import ru.popovich.emergencyassist.repository.UserDao;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    List<User> users = UserGenerator.getInstance().getUsers();


//    @PostConstruct
    public void init() { users.forEach(userDao::save); }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDao.findByNickname(username);

        org.springframework.security.core.userdetails.User.UserBuilder userBuilder = null;

        if(user == null){
            throw new UsernameNotFoundException(username);
        }

        userBuilder = org.springframework.security.core.userdetails.User.withUsername(user.getNickname());
        userBuilder.disabled(!user.isEnable());
        userBuilder.password(passwordEncoder.encode(user.getPassword()));

        userBuilder.authorities(user
                .getRoles()
                .stream()
                .map(UserRole::name)
                .toArray(String[]::new)
        );

        return userBuilder.build();
    }
}
