package ru.popovich.emergencyassist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.popovich.EmergencyAssistant.model.SocialService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/service")
public class ServiceController {

    public List<SocialService> socialDomesticServices = new ArrayList<SocialService>(){{
        add(new SocialService("1", "Покупка продуктов",143.50F));
        add(new SocialService("2", "Приготовление пищи",143.50F));
        add(new SocialService("3", "Оплата ЖКХ",71.77F));
        add(new SocialService("4", "Уборка",287.09F));
    }};

    @GetMapping
    public List<SocialService> listSocialService() { return socialDomesticServices; }

    @GetMapping("info")
    public String getInfo(){
        return "Rest service";
    }

}
