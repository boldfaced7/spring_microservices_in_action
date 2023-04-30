package com.optimagrowth.license.controller;

import com.optimagrowth.license.model.License;
import com.optimagrowth.license.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

// 스프링 부트에 이 서비스는 REST 기반 서비스이며, 응답은 JSON으로 서비스 요청 및 자동으로 직렬화 및 역직렬화할 것이라고 지정
@RestController
// 이 클래스의 모든 HTTP 엔드포인트가 /v1/organization/{organizationId}/license에서 시작하도록 노출
@RequestMapping(value="v1/organization/{organizationId}/license")
public class LicenseController {
    @Autowired
    private LicenseService licenseService;

    // URL의 두 매개변수(organizationId, licenseId)를 @GetMapping 매개변수로 매핑
    @GetMapping(value = "/{licenseId}")
    // 라이선스 데이터를 조회하는 GET 메서드
    public ResponseEntity<License> getLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId) {
        License license = licenseService.getLicense(licenseId, organizationId);
        // ResponseEntity로 전체 HTTP 응답을 표현
        return ResponseEntity.ok(license);
    }

    @PutMapping
    // 라이선스를 업데이트하는 PUT 메서드
    public ResponseEntity<String> updateLicense(
            @PathVariable("organizationId") String organizationId,
            @RequestBody License request) {
        // HTTP 요청 바디(내용)를 라이선스 객체로 매핑
        return ResponseEntity.ok(licenseService.updateLicense(request, organizationId));
    }

    @PostMapping
    // 라이선스를 생성하는 POST 메서드
    public ResponseEntity<String> createLicense(
            @PathVariable("organizationId") String organizationId,
            @RequestBody License request,
            // 요청 헤더 값을 메서드 매개변수에 매핑
            @RequestHeader(value="Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(licenseService.createLicense(request, organizationId, locale));
    }

    @DeleteMapping(value = "/{licenseId}")
    // 라이선스를 삭제하는 DELETE 메서드
    public ResponseEntity<String> deleteLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId) {
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId, organizationId));
    }
}
