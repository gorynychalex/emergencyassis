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

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    List<TaskSocialService> taskSocialServices = TaskGenerator.getInstance().getTaskSocialServices();

    @GetMapping
    public List<TaskSocialService> taskSocialServices() {
        return taskSocialServices;
    }

    @GetMapping("{id}")
    public TaskSocialService getTaskById(@PathVariable String id) { return getTaskByIdPriv(id); }

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

        Long sid = taskSocialServiceIds.getSid(); String uid = taskSocialServiceIds.getUid();

        TaskSocialService taskSocialService = new TaskSocialService(
                String.valueOf(TaskGenerator.getInstance().getTaskSocialServices().size()+10),
                SocialServiceGenerator.getInstance().getSocialServices().stream().filter(s->s.getId().equals(sid)).findFirst().get(),
                UserGenerator.getInstance().getUsers().stream().filter(t->t.getNickname().equals(uid)).findFirst().get()
                );

        taskSocialServices.add(taskSocialService);

        return taskSocialService;
    }
}
