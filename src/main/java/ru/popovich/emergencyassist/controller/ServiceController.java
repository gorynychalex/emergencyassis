package ru.popovich.emergencyassist.controller;

import org.springframework.web.bind.annotation.*;
import ru.popovich.emergencyassist.dbtest.SocialServiceGenerator;
import ru.popovich.emergencyassist.model.SocialService;
import ru.popovich.emergencyassist.model.TaskSocialService;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/service")
public class ServiceController {

    List<SocialService> socialServices = SocialServiceGenerator.getInstance().getSocialServices();

    @GetMapping
    public List<SocialService> listSocialService() { return socialServices; }


    @GetMapping("{id}")
    public SocialService getServiceById(@PathVariable String id) { return getServiceByIdPriv(id); }

    private SocialService getServiceByIdPriv(String id){
        return socialServices.stream()
                .filter(t->t.getId().equals(id))
                .findFirst().get();
    }

    @PostMapping
    public SocialService addTask(@RequestBody SocialService service){
        socialServices.add(service);
        return service;
    }
}
