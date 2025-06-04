package com.odc.inf.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.odc.inf.dto.GameDto;

@Repository
public interface GameMapper {

	List<GameDto> selectGameList(GameDto gameParam);

}
