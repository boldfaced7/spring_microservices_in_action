package com.optimagrowth.license.repository;

import com.optimagrowth.license.model.License;

import java.util.List;
import java.util.Optional;

public interface LicenseRepository {
    public License save(License license);
    public void delete(License license);
    public List<License> findByOrganizationId(String organizationId);
    public Optional<License> findByOrganizationIdAndLicenseId(String organizationId, String licenseId);
}
