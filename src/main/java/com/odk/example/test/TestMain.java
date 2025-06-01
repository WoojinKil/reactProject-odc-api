package com.odk.example.test;

import com.odk.login.dto.UsrIdRequestDto;

public class TestMain {
    public static void main(String[] args) {
        UsrIdRequestDto dto = new UsrIdRequestDto();
        dto.setCstmId("test123");
        System.out.println(dto.getUsrId());  // 정상 출력?
    }


}
