package com.odc.login.service;

import java.util.Map;

import com.odc.login.dto.LoginRequestDto;

public interface LoginService {

	Map<String, Object>  loginProcess(LoginRequestDto loginRequestDto);

}
