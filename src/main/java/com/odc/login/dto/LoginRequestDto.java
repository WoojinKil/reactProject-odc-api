package com.odc.login.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {

	private String cstmId;
	private String trgtSeq;
	private String usrId;
	private String lgnId     ;
	private String pwd       ;
}
