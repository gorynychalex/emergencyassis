package ru.popovich.emergencyassist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.popovich.emergencyassist.model.TaskSocialService;
import ru.popovich.emergencyassist.model.User;

import java.util.List;

public interface TaskSocialServiceDao extends JpaRepository<TaskSocialService, Long> {

    @Query("select t from TaskSocialService t where t.needy in :user or t.employee in :user")
    List<TaskSocialService> findTaskSocialServicesByUser(@Param("user") User user);

    @Query("select t from TaskSocialService t where t.needy in :unid and t.employee in :ueid")
    List<TaskSocialService> findTaskSocialServicesByUserNeedyUserEmployee(
            @Param("unid") User unid,
            @Param("ueid") User ueid
    );

}