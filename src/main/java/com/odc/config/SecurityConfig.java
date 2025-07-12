package com.odc.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))  // ❌ CORS 비활성화 (필요하면 활성화 가능)
            .csrf(csrf -> csrf.disable())  //  CSRF 보호 비활성화 (이거 안 하면 POST/PUT 요청 차단됨)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**","/kafka/**",
                		"/swagger-ui/**", 
                        "/v3/api-docs/**", 
                        "/swagger-resources/**", 
                        "/webjars/**").permitAll()  //  API 요청 인증 없이 허용
                .anyRequest().authenticated()  //  그 외 요청은 인증 필요
            )
            .formLogin(login -> login.disable())  // 로그인 폼 비활성화
            .httpBasic(basic -> basic.disable());  // HTTP 기본 인증 비활성화

        return http.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000")); // 허용할 프론트엔드 주소
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
