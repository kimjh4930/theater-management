package com.theater.admin.movie.domain.movie;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class ActorsTest {
    @DisplayName("배우의 이름을 입력한다.")
    @Test
    void actors (){
        //given
        List<String> actorNames = List.of("송강호", "이선균", "조여정", "최우식", "박소담", "장혜진", "이정은", "박명훈");
        Actors actors = new Actors(actorNames);

        //when
        List<String> actorsName = actors.getActors();

        //then
        Assertions.assertThat(actorsName).isEqualTo(actorNames);
    }
}