package com.odc.inf.entity;

import java.io.Serializable;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class GmeId implements Serializable {

    private String cstmId;
    private Integer trgtSeq;
    private String gmeCd;

    public GmeId() {}

    public GmeId(String cstmId, Integer trgtSeq, String gmeCd) {
        this.cstmId = cstmId;
        this.trgtSeq = trgtSeq;
        this.gmeCd = gmeCd;
    }
}
