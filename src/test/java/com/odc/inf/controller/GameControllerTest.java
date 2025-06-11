package com.odc.inf.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odc.inf.dto.GameDto;
import com.odc.login.dto.LoginRequestDto;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") 
public class GameControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String jwtToken;
    @BeforeEach
    void setUp() throws Exception {
        // 로그인 요청 DTO
        LoginRequestDto loginRequest = new LoginRequestDto();
        loginRequest.setLgnId("wjk7729"); // 실제 로그인 가능한 ID로 변경
        loginRequest.setPwd("123123123");       // 실제 패스워드로 변경

        String requestJson = objectMapper.writeValueAsString(loginRequest);

        MvcResult result = mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();

        // 헤더에서 JWT 추출
        String token = result.getResponse().getHeader("Authorization");
        assertThat(token).isNotNull().startsWith("Bearer ");
        jwtToken = token;
    }

	
	
    @Test
    
    @DisplayName("게임 조회 성공")
    @Sql(statements = {
    		"INSERT INTO ODC_BO_USR_M"
    		+ "(CSTM_ID, TRGT_SEQ, USR_ID, LGN_ID, PWD, USR_NM, EMAIL, PHONE, ADDRESS, ROLE_TP, USE_YN, ACTV_YN, DLT_YN, CRT_DTTM, CRTR_ID, UPD_DTTM, UPDR_ID)"
    		+ "VALUES('LBMP001', 1, 'wjk7729', 'wjk7729', '932f3c1b56257ce8539ac269d7aab42550dacf8818d075f0bdf1990562aae3ef', 'dasfas', 'ks676065@naver.com', '01086058253', '경기 성남시 분당구 판교역로 166 (백현동, 카카오 판교 아지트) ㄹㄹㄹㄹ', NULL, 'Y', 'N', 'N', '2025-06-05 02:28:29', 'asdfa', '2025-06-09 23:07:30', 'asdfa');"
    		,
    		"INSERT INTO ODC_GME_M"
    		+ "(CSTM_ID, TRGT_SEQ, GME_CD, KR_GME_NM, EN_GME_NM, LNG, PRD_CD, DSTBTR_CD, GME_CMNT, FRNT_YN, USE_YN, DLT_YN, CRT_DTTM, CRTR_ID, UPD_DTTM, UPDR_ID)"
    		+ "VALUES('LBMP001', 1, 'GM0001', '바이스슈발츠', 'Weis Schwarz', 'JPN', 'BURD', NULL, '바이스슈발츠', 'Y', 'Y', 'N', NULL, NULL, NULL, NULL);"
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void testGetGameById() throws Exception {
        String testGmeCd = "GM0001";
        GameDto requestDto = new GameDto();
        requestDto.setGmeCd(testGmeCd); // 또는 필요한 필드 설정

        String requestJson = objectMapper.writeValueAsString(requestDto);
        mockMvc.perform(post("/api/game/selectGameList")
                .header("Authorization", jwtToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].krGmeNm").value("바이스슈발츠"));
  
		
    }
}
