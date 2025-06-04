package com.odc.inf.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odc.inf.dto.GameDto;
import com.odc.inf.service.GameService;

//@RestController
//@RequestMapping("/api")
public class GameController {

	@Autowired
	private GameService gameService;
	
	//@GetMapping("/game/selectGameList")
    //public List<GameDto> selectGameList(@RequestBody GameDto gameParam) {
	public ResponseEntity<?> selectGameList() {
		
		GameDto gameParam = null;
		List<GameDto> gameList = gameService.selectGameList(gameParam);
		
		
		return ResponseEntity.ok(gameList);

    }
}
