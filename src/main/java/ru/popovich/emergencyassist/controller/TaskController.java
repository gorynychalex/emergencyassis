package ru.popovich.emergencyassist.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.popovich.emergencyassist.dto.TaskSocialServiceIds;
import ru.popovich.emergencyassist.model.TaskSocialService;
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
    public TaskSocialService addTask(@RequestBody TaskSocialServiceIds taskSocialServiceIds){

        Long sid = taskSocialServiceIds.getSid();
        Long userHardupId = taskSocialServiceIds.getUserHardupId();
        Long userEmployeeId = taskSocialServiceIds.getUserEmployeeId();

        TaskSocialService taskSocialServiceDB =
                new TaskSocialService(
                        socialServiceDao.findById(sid).get(),
                        userDao.findById(userHardupId).get(),
                        userDao.findById(userEmployeeId).get()
        );

        return taskSocialServiceDao.save(taskSocialServiceDB);
    }

    @PutMapping("{id}")
    public TaskSocialService update(@PathVariable("id") TaskSocialService taskSocialServiceInit,
                       @RequestBody TaskSocialService taskSocialService){
        BeanUtils.copyProperties(taskSocialService, taskSocialServiceInit, "id");

        return taskSocialServiceDao.save(taskSocialServiceInit);
    }


    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") TaskSocialService taskSocialService){
        taskSocialServiceDao.delete(taskSocialService);
    }
}
