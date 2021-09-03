package com.theater.admin.common;

import com.theater.admin.common.Name;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class NameTest {
    @DisplayName("이름을 설정한다.")
    @ParameterizedTest
    @ValueSource(strings = {"송강호", "이선균", "조여정", "최우식", "박소담", "장혜진", "이정은", "박명훈"})
    void setName (String input){
        //given
        Name name = new Name(input);

        //when

        //then
        Assertions.assertThat(name.getName()).isEqualTo(input);
    }

    @DisplayName("이름을 입력하지 않으면 예외가 발생한다.")
    @ParameterizedTest
    @EmptySource
    void unsetMovieDirector(String unsetName){
        //given

        //when

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Name(unsetName));
    }

    @DisplayName("공백만 있는 이름을 설정할 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {" ", "   ", "     "})
    void emptyMovieTitle(String emptyName) {
        //given

        //when

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Name(emptyName));
    }

    @DisplayName("감독이름은 공백으로 시작하거나 끝날 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {" 봉준호 ", " 강우석", "    구명철 ", "    곽재용 "})
    void withIgnoreCase (String withIgnoreCase){
        //given

        //when

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Name(withIgnoreCase));
    }

}