package smia.simpleapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

// 이 클래스를 스프링 부트 서비스의 진입점으로 스프링 부트에 지정
@SpringBootApplication
// 이 클래스의 코드를 Spring RestController로 노출하도록 스프링 부트에 지정
@RestController
// 이 애플리케이션에서 노출되는 모든 URL 앞에 /hello가 붙음
@RequestMapping(value="hello")
public class SimpleApplication {
	public static void main(String[] args) {
		SpringApplication.run(SimpleApplication.class, args);
	}
	// 경로 변수(@PathVariable)와 요청 변수(@RequestParam) 형식으로 두 개의 매개변수(firstName, lastName)를 받는 GET HTTP 엔드포인트를 URL에 노출
	@GetMapping(value="/{firstName}")
	public String helloGET(
			@PathVariable("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		return String.format("{\"message\":\"Hello %s %s\"}", firstName, lastName);
	}
	@PostMapping
	public String helloPOST(@RequestBody HelloRequest request) {
		return String.format("{\"message\":\"Hello %s %s\"}", request.getFirstName(), request.getLastName());
	}}
// 사용자가 전송한 JSON 구조체 필드를 저장
class HelloRequest{
	private String firstName;
	private String lastName;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}