package com.odc.login.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odc.common.util.Const;
import com.odc.login.dto.LoginRequestDto;
import com.odc.login.service.LoginService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author woojin kil
 *
 */
@RestController
@RequestMapping("/api")
@Slf4j
@Tag(name = "로그인 API", description = "로그인 관련 기능")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@Operation(summary = "로그인", description = "로그인 처리.")
	@PostMapping("/login")
	public ResponseEntity<?> sendEmailCode(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        String result = "";

        Map<String, Object>  loginResult = new HashMap <String, Object>();
        try {
			result = Const.BOOLEAN_SUCCESS;
			loginResult = loginService.loginProcess(loginRequestDto);
	        response.setHeader("Authorization", "Bearer " + loginResult.get("accessToken"));
		} catch (Exception e) {
			result = Const.BOOLEAN_FAIL;
			e.printStackTrace();
		}

        resultMap.put("result", result);
        resultMap.put("loginResult", loginResult);
        
        return ResponseEntity.ok(resultMap);
		
	}

	
	
}
