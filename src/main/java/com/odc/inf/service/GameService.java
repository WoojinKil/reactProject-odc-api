package com.odc.inf.service;

import java.util.List;

import com.odc.inf.dto.GameDto;

public interface GameService {

	List<GameDto> selectGameList(GameDto gameParam);

}
