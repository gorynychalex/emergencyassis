package ru.popovich.emergencyassist.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.popovich.emergencyassist.model.User;

import java.util.Date;

public interface UserDao extends JpaRepository<User, Long> {

//    @Query("select lastname,usr_roles.roles from usr join usr_roles on usr.id=usr_roles.usr_id where usr.nickname=:nickname")

//    @Query("select u from User u left join u.roles where u.nickname = :name")
//    @EntityGraph(attributePaths = {"roles"})
    User findByNickname(String nickname);

    void deleteUserByNickname(String nickname);

//    @Query("select usr_id from usr_users where users_id = :nid")
//    Long findByIdAfterAndUsers(Long nid);
    User findByUsers(User user);

}