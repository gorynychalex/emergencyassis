package ru.popovich.emergencyassist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import ru.popovich.emergencyassist.model.TaskSocialService;
import ru.popovich.emergencyassist.model.User;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

public interface TaskSocialServiceDao extends JpaRepository<TaskSocialService, Long> {

    @Query("select t from TaskSocialService t where t.needy in :user or t.employee in :user")
    List<TaskSocialService> findTaskSocialServicesByUser(@Param("user") User user);

    @Query("select t from TaskSocialService t where t.needy in :unid and t.employee in :ueid")
    List<TaskSocialService> findTaskSocialServicesByUserNeedyUserEmployee(
            @Param("unid") User unid,
            @Param("ueid") User ueid
    );

    @Query("SELECT t FROM TaskSocialService t WHERE t.needy in :usr and t.dateCreate between :datestart and :datestop")
    List<TaskSocialService> findTaskSocialServiceByUserAndDateCreate(
            @Param("usr") User usr,
            @Param("datestart") @Temporal(TemporalType.DATE) Date datestart,
            @Param("datestop") @Temporal(TemporalType.DATE) Date datestop
    );

    @Query("SELECT t FROM TaskSocialService t WHERE t.needy in :usr and t.dateStart between :datestart and :datestop")
    List<TaskSocialService> findTaskSocialServiceByUserAndDateStart(
            @Param("usr") User usr,
            @Param("datestart") @Temporal(TemporalType.DATE) Date datestart,
            @Param("datestop") @Temporal(TemporalType.DATE) Date datestop
    );

    @Query("SELECT t FROM TaskSocialService t WHERE t.needy in :usr and t.dateStop between :datestart and :datestop")
    List<TaskSocialService> findTaskSocialServiceByUserAndDateStop(
            @Param("usr") User usr,
            @Param("datestart") @Temporal(TemporalType.DATE) Date datestart,
            @Param("datestop") @Temporal(TemporalType.DATE) Date datestop
    );

}