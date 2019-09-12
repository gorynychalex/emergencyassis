package ru.popovich.emergencyassist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.popovich.emergencyassist.model.User;

public interface UserDao extends JpaRepository<User, String> {

    User findByNickname(String nickname);
    void deleteUserByNickname(String nickname);

}
