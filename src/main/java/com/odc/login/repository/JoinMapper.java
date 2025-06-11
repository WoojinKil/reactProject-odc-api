package com.odc.login.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.odc.login.dto.BoUsrRequestDto;

@Mapper
public interface JoinMapper {

	String selectEmailDupCheck(String email);

	String selectIdDupCheck(String usrId);

	void insertBoUsr(BoUsrRequestDto requestDto);

}
