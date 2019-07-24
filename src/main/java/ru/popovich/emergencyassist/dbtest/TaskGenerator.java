package ru.popovich.emergencyassist.dbtest;

import ru.popovich.emergencyassist.model.SocialService;
import ru.popovich.emergencyassist.model.TaskSocialService;
import ru.popovich.emergencyassist.model.User;

import java.util.ArrayList;
import java.util.List;

public class TaskGenerator {

    private final List<TaskSocialService> taskSocialServices;

    private final List<SocialService> socialServices;

    private final List<User> users;

    private static TaskGenerator instance = new TaskGenerator();

    public TaskGenerator() {

        this.socialServices = SocialServiceGenerator.getInstance().getSocialServices();
        this.users = UserGenerator.getInstance().getUsers();

        this.taskSocialServices = new ArrayList<TaskSocialService>() {{
            add(new TaskSocialService(1L,socialServices.get(0),users.get(0),users.get(3)));
            add(new TaskSocialService(2L,socialServices.get(2),users.get(0),users.get(3)));
            add(new TaskSocialService(3L,socialServices.get(0),users.get(1),users.get(4)));
            add(new TaskSocialService(4L,socialServices.get(3),users.get(1),users.get(4)));
        }};
    }

    public static TaskGenerator getInstance(){ return instance; }

    public List<TaskSocialService> getTaskSocialServices() { return taskSocialServices; }

    public List<SocialService> getSocialServices() { return socialServices; }

    public List<User> getUsers() { return users; }


}
