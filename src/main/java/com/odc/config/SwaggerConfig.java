package com.odc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI customOpenAPI() {
	
		return new OpenAPI()
	            .info(new Info()
	                .title("ODC API 문서")
	                .version("1.0")
	                .description("TCG 시스템의 백엔드 API 문서입니다."));
	}
}
