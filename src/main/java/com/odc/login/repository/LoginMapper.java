package com.odc.login.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import com.odc.login.dto.BoUsrDto;
import com.odc.login.dto.LoginRequestDto;

@Mapper
public interface LoginMapper {

	BoUsrDto selectByLgnId(LoginRequestDto loginRequestDto);

}
