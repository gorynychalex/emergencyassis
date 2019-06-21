package ru.popovich.emergencyassist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v2/task")
public class SocialServiceTaskController {

    List<Map<String,String>> tasks = new ArrayList<Map<String, String>>(){{
        add(new HashMap<String, String>(){{ put("id","1"); put("name","Уборка"); }});
        add(new HashMap<String, String>(){{ put("id","2"); put("name","Продукты"); }});
        add(new HashMap<String, String>(){{ put("id","3"); put("name","ЖКХ оплата"); }});
    }};

    @GetMapping
    public List<Map<String, String>> taskList() {
        return tasks;
    }

}
