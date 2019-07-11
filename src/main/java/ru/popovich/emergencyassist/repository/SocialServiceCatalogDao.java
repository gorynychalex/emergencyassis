package ru.popovich.emergencyassist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.popovich.emergencyassist.model.SocialServiceCatalog;

public interface SocialServiceCatalogDao extends JpaRepository<SocialServiceCatalog, Long> {
}
