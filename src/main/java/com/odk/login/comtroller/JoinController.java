package com.odk.login.comtroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odk.common.util.Const;
import com.odk.login.service.JoinService;

/**
 * @author woojin kil
 *
 */
@RestController
@RequestMapping("/api")
public class JoinController {

	@Autowired
	JoinService joinService;
	
	@PostMapping("/join/sendEmailCode")
	public ResponseEntity<?> sendEmailCode(@RequestBody Map<String, Object> map) {
	
		System.out.println(map);
		joinService.sendEmailCode(map);
		
		return ResponseEntity.ok("email success");
		
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
}
