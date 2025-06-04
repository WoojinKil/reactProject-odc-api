package com.odc.login.service;

import java.util.Map;

import com.odc.login.dto.BoUsrRequestDto;
import com.odc.login.dto.UsrIdRequestDto;

public interface JoinService {

	void sendEmailCode(Map<String, Object> map);

	String selectEmailDupCheck(Map<String, Object> map);

	String selectVerifyEmailCode(Map<String, Object> map);

	String selectIdDupCheck(UsrIdRequestDto requestDto);

	void saveUser(BoUsrRequestDto requestDto);

}
