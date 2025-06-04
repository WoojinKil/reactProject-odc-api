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

import lombok.extern.slf4j.Slf4j;

/**
 * @author woojin kil
 *
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class JoinController {

	@Autowired
	JoinService joinService;
	
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
