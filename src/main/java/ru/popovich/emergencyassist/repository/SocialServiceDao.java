package ru.popovich.emergencyassist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.popovich.emergencyassist.model.SocialService;

public interface SocialServiceDao extends JpaRepository<SocialService, String> {
}
