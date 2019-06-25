package ru.popovich.emergencyassist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.popovich.emergencyassist.model.TaskSocialService;

public interface TaskSocialServiceDao extends JpaRepository<TaskSocialService, String> {
}
