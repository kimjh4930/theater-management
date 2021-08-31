package com.theater.admin.movie.domain.movie;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class MovieTitleTest {

    @DisplayName("영화 이름을 입력할 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {"기생충", "남산의 부장들", "다만 악에서 구하소서", "반도", "히트맨", "#살아있다"})
    void setMovieTitle (String title){
        //given
        MovieTitle movieTitle = new MovieTitle(title);

        //when

        //then
        assertThat(movieTitle.valueOf()).isEqualTo(title);
    }

    @DisplayName("영화 이름을 입력하지 않으면 예외가 발생한다.")
    @ParameterizedTest
    @EmptySource
    void unsetMovieTitle (String unsetTitle){
        //given

        //when

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new MovieTitle(unsetTitle));
    }

    @DisplayName("공백만 있는 영화이름을 설정할 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {" ", "   ", "     "})
    void emptyMovieTitle(String emptyTitle) {
        //given

        //when

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new MovieTitle(emptyTitle));
    }

    @DisplayName("영화제목은 공백으로 시작하거나 끝날 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {" 기생충 ", " 남산의 부장들", "    다만 악에서 구하소서 ", "    반도 ", "히트맨   ", "#살아있다      "})
    void withIgnoreCase (String withIgnoreCase){
        //given

        //when

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new MovieTitle(withIgnoreCase));
    }

}