package com.odc.inf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odc.inf.dto.GameDto;
import com.odc.inf.service.GameService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class GameController {

	@Autowired
	private GameService gameService;
	
	@PostMapping("/game/selectGameList")
    //public List<GameDto> selectGameList(@RequestBody GameDto gameParam) {
	public ResponseEntity<?> selectGameList(HttpServletRequest request, @RequestBody GameDto gameParam) {
		String header = request.getHeader("Authorization");
	    String userId = (String) request.getAttribute("userId");

	    if (userId == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
	    }
		//GameDto gameParam = null;
		List<GameDto> gameList = gameService.selectGameList(gameParam);
		
		
		return ResponseEntity.ok(gameList);

    }
}
