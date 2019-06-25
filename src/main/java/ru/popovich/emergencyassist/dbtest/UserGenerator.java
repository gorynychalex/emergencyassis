package ru.popovich.emergencyassist.dbtest;

import ru.popovich.emergencyassist.model.User;
import ru.popovich.emergencyassist.model.UserRelation;
import ru.popovich.emergencyassist.model.UserRole;

import java.util.ArrayList;
import java.util.List;

public class UserGenerator {

    private static UserGenerator instance = new UserGenerator();

    private final List<User> users;

    public UserGenerator() {

        users = new ArrayList<User>() {
            {
                add(new User("pupkin", "12345678", UserRole.HARDUP));
                add(new User("ivanov", "12345678", UserRole.HARDUP));
                add(new User("sidorov", "12345678", UserRole.HARDUP));

                add(new User("peskov", "12345678", UserRole.EMPLOYEE));
                add(new User("buzova", "12345678", UserRole.EMPLOYEE));
                add(new User("putin", "12345678", UserRole.EMPLOYEE));
            }
        };

        //Add relations between Needy Users and Employee

        List<UserRelation> userHardUp00Relations = new ArrayList<UserRelation>() {{
            add(new UserRelation(users.get(3),true)); add(new UserRelation(users.get(4),false)); add(new UserRelation(users.get(5),false));
        }};
//        users.get(0).setUserRelations(userHardUp00Relations);

        List<UserRelation> userEmployee03Relations = new ArrayList<UserRelation>(){{
            add(new UserRelation(users.get(0),true)); add(new UserRelation(users.get(1),true));
        }};
//        users.get(3).setUserRelations(userEmployee03Relations);

        List<UserRelation> userEmployee04Relations = new ArrayList<UserRelation>(){{
            add(new UserRelation(users.get(2),true)); add(new UserRelation(users.get(1),false));
        }};
//        users.get(4).setUserRelations(userEmployee04Relations);
    }

    public static UserGenerator getInstance() { return instance; }

    public List<User> getUsers() { return users; }
}
