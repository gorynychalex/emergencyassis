package ru.popovich.emergencyassist.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.*;
import ru.popovich.emergencyassist.model.Organization;
import ru.popovich.emergencyassist.model.SocialService;
import ru.popovich.emergencyassist.model.SocialServiceCatalog;
import ru.popovich.emergencyassist.repository.OrganizationDao;
import ru.popovich.emergencyassist.repository.SocialServiceCatalogDao;
import ru.popovich.emergencyassist.repository.SocialServiceDao;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ServiceController {

    @Autowired
    private SocialServiceDao socialServiceDao;

    @Autowired
    private SocialServiceCatalogDao socialServiceCatalogDao;

    @GetMapping({"/service","/service/list"})
    public List<SocialService> listSocialServiceFromDao() { return socialServiceDao.findAll(); }

    @GetMapping("/service/{id}")
    public SocialService getServiceById(@PathVariable("id") SocialService socialService) { return socialService; }

    @PostMapping("/service")
    public SocialService addTaskDao(@RequestBody SocialService service){
        socialServiceDao.save(service);
        return service;
    }

    @PutMapping("/service/{id}")
    public SocialService update(@PathVariable("id") SocialService socialServiceFromDb,
                                @RequestBody SocialService socialService)
    {
        BeanUtils.copyProperties(socialService, socialServiceFromDb, "id");

        return socialServiceDao.save(socialServiceFromDb);
    }

    @DeleteMapping("/service/{id}")
    public void delete(@PathVariable("id") SocialService socialService){
        socialServiceDao.delete(socialService);
    }

    @GetMapping("/add")
    public void add(){
        socialServiceDao.save(new SocialService("Еще услуга", 145.98F));
    }

    //////////////////////////// CATALOG ////////////////////////////////

    @GetMapping("/catalog")
    public List<SocialServiceCatalog> catalogs(){
        return socialServiceCatalogDao.findAll();
    }

    @GetMapping("/catalog/{id}")
    public SocialServiceCatalog catalog(@PathVariable("id") SocialServiceCatalog socialServiceCatalog){
        return socialServiceCatalog;
    }

    @GetMapping({"/catalog/{cid}/service/{id}"})
    public SocialService getServiceFromCatalogById(
            @PathVariable("id") SocialService socialService){

        return socialService;
    }

}
