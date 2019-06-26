package ru.popovich.emergencyassist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.popovich.emergencyassist.model.SocialService;

public interface SocialServiceDao extends JpaRepository<SocialService, Long> {

    SocialService findByTitle(String title);
}
