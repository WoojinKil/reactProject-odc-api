package com.odc.login.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odc.common.util.JwtUtil;
import com.odc.common.util.PasswordEncrptor;
import com.odc.login.dto.BoUsrDto;
import com.odc.login.dto.LoginRequestDto;
import com.odc.login.repository.LoginMapper;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginMapper loginMapper;
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	public Map<String, Object> loginProcess(LoginRequestDto loginRequestDto) {
		Map<String, Object> result = new HashMap<>();
		try {
		
			String loginResult = "S";
			BoUsrDto user =  loginMapper.selectByLgnId(loginRequestDto);
			if(user == null) {
				throw new RuntimeException("아이디가 존재하지 않습니다.");
			}
			if (!PasswordEncrptor.matches(loginRequestDto.getPwd(), user.getPwd())) {
			    throw new RuntimeException("비밀번호가 일치하지 않습니다.");
			}
			//로그인 성공시 토큰 생성
			
			String token = jwtUtil.createToken(loginRequestDto.getLgnId());

			
			result.put("accessToken", token);
			result.put("loginResult", loginResult);
			return result;
		} catch (Exception e) {
			 e.printStackTrace();
			 throw new RuntimeException("서버 에러");
		}
		
		
		
		
		
	}
}
