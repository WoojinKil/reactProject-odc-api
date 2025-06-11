package com.odc.login.entity;

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
@Table(name = "ODC_BO_USR_M")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoUsrEntity {

    @EmbeddedId
    private BoUsrId id;

    @Column(name = "LGN_ID")
    private String lgnId;

    @Column(name = "PWD")
    private String pwd;

    @Column(name = "USR_NM")
    private String usrNm;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "ROLE_TP")
    private String roleTp;

    @Column(name = "USE_YN")
    private String useYn;

    @Column(name = "ACTV_YN")
    private String actvYn;

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
