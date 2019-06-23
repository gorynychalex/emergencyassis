package ru.popovich.emergencyassist.controller;

import org.springframework.web.bind.annotation.*;
import ru.popovich.emergencyassist.dbtest.TaskGenerator;
import ru.popovich.emergencyassist.model.TaskSocialService;

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
}
