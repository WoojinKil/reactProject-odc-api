package com.odk.login.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface JoinMapper {

	String selectEmailDupCheck(String email);

	String selectIdDupCheck(String usrId);

}
