package com.theater.admin.movie.adapter.presentation.web;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDate;

@Component
public class MovieRequestValidator {
    public void validate(NewMovie newMovie, Errors errors){
        if(newMovie.getOpeningDate().isBefore(LocalDate.now())){
            errors.reject("wrongOpeningDate", "이미 개봉한 영화를 등록할 수 없습니다.");
        }
    }

    public void validate(UpdatedMovie updatedMovie, Errors errors){
        if(updatedMovie.getOpeningDate().isBefore(LocalDate.now())){
            errors.reject("wrongOpeningDate", "이미 개봉한 영화를 수정할 수 없습니다.");
        }
    }
}
