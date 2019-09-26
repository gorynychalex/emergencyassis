package ru.popovich.emergencyassist.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.popovich.emergencyassist.model.Organization;
import ru.popovich.emergencyassist.repository.OrganizationDao;

import java.util.List;

@RestController
@RequestMapping("/api/v1/organization")
public class OrganizationController {

    @Autowired
    private OrganizationDao organizationDao;


    @GetMapping({"list","s"})
    public List<Organization> organizationList(){ return organizationDao.findAll(); }

    @GetMapping
    public Organization organization() { return organizationDao.findAll().size()!=0? organizationDao.findAll().get(0) : null; }

    @GetMapping("{id}")
    public Organization getOrganization(@PathVariable("id") Organization organization){
        return organization;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Organization add(@RequestBody Organization organization){ return organizationDao.save(organization); }

    @PutMapping("{id}")
    public Organization editOrganization(@PathVariable("id") Organization organizationDb, @RequestBody Organization organization){
        BeanUtils.copyProperties(organization, organizationDb, "id");

        organization.getId();
        organizationDb.getId();

        return organizationDao.save(organizationDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Organization organization){ organizationDao.delete(organization); }

}
