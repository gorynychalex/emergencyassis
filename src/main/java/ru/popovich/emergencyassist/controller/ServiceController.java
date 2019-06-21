package ru.popovich.emergencyassist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.popovich.emergencyassist.dbtest.SocialServiceGenerator;
import ru.popovich.emergencyassist.model.SocialService;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/service")
public class ServiceController {

    List<SocialService> socialDomesticServices = SocialServiceGenerator.getInstance().getSocialServices();

    @GetMapping
    public List<SocialService> listSocialService() { return socialDomesticServices; }

}
