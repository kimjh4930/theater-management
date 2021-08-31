package com.theater.admin.movie.adapter.infrastructure.jpa.h2;

import com.theater.admin.movie.domain.movie.Movie;
import com.theater.admin.movie.domain.movie.MovieRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class H2MovieRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MovieRepository repository;

    @Transactional
    @Rollback
    @DisplayName("데이터베이스에 등록한 모든 영화를 조회한다.")
    @Test
    void findAll (){
        System.out.println("findAll");
        //given

        //when
        List<Movie> savedMovies = repository.findAll();

        //then
        System.out.println("result");
        savedMovies.forEach(System.out::println);
        assertThat(savedMovies.size()).isNotZero();
    }

    @Transactional
    @Rollback
    @DisplayName("Movie를 저장 후 조회한다.")
    @Test
    void save () {
        //given
        Movie newMovie = new Movie.Builder(
                "모가디슈",
                "류승완",
                LocalDate.of(2021, 7, 28),
                List.of("김윤석", "조인성", "허준호", "구교환", "김소진", "정만식", "김재화", "박경혜"),
                "PG15",
                121)
                .build();

        //when
        Long id = repository.save(newMovie);
        entityManager.flush();
        entityManager.clear();

        //then
        Movie savedMovie = repository.findById(id).orElseThrow(() -> new NullPointerException());
        assertThat(savedMovie.getId()).isEqualTo(id);
        assertThat(repository.findAll().size()).isEqualTo(4);
        assertThat(savedMovie.getVersion()).isEqualTo(0);
    }

    @Transactional
    @Rollback
    @DisplayName("영화 제목으로 조회한다.")
    @Test
    void findByTitle () {
        //given
        String title = "아바타";

        //when
        Movie findMovie = repository.findByTitle(title).orElseThrow(() -> new NullPointerException());

        //then
        assertThat(findMovie.getTitle()).isEqualTo(title);
    }

    @Transactional
    @Rollback
    @DisplayName("영화관람 등급을 변경한다")
    @Test
    void update (){
        //given
        String title = "기생충";
        Movie parasite = repository.findByTitle(title).orElseThrow(() -> new NullPointerException());

        Movie updated = new Movie.Builder(
                "기생충",
                "봉준호",
                LocalDate.of(2019, 5, 30),
                List.of("송강호", "이선균", "조여정", "최우식", "박소담", "이정은", "장혜진"),
                "PG18",
                131)
                .id(parasite.getId())
                .build();

        //when
        repository.update(updated);

        entityManager.flush();
        entityManager.clear();

        //then
        Movie updatedMovie = repository.findById(parasite.getId()).orElseThrow(() -> new NullPointerException());
        assertThat(updatedMovie.getGrade()).isEqualTo("PG18");
        assertThat(updatedMovie.getVersion()).isEqualTo(1);
    }
}