package com.odk.inf.service;

import java.util.List;

import com.odk.inf.dto.GameDto;

public interface GameService {

	List<GameDto> selectGameList(GameDto gameParam);

}
