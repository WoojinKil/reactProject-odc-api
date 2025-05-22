package com.odk.inf.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.odk.inf.dto.GameDto;

@Repository
public interface GameMapper {

	List<GameDto> selectGameList(GameDto gameParam);

}
