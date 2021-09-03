package com.theater.admin.account.adapter.presentation.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.server.Encoding;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class AccountControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @DisplayName("회원가입 페이지가 보이는지 테스트")
    @Test
    void getSignUpPage () throws Exception {
        mockMvc.perform(get("/accounts/signup"))
                .andExpect(status().isOk())
                .andExpect(view().name("account/signup"))
                .andExpect(model().attributeExists("newAccount"))
        ;
    }

    @DisplayName("회원 가입 처리 - 입력값 오류")
    @Test
    void signup_with_wrong_input () throws Exception {
        //given
        NewAccount newAccount = new NewAccount.Builder()
                .build();

        //when

        //then
        mockMvc.perform(post("/accounts/signup")
                        .param("name", newAccount.getName())
                        .param("nickname", newAccount.getNickname())
                        .param("email", newAccount.getEmail())
                        .param("password", newAccount.getPassword())
                )
                .andExpect(status().isOk())
                .andExpect(view().name("account/signup"))
                ;
    }

    @DisplayName("회원 가입")
    @Test
    void signup () throws Exception {
        //given
        String redirectPath = "/";
        NewAccount newAccount = new NewAccount.Builder()
                .email("junha.kim@kakao.com")
                .name("김준하")
                .nickname("jhkim")
                .password("12345678")
                .build();

        //when
        MvcResult mvcResult = mockMvc.perform(post("/accounts/signup")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .characterEncoding("utf-8")
                        .param("name", newAccount.getName())
                        .param("nickname", newAccount.getNickname())
                        .param("email", newAccount.getEmail())
                        .param("password", newAccount.getPassword())
                )
                .andExpect(status().is3xxRedirection())
                .andReturn();

        //then
        assertThat(mvcResult.getResponse().getRedirectedUrl())
                .isEqualTo(redirectPath);
    }
}