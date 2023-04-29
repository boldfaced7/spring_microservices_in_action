package com.optimagrowth.license.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 스프링 부트에 이 서비스는 REST 기반 서비스이며, 응답은 JSON으로 서비스 요청 및 자동으로 직렬화 및 역직렬화할 것이라고 지정
@RestController
// 이 클래스의 모든 HTTP 엔드포인트가 /v1/organization/{organizationId}/license에서 시작하도록 노출
@RequestMapping(value="v1/organization/{organizationId}/license")
public class LicenseController {

}
