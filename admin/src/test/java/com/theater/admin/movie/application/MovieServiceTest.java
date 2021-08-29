package com.theater.admin.movie.application;

import com.theater.admin.movie.adapter.presentation.web.NewMovie;
import com.theater.admin.movie.adapter.presentation.web.StoredMovie;
import com.theater.admin.movie.adapter.presentation.web.UpdatedMovie;
import com.theater.admin.movie.domain.movie.Movie;
import com.theater.admin.movie.domain.movie.MovieRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MovieServiceTest {
    @Autowired MovieService movieService;
    @Autowired MovieRepository movieRepository;

    @Transactional
    @DisplayName("영화를 등록한다.")
    @Test
    void create (){
        //given
        NewMovie newMovie = new NewMovie(
                "모가디슈",
                "류승완",
                LocalDate.of(2021, 7, 28),
                List.of("김윤석", "조인성", "허준호", "구교환", "김소진", "정만식", "김재화", "박경혜"),
                "PG15",
                131
        );

        //when
        Long id = movieService.create(newMovie);

        //then
        System.out.println("NewMovie id : " + id);
        Movie saved = movieRepository.findById(id).orElseThrow(() -> new NullPointerException());
        System.out.println("saved : " + saved);
        assertThat(id).isNotNull();
    }

    @Transactional
    @DisplayName("등록된 영화를 모두 불러온다.")
    @Test
    void findAll (){
        //given

        //when
        List<StoredMovie> storedMovies = movieService.findAll();

        //then
        storedMovies.forEach(System.out::println);
        assertThat(storedMovies).isNotEmpty();
    }

    @Transactional
    @DisplayName("영화개봉일을 수정한다.")
    @Test
    void update (){
        //given
        Movie parasite = movieRepository.findByTitle("기생충").orElseThrow(() -> new NullPointerException());
        UpdatedMovie updatedParasite = new UpdatedMovie(
                parasite.getId(),
                parasite.getTitle(),
                parasite.getDirector(),
                LocalDate.of(2021, 12, 31),
                parasite.getActors(),
                parasite.getGrade(),
                parasite.getRunningTime()
        );

        //when
        Long id = movieService.update(updatedParasite);
        Movie findMovie = movieRepository.findById(id).orElseThrow(() -> new NullPointerException());

        //then
        System.out.println("findMovie : " + findMovie);
        assertThat(findMovie.getOpeningDate()).isEqualTo(updatedParasite.getOpeningDate());
        assertThat(findMovie.getVersion()).isEqualTo(1);
    }
}