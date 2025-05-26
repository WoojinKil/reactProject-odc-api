package com.odk.login.service;

import java.util.Map;

public interface JoinService {

	void sendEmailCode(Map<String, Object> map);

	String selectEmailDupCheck(Map<String, Object> map);

	String selectVerifyEmailCode(Map<String, Object> map);

}
