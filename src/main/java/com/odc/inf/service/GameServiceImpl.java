package com.odc.inf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odc.inf.dto.GameDto;
import com.odc.inf.repository.GameMapper;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private GameMapper gameMapper;
	
	@Override
	public List<GameDto> selectGameList(GameDto gameParam) {
		// TODO Auto-generated method stub
		return gameMapper.selectGameList(gameParam);
	}
	
}
