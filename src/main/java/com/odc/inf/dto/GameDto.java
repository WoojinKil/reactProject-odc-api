package com.odc.inf.dto;

import lombok.Data;

@Data
public class GameDto {

	private String cstmId          ;              //고객사아이디
	private String trgtSeq         ;              //대상순번
	private String gmeCd           ;              //게임코드
	private String krGmeNm         ;              //한글게임명
	private String enGmeNm          ;             //영문게임명
	private String lng              ;             //언어
	private String prdCd           ;              //제작사
	private String dstbtrCd        ;              //유통사
	private String gmeCmnt         ;              //게임설명
	private String frntYn          ;              //프론트전시여부
	private String useYn           ;              //사용여부
	private String dltYn           ;              //삭제여부
	private String crtDttm         ;              //등록일자
	private String crtrId          ;              //등록자ID
	private String updDttm         ;              //수정일자
	private String updrId          ;              //수정자ID
	

	
	
	
}
