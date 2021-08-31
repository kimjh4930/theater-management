package com.theater.admin.movie.domain.movie;

import org.springframework.util.StringUtils;

import javax.persistence.Embeddable;

@Embeddable
public class MovieTitle {
    private String title;

    protected MovieTitle() {}

    public MovieTitle(final String title) {
        validate(title);
        this.title = title;
    }

    public String valueOf (){
        return this.title;
    }

    private void validate (final String title){
        if(!StringUtils.hasLength(title)){
            throw new IllegalArgumentException("영화제목을 입력하세요.");
        }

        if(StringUtils.startsWithIgnoreCase(title," ") || StringUtils.endsWithIgnoreCase(title, " ")){
            throw new IllegalArgumentException("영화제목은 공백으로 시작하거나, 끝날 수 없습니다.");
        }
    }

    @Override
    public String toString() {
        return "MovieTitle{" +
                "title='" + title + '\'' +
                '}';
    }
}
