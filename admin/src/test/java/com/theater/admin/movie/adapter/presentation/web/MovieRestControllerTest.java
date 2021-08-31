package com.theater.admin.movie.adapter.presentation.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
class MovieRestControllerTest {

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

    @DisplayName("저장된 영화를 모두 불러온다.")
    @Test
    void findAll () throws Exception {
        this.mockMvc.perform(get("/movies"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("영화를 조회한다.")
    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L})
    void findOne(Long id) throws Exception {
        this.mockMvc.perform(get("/movies/" + id))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("영화를 등록한다.")
    @Test
    void add () throws Exception {
        NewMovie newMovie = NewMovie.builder()
                .title("모가디슈")
                .director("류승완")
                .openingDate(LocalDate.now().plusDays(1))
                .actors(List.of("김윤석", "조인성", "허준호", "구교환", "김소진", "정만식", "김재화", "박경혜"))
                .grade("PG15")
                .runningTime(131)
                .build();

        this.mockMvc.perform(post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newMovie)))
                .andDo(print())
                .andExpect(status().isCreated())
//                .andExpect(content().string("4"))
        ;
    }

    @DisplayName("영화 정보를 수정한다.")
    @Test
    void update () throws Exception {
        Long id = 1L;

        UpdatedMovie updatedMovie = new UpdatedMovie(
                1L,
                "기생충1",
                "봉준호",
                LocalDate.now(),
                List.of("송강호", "이선균", "박경혜"),
                "PG18",
                100
        );

        MvcResult mvcResult = this.mockMvc.perform(put("/movies/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updatedMovie)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("result : " + result);
    }

    @DisplayName("영화정보를 제대로 입력하지 않으면 BAD_REQUEST를 반환한다.")
    @Test
    void imcompleteMovieInfo () throws Exception {
        NewMovie newMovie = NewMovie.builder()
                .openingDate(LocalDate.of(2021, 7, 28))
                .actors(List.of("김윤석", "조인성", "허준호"))
                .grade("PG15")
                .build();

        System.out.println("NewMovie : " + objectMapper.writeValueAsString(newMovie));

        mockMvc.perform(post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newMovie)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("이미 개봉한 영화를 등록할 수 없다.")
    @Test
    void openingDateIsPastThanNow () throws Exception {
        NewMovie newMovie = NewMovie.builder()
                .title("모가디슈")
                .director("류승완")
                .openingDate(LocalDate.now().minusDays(1))
                .actors(List.of("김윤석", "조인성", "허준호", "구교환", "김소진", "정만식", "김재화", "박경혜"))
                .grade("PG15")
                .runningTime(131)
                .build();

        mockMvc.perform(post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newMovie)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("이미 개봉한 영화를 수정할 수 없다.")
    @Test
    void cannotModifyOpeningDate () throws Exception {
        Long id = 1L;

        UpdatedMovie updatedMovie = new UpdatedMovie(
                1L,
                "기생충1",
                "봉준호",
                LocalDate.now().minusDays(1),
                List.of("송강호", "이선균", "박경혜"),
                "PG18",
                100
        );

        mockMvc.perform(put("/movies/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedMovie)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}