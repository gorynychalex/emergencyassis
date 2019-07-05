package ru.popovich.emergencyassist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.popovich.emergencyassist.dbtest.SocialServiceGenerator;
import ru.popovich.emergencyassist.dbtest.TaskGenerator;
import ru.popovich.emergencyassist.dbtest.UserGenerator;
import ru.popovich.emergencyassist.dto.TaskSocialServiceIds;
import ru.popovich.emergencyassist.model.SocialService;
import ru.popovich.emergencyassist.model.TaskSocialService;
import ru.popovich.emergencyassist.model.User;
import ru.popovich.emergencyassist.repository.SocialServiceDao;
import ru.popovich.emergencyassist.repository.TaskSocialServiceDao;
import ru.popovich.emergencyassist.repository.UserDao;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    List<TaskSocialService> taskSocialServices = TaskGenerator.getInstance().getTaskSocialServices();

    @Autowired
    private TaskSocialServiceDao taskSocialServiceDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private SocialServiceDao socialServiceDao;

    @GetMapping
    public List<TaskSocialService> taskSocialServices() {
        return taskSocialServiceDao.findAll();
    }

    @GetMapping("{id}")
    public TaskSocialService getTaskById(@PathVariable("id") TaskSocialService taskSocialService) { return taskSocialService; }

    private TaskSocialService getTaskByIdPriv(String id){
        return taskSocialServices.stream()
                .filter(t->t.getId().equals(id))
                .findFirst().get();
    }

    @PostMapping
    public TaskSocialService addTask(@RequestBody TaskSocialService task){
        taskSocialServices.add(task);
        return task;
    }

    @PostMapping("/new")
    public TaskSocialService addTask(@RequestBody TaskSocialServiceIds taskSocialServiceIds){

        User user = userDao.findByNickname(taskSocialServiceIds.getUid());
        SocialService socialService = socialServiceDao.findById(taskSocialServiceIds.getSid()).get();

        TaskSocialService taskSocialService = new TaskSocialService(
                socialService,
                user,
                userDao.findByNickname(SecurityContextHolder.getContext().getAuthentication().getName())
        );

        taskSocialServices.add(taskSocialService);
        taskSocialServiceDao.save(taskSocialService);

        return taskSocialService;
    }
}
