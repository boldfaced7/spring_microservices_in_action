package com.optimagrowth.license.service;

import com.optimagrowth.license.model.License;
import com.optimagrowth.license.repository.LicenseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LicenseService {
    @Autowired
    MessageSource messageSource;

    @Autowired
    private LicenseRepository licenseRepository;

    public Optional<License> getLicense(String licenseId, String organizationId) {
        Optional<License> license = licenseRepository.
                findByOrganizationIdAndLicenseId(organizationId, licenseId);
        return license;
    }
    public License createLicense(License license) {
        license.setLicenseId(UUID.randomUUID().toString());
        licenseRepository.save(license);
        return license;
    }
    public License updateLicense(License license) {
        licenseRepository.save(license);
        return license;
    }
    public String deleteLicense(String licenseId) {
        String responseMessage = null;
        License license = new License();
        license.setLicenseId(licenseId);
        licenseRepository.delete(license);

        responseMessage = String.format(messageSource.getMessage(
                "license.delete.message %s", null, null), licenseId);
        return responseMessage;
    }
}
