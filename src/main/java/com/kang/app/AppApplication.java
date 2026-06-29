package com.kang.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication  //이 클래스가 Spring Boot 앱의 시작점
// 1. 자동설정 2. 컴포넌트 스캔 3. 설정 클래스 등록
public class AppApplication {

	public static void main(String[] args) {

		SpringApplication.run(AppApplication.class, args);
	}

}
