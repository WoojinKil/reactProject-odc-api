package com.odc.inf.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "ODC_GME_M")
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameEntity {

    @EmbeddedId
    private GmeId id;

    @Column(name = "KR_GME_NM")
    private String krGmeNm;

    @Column(name = "EN_GME_NM")
    private String enGmeNm;

    @Column(name = "LNG")
    private String lng;

    @Column(name = "PRD_CD")
    private String prdCd;

    @Column(name = "DSTBTR_CD")
    private String dstbtrCd;

    @Column(name = "GME_CMNT")
    private String gmeCmnt;

    @Column(name = "FRNT_YN")
    private String frntYn;

    @Column(name = "USE_YN")
    private String useYn;

    @Column(name = "DLT_YN")
    private String dltYn;

    @Column(name = "CRT_DTTM")
    private String crtDttm;

    @Column(name = "CRTR_ID")
    private String crtrId;

    @Column(name = "UPD_DTTM")
    private String updDttm;

    @Column(name = "UPDR_ID")
    private String updrId;
}
