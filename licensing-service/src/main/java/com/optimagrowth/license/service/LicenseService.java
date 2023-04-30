package com.optimagrowth.license.service;

import com.optimagrowth.license.model.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Locale;
import java.util.Random;

@Service
public class LicenseService {
    @Autowired
    MessageSource messages;
    public License getLicense(String licenseId, String organizationId) {
        License license = new License();
        license.setId(new Random().nextInt(1000));
        license.setLicenseId(licenseId);
        license.setOrganizationId(organizationId);
        license.setDescription("Software product");
        license.setProductName("Ostock");
        license.setLicenseType("full");
        return license;
    }
    // 메서드 매개변수로 로케일을 전달 받음
    public String createLicense(License license, String organizationId, Locale locale) {
        String responseMessage = null;
        if (!ObjectUtils.isEmpty(license)) {
            license.setOrganizationId(organizationId);
            // 특정 메시지를 조회하기 위해 전달된 로케일로 설정
            responseMessage = String.format(messages.getMessage(
                    "license.create.message", null, locale),
                    license.toString());
        }
        return responseMessage;
    }
    public String updateLicense(License license, String organizationId) {
        String responseMessage = null;
        if (!ObjectUtils.isEmpty(license)) {
            license.setOrganizationId(organizationId);
            // 특정 메시지를 조회하기 위해 NULL 로케일을 전달해, 디폴트 로케일을 사용
            responseMessage = String.format(messages.getMessage(
                    "license.update.message", null, null),
                    license.toString());
        }
        return responseMessage;
    }
    public String deleteLicense(String licenseId, String organizationId) {
        String responseMessage = null;
        responseMessage = String.format("Deleting license with id %s for the " +
                "organization %s", licenseId, organizationId);
        return responseMessage;
    }
}
