package ru.popovich.emergencyassist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.popovich.emergencyassist.model.Organization;

public interface OrganizationDao extends JpaRepository<Organization, Long> {
}
