package com.odc.inf.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.odc.inf.dto.GameDto;

@Mapper
public interface GameMapper {

	List<GameDto> selectGameList(GameDto gameParam);

}
