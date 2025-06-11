package com.odc.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.odc.filter.JwtFilter;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000")  // 프론트엔드 URL
                .allowedMethods("*")  // 모든 HTTP 메서드 허용
                .allowedHeaders("*")
                .allowCredentials(true);
    }
    
    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter() {
        FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new JwtFilter()); // 사용할 필터 지정
        registrationBean.addUrlPatterns("/api/*");  // 이 경로로 들어오는 요청에만 적용
        registrationBean.setOrder(1);               // 필터 순서 지정 (낮을수록 먼저 실행)

        return registrationBean;
    }
}
