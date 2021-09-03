package com.theater.admin.account.domain.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

class EmailTest {

    @DisplayName("이메일을 입력한다.")
    @ParameterizedTest
    @ValueSource(strings = {
            "email@example.com",
            "firstname.lastname@example.com",
            "email@subdomain.example.com",
            "firstname+lastname@example.com",
            "email@123.123.123.123",
            "email@[123.123.123.123]",
            "\"email\"@example.com",
            "1234567890@example.com",
            "email@example.museum",
            "email@example.co.jp"
    })
    void email (String input){
        //given

        //when

        //then
        assertThatNoException().isThrownBy(() -> new Email(input));
    }

    @DisplayName("이메일 형식을 잘못 입력하면 Exception이 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {
            "12345678",
            "abc",
            "def@def"
    })
    void wrongEmail (String input){
        //given

        //when

        //then
        assertThatIllegalArgumentException().isThrownBy(() -> new Email(input));
    }

}