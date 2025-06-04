package com.odc.login.comtroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odc.common.util.Const;
import com.odc.login.dto.BoUsrRequestDto;
import com.odc.login.dto.UsrIdRequestDto;
import com.odc.login.service.JoinService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 * @author woojin kil
 *
 */
@RestController
@RequestMapping("/api")
@Slf4j
@Tag(name = "회원가입 API", description = "회원가입 관련 기능")
public class JoinController {

	@Autowired
	JoinService joinService;
	
	@Operation(summary = "이메일 송신", description = "대상 이메일을 송신합니다.")
	@PostMapping("/join/sendEmailCode")
	public ResponseEntity<?> sendEmailCode(@RequestBody Map<String, Object> map) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        String result = "";
 
        try {
			result = Const.BOOLEAN_SUCCESS;
			joinService.sendEmailCode(map);
			
		} catch (Exception e) {
			result = Const.BOOLEAN_FAIL;
			e.printStackTrace();
		}
        
        resultMap.put("result", result);
        return ResponseEntity.ok(resultMap);
		
	}
	
	
	
	/**
	 * 이메일 중복체크
	 * @param map
	 * @return
	 */
	@Operation(summary = "이메일 중복체크", description = "이메일을 중복체크 합니다.")
	@PostMapping("/join/selectEmailDupCheck")
	public ResponseEntity<?> selectEmailDupCheck(@RequestBody Map<String, Object> map) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        String result = "";
        String emailDupCheck = "";
        try {
			result = Const.BOOLEAN_SUCCESS;
			emailDupCheck = joinService.selectEmailDupCheck(map);
			
		} catch (Exception e) {
			result = Const.BOOLEAN_FAIL;
			e.printStackTrace();
		}
        
        resultMap.put("emailDupCheck", emailDupCheck);
        resultMap.put("result", result);
        return ResponseEntity.ok(resultMap);
	}
	

	@Operation(summary = "인증메일 코드 비교", description = "인증메일로 발급된 코드를 비교합니다.")
	@PostMapping("/join/selectVerifyEmailCode")
	public ResponseEntity<?> selectVerifyEmailCode(@RequestBody Map<String, Object> map) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        String result = "";
        String currect = "";
        try {
			result = Const.BOOLEAN_SUCCESS;
			currect = joinService.selectVerifyEmailCode(map);
			
		} catch (Exception e) {
			result = Const.BOOLEAN_FAIL;
			currect = "F";
			e.printStackTrace();
		}
        resultMap.put("currectResult", currect);
        resultMap.put("result", result);
        return ResponseEntity.ok(resultMap);
		
	}
	
	
	
	/**
	 * 아이디 중복체크
	 * @param map
	 * @return
	 */
	@Operation(summary = "아이디 중복체크", description = "아이디를 중복체크 합니다.")
	@PostMapping("/join/selectIdDupCheck")
	public ResponseEntity<?> selectIdDupCheck(@RequestBody UsrIdRequestDto requestDto) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        String result = "";
        String idDupCheck = "";
        try {
			result = Const.BOOLEAN_SUCCESS;
			idDupCheck = joinService.selectIdDupCheck(requestDto);
			
		} catch (Exception e) {
			result = Const.BOOLEAN_FAIL;
			e.printStackTrace();
		}
        
        resultMap.put("idDupCheck", idDupCheck);
        resultMap.put("result", result);
        return ResponseEntity.ok(resultMap);
	}
	/**
	 * 아이디 중복체크
	 * @param map
	 * @return
	 */
	@Operation(summary = "회원가입", description = "BO 회원을 신규 등록합니다.")
	@PostMapping("/join/saveUser")
	public ResponseEntity<?> saveUser(@RequestBody BoUsrRequestDto requestDto) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        String result = "";
        try {
			result = Const.BOOLEAN_SUCCESS;
			joinService.saveUser(requestDto);
			
		} catch (Exception e) {
			result = Const.BOOLEAN_FAIL;
			e.printStackTrace();
		}
        
        resultMap.put("result", result);
        return ResponseEntity.ok(resultMap);
	}

}
