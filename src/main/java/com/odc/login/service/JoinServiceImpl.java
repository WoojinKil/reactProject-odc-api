package com.odc.login.service;

import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.odc.common.util.EmailUtils;
import com.odc.common.util.PasswordEncrptor;
import com.odc.login.dto.BoUsrRequestDto;
import com.odc.login.dto.UsrIdRequestDto;
import com.odc.login.repository.JoinMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
    @Value("${spring.redis.data.host}")
    private String redisHost;

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
		System.out.println("code ::" + code);
	    System.out.println("redisHost::: "+ redisHost);
	    
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
	
	@Override
	public String selectVerifyEmailCode(Map<String, Object> map) {
		String email = (String)map.get("email");
		String key = "emailAuth:"+email;
		String inputCode = (String)map.get("code");
		String savedCode = redisTemplate.opsForValue().get(key);

	    if (savedCode != null && savedCode.equalsIgnoreCase(inputCode)) {
	        redisTemplate.delete(key); // 인증 성공하면 삭제
	        return "S";
	    } else {
	    	return "F";
	    }
		
	}


	@Override
	public String selectIdDupCheck(UsrIdRequestDto requestDto) {
		String usrId = requestDto.getUsrId();
		String idDupCheck = joinMapper.selectIdDupCheck(usrId);
		return idDupCheck;
	}


	@Override
	public void saveUser(BoUsrRequestDto requestDto) {
		String usrId = "";
		//usrId = lgnId 
		if(requestDto.getLgnId() != "") {
			usrId = requestDto.getLgnId();
			requestDto.setUsrId(usrId);
		}
		// 비밀번호 암호화
		// 기본 전부 활성화 및 사용 가능
		//LBMP001
		//1 우선 하드코딩
		// 추후 삭제 예정
		requestDto.setCstmId("LBMP001");
		requestDto.setTrgtSeq(1);
		
		
		
		requestDto.setActvYn("N");
		requestDto.setUseYn("Y");
		String oringinalPwd = requestDto.getPwd();
		String encryptedPwd = PasswordEncrptor.encryptSha256(oringinalPwd);
		String crtrId = requestDto.getLgnId();
		String updrId = requestDto.getLgnId();
		requestDto.setCrtrId(crtrId);
		requestDto.setUpdrId(updrId);
		
		log.info(encryptedPwd);
		requestDto.setPwd(encryptedPwd);
		joinMapper.insertBoUsr(requestDto);
		
		
		
		
	}
}
