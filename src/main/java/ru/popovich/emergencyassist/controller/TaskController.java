package ru.popovich.emergencyassist.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.popovich.emergencyassist.dto.TaskSocialServiceIds;
import ru.popovich.emergencyassist.model.SocialService;
import ru.popovich.emergencyassist.model.TaskSocialService;
import ru.popovich.emergencyassist.model.User;
import ru.popovich.emergencyassist.repository.SocialServiceDao;
import ru.popovich.emergencyassist.repository.TaskSocialServiceDao;
import ru.popovich.emergencyassist.repository.UserDao;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    @Autowired
    private TaskSocialServiceDao taskSocialServiceDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private SocialServiceDao socialServiceDao;

    @GetMapping({"","/","/list"})
    public List<TaskSocialService> taskSocialServices() {
        return taskSocialServiceDao.findAll();
    }

    @GetMapping("{id}")
    public TaskSocialService getTaskById(@PathVariable("id") TaskSocialService taskSocialService) {
        return taskSocialService;
    }

    @GetMapping("/list/user/{uid}")
    public List<TaskSocialService> getTasksByUserId(@PathVariable("uid") User user){
        return taskSocialServiceDao.findTaskSocialServicesByUser(user);
    }

    @GetMapping("/list/needy/{unid}/employee/{ueid}")
    public List<TaskSocialService> getTasksByUserId(@PathVariable("unid") User userNeedy, @PathVariable("ueid") User userEmployee){
        return taskSocialServiceDao.findTaskSocialServicesByUserNeedyUserEmployee(userNeedy, userEmployee);
    }

//    @GetMapping("/list/needy/{unid}/datecreate/{date}")
//    public List<TaskSocialService> getTasksByUseridAndDateCreate(
//            @NotNull @PathVariable("unid") User userNeedy,
//            @PathVariable("date") @DateTimeFormat(pattern = "yyyyMMdd") Date date){
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//
//        String d = simpleDateFormat.format(date);
//
//        long oneday=24*60*60*1000;
//
//        Date date1 = new Date(date.getTime() + oneday);
//
//        String d2 = simpleDateFormat.format(date1);
//
//        return taskSocialServiceDao.findTaskSocialServiceByUserAndDateCreate(userNeedy, date, date1);
//    }

    @GetMapping({"/list/needy/{unid}/{datespec}/{datefrom}/{dateto}","/list/needy/{unid}/{datespec}/{datefrom}"})
    public List<TaskSocialService> getTasksByUseridAndDateCreatePeriod(
            @NotNull @PathVariable("unid") User userNeedy,
            @NotNull @PathVariable("datespec") String dateSpec,
            @NotNull @PathVariable("datefrom") @DateTimeFormat(pattern = "yyyyMMdd") Date dateFrom,
            @PathVariable(value = "dateto", required = false) @DateTimeFormat(pattern = "yyyyMMdd") Date dateTo
    ){

        switch (dateSpec) {
            case "datestart":
                if(dateTo == null)
                    return taskSocialServiceDao.findTaskSocialServiceByUserAndDateStart(userNeedy, dateFrom, new Date(dateFrom.getTime() + (long)(24*60*60*1000)));
                return taskSocialServiceDao.findTaskSocialServiceByUserAndDateStart(userNeedy, dateFrom, dateTo);
            case "datestop":
                if(dateTo == null)
                    return taskSocialServiceDao.findTaskSocialServiceByUserAndDateStop(userNeedy, dateFrom, new Date(dateFrom.getTime() + (long)(24*60*60*1000)));
                return taskSocialServiceDao.findTaskSocialServiceByUserAndDateStop(userNeedy, dateFrom, dateTo);
            default:
                if(dateTo == null)
                    return taskSocialServiceDao.findTaskSocialServiceByUserAndDateCreate(userNeedy, dateFrom, new Date(dateFrom.getTime() + (long)(24*60*60*1000)));
                return taskSocialServiceDao.findTaskSocialServiceByUserAndDateCreate(userNeedy, dateFrom, dateTo);
        }
    }

    @GetMapping("/list/needy/{unid}/datestart/from/{datestart}/to/{datestop}")
    public List<TaskSocialService> getTasksByUseridAndDateStartPeriod(
            @NotNull @PathVariable("unid") User userNeedy,
            @PathVariable("datestart") @DateTimeFormat(pattern = "yyyyMMdd") Date dateStart,
            @PathVariable("datestop") @DateTimeFormat(pattern = "yyyyMMdd") Date dateStop
    ){ return taskSocialServiceDao.findTaskSocialServiceByUserAndDateStart(userNeedy, dateStart, dateStop); }

    @GetMapping("/list/needy/{unid}/datestop/from/{datestart}/to/{datestop}")
    public List<TaskSocialService> getTasksByUseridAndDateStopPeriod(
            @NotNull @PathVariable("unid") User userNeedy,
            @PathVariable("datestart") @DateTimeFormat(pattern = "yyyyMMdd") Date dateStart,
            @PathVariable("datestop") @DateTimeFormat(pattern = "yyyyMMdd") Date dateStop
    ){ return taskSocialServiceDao.findTaskSocialServiceByUserAndDateStop(userNeedy, dateStart, dateStop); }


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

    // Create NEW task by User Id & Service Id
    @GetMapping("/new/needy/{userid}/service/{serviceid}")
    public TaskSocialService addTask(
            @NotNull  @PathVariable("userid") User userNeedy,
            @NotNull @PathVariable("serviceid") SocialService socialService){

        // Get Employee User by Needy User
        User userEmployee=userDao.findByUsers(userNeedy);

//
        if( userEmployee != null ) {
            return taskSocialServiceDao.save(new TaskSocialService(socialService, userNeedy, userEmployee));

        }

        return taskSocialServiceDao.save(new TaskSocialService(socialService, userNeedy));
    }

    // Create NEW task by User Id & Service Id & Employee Id
    @GetMapping("/new/needy/{userid}/service/{serviceid}/employee/{employeeid}")
    public TaskSocialService addTask(
            @PathVariable("userid") User userNeedy,
            @PathVariable("serviceid") SocialService socialService,
            @PathVariable("employeeid") User userEmployee
    ){
        return taskSocialServiceDao.save(new TaskSocialService(socialService, userNeedy, userEmployee));
    }

    @PutMapping("{id}")
    public TaskSocialService update(@PathVariable("id") TaskSocialService taskSocialServiceInit,
                       @RequestBody TaskSocialService taskSocialService){
        BeanUtils.copyProperties(taskSocialService, taskSocialServiceInit, "id","socialService");

        return taskSocialServiceDao.save(taskSocialServiceInit);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") TaskSocialService taskSocialService){
        taskSocialServiceDao.delete(taskSocialService);
    }
}
