package com.odk.login.comtroller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odk.login.comtroller.dto.EmailRequest;
import com.odk.login.service.JoinService;

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
}
