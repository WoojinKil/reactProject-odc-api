package com.odk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.odk.inf.repository")
public class OdkApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OdkApiApplication.class, args);
	}

}
