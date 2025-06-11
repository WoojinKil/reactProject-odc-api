package com.odc.common.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	  // 비밀키 (실제로는 application.yml 등에 따로 분리해서 관리하는 것이 안전)
	private static final String SECRET = "my-super-secret-key-which-is-long-enough";

	// key 객체로 변환
	private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    // 토큰 만료 시간 (30분)
    private final long ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 30;

    // JWT 생성
    public String createToken(String subject) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + ACCESS_TOKEN_EXPIRATION);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key)
                .compact();
    }
    
	public static String validateToken(String token) {
        try {
        	System.out.println("[validateToken] token = " + token);

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            System.out.println("[validateToken] subject = " + claims.getSubject());

            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }
    // JWT에서 subject 추출
    public String getSubject(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
