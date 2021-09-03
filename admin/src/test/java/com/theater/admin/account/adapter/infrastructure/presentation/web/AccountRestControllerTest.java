package com.theater.admin.account.adapter.infrastructure.presentation.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theater.admin.account.adapter.presentation.web.StoredAccount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class AccountRestControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @DisplayName("모든 계정을 조회한다.")
    @Test
    void findAll () throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/api/accounts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString();
        List<StoredAccount> accounts = List.of(objectMapper.readValue(body, StoredAccount[].class));

        accounts.forEach(System.out::println);
    }

    @DisplayName("ID로 계정을 조회한다")
    @Test
    void findById () throws Exception {
        Long id = 1L;
        MvcResult mvcResult = this.mockMvc.perform(get("/api/accounts/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString();

        StoredAccount account = objectMapper.readValue(body, StoredAccount.class);
        System.out.println(account);
    }

    @DisplayName("Email로 계정을 조회한다.")
    @Test
    void findByEmail () throws Exception {
        String email = "kimjh4930@gmail.com";

        MvcResult mvcResult = this.mockMvc.perform(get("/api/accounts/email/" + email))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("email").value(email))
                .andReturn();

        String body = mvcResult.getResponse().getContentAsString();

        StoredAccount account = objectMapper.readValue(body, StoredAccount.class);

        Assertions.assertThat(account.getEmail()).isEqualTo(email);
    }

}