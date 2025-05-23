package com.odk.login.service;

import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.odk.common.util.EmailUtils;
import com.odk.login.repository.JoinMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoinServiceImpl implements JoinService {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private JoinMapper joinMapper; 
	@Value("${app.views.url}")
	private String boUrl;
    @Autowired
    private EmailUtils emailUtils;
    
	@Override
	public String selectEmailDupCheck(Map<String, Object> map) {
		String email = "";
		email = (String)map.get("email");
		String dupCheck = joinMapper.selectEmailDupCheck(email);
		return dupCheck;
		
		
	}
	
	
	@Override
	public void sendEmailCode(Map<String, Object> map) {
		String email = (String) map.get("email");
		String code = generateAuthCode();
		saveCodeToRedis(email, code);
		try {
			String subject = "이메일 인증코드입니다.";
			String url = boUrl;
			String domain = "http://okc.com";
			
			String text = EmailUtils.getEmailVerificationContents("이메일 인증코드", code);
			emailUtils.sendMimeMessage(email, subject, text);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public String generateAuthCode() {
	    int length = 6;
	    String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    StringBuilder sb = new StringBuilder();

	    Random random = new SecureRandom();
	    for (int i = 0; i < length; i++) {
	        sb.append(chars.charAt(random.nextInt(chars.length())));
	    }

	    return sb.toString();
	}
	public void saveCodeToRedis(String email, String authCode) {
	    String key = "emailAuth:" + email;
	    redisTemplate.opsForValue().set(key, authCode, 5, TimeUnit.MINUTES);
	}
}
