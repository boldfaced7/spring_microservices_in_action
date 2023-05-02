package com.optimagrowth.license.repository;

import com.optimagrowth.license.model.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface SpringDataJpaLicenseRepository extends JpaRepository<License, String>, LicenseRepository {
    public List<License> findByOrganizationId(String organizationId);
    public Optional<License> findByOrganizationIdAndLicenseId(String organizationId, String licenseId);

}
