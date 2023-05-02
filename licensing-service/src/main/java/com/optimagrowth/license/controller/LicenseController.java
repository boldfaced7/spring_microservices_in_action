package com.optimagrowth.license.controller;
// WebMvcLinkBuilder는 컨트롤러 클래스에 대한 링크를 생성하는 유틸리티 클래스
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    @GetMapping("/{licenseId}")
    // 라이선스 데이터를 조회하는 GET 메서드
    public ResponseEntity<License> getLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId) {
        License license = licenseService.getLicense(licenseId, organizationId).orElse(new License());
        // add()는 RepresentationModel 클래스 메서드
        license.add(
                // linkTo() 메서드는 LicenseController 클래스를 검사해 루트 매핑을 얻음
                // methodOn() 메서드는 대상 메서드에 더미 호출을 수행해 메서드 매핑을 가져옴
                linkTo(methodOn(LicenseController.class).getLicense(organizationId, license.getLicenseId())).withSelfRel(),
                linkTo(methodOn(LicenseController.class).createLicense(license)).withRel("createLicense"),
                linkTo(methodOn(LicenseController.class).updateLicense(license)).withRel("updateLicense"),
                linkTo(methodOn(LicenseController.class).deleteLicense(license.getLicenseId())).withRel("deleteLicense")
        );
        // ResponseEntity로 전체 HTTP 응답을 표현
        return ResponseEntity.ok(license);
    }

    @PutMapping
    // 라이선스를 업데이트하는 PUT 메서드
    public ResponseEntity<License> updateLicense(@RequestBody License request) {
        // HTTP 요청 바디(내용)를 라이선스 객체로 매핑
        return ResponseEntity.ok(licenseService.updateLicense(request));
    }
    @PostMapping
    // 라이선스를 생성하는 POST 메서드
    public ResponseEntity<License> createLicense(@RequestBody License request) {
        return ResponseEntity.ok(licenseService.createLicense(request));
    }
    @DeleteMapping("/{licenseId}")
    // 라이선스를 삭제하는 DELETE 메서드
    public ResponseEntity<String> deleteLicense(@PathVariable("licenseId") String licenseId) {
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId));
    }
}
