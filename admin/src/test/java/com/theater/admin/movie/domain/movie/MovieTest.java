package com.theater.admin.movie.domain.movie;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

class MovieTest {

    @DisplayName("영화를 등록한다.")
    @Test
    void addMovie (){
        //given

        //when

        //then
        Assertions.assertThatNoException().isThrownBy(() ->
            new Movie.Builder(
                    "기생충",
                    "봉준호",
                    LocalDate.of(2019, 5, 30),
                    List.of("송강호", "이선균", "조여정", "최우식", "박소담", "이정은", "장혜진"),
                    "PG15",
                    131
            ).build()
        );
    }

}