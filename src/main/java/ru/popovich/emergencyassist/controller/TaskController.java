package ru.popovich.emergencyassist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
