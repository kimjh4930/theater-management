package com.theater.admin.movie.domain.movie;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class GradeTest {

    @DisplayName("String 값으로 Enum 값을 가져올 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {"ALL", "PG12", "PG15", "ADULT"} )
    void setGrade (String stringGrade){
        Assertions.assertThat(Grade.get(stringGrade)).isEqualTo(Grade.valueOf(stringGrade));
    }

}