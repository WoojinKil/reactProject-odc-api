package com.odc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.odc.*.repository")
public class OdcApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OdcApiApplication.class, args);
	}

}
