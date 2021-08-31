package com.theater.admin.movie.domain.movie;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class RunningTimeTest {
    @DisplayName("상영시간을 설정한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 10, 30})
    void setRunningTime (Integer value){
        //given
        RunningTime runningTime = new RunningTime(value);
        //when

        //then
        assertThat(runningTime.valueOf()).isEqualTo(value);
    }

    @DisplayName("상영시간을 설정하지 않거나 0 또는 음수로 설정하면 예외가 발생한다.")
    @ParameterizedTest
    @NullSource
    @ValueSource(ints = {-1, -20, 0})
    void unsetRunningTime (Integer value){
        //given

        //when

        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new RunningTime(value).valueOf());
    }
}