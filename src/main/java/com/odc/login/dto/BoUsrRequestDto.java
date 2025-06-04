package com.odc.login.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoUsrRequestDto {
	
	private String cstmId    ;
	private int trgtSeq   ;
	private String usrId     ;
	private String lgnId     ;
	private String pwd       ;
	private String usrNm     ;
	private String email     ;
	private String phone     ;
	private String address   ;
	private String roleTp    ;
	private String useYn     ;
	private String actvYn    ;
	private String dltYn     ;
	private String crtDttm   ;
	private String crtrId    ;
	private String updDttm   ;
	private String updrId    ;
}
