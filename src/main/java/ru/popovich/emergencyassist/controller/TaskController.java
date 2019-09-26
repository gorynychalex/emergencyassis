package ru.popovich.emergencyassist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.popovich.emergencyassist.dbtest.SocialServiceGenerator;
import ru.popovich.emergencyassist.dbtest.TaskGenerator;
import ru.popovich.emergencyassist.dbtest.UserGenerator;
import ru.popovich.emergencyassist.dto.TaskSocialServiceIds;
import ru.popovich.emergencyassist.model.TaskSocialService;
import ru.popovich.emergencyassist.model.User;
import ru.popovich.emergencyassist.repository.SocialServiceDao;
import ru.popovich.emergencyassist.repository.TaskSocialServiceDao;
import ru.popovich.emergencyassist.repository.UserDao;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

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
    public TaskSocialService getTaskById(@PathVariable("id") TaskSocialService taskSocialService) {
        return taskSocialService;
    }

    @PostMapping
    public void addTask(@RequestBody TaskSocialService task){
        taskSocialServiceDao.save(task);
    }

    @PostMapping("/new")
    public void addTask(@RequestBody TaskSocialServiceIds taskSocialServiceIds){

        Long sid = taskSocialServiceIds.getSid();
        String uid = taskSocialServiceIds.getUid();

        TaskSocialService taskSocialServiceDB = new TaskSocialService(
                socialServiceDao.findById(sid).get(),
                userDao.findByNickname(uid)
        );

        taskSocialServiceDao.save(taskSocialServiceDB);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") TaskSocialService taskSocialService){
        taskSocialServiceDao.delete(taskSocialService);
    }
}
