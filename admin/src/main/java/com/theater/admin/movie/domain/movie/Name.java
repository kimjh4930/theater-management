package com.theater.admin.movie.domain.movie;

import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Name {
    private String name;

    public Name() { }

    public Name(final String name) {
        validate(name);
        this.name = name;
    }

    private void validate (final String name){
        if(!StringUtils.hasLength(name)){
            throw new IllegalArgumentException("이름을 입력하세요.");
        }

        if((StringUtils.startsWithIgnoreCase(name," ") || StringUtils.endsWithIgnoreCase(name, " "))){
            throw new IllegalArgumentException("이름은 공백으로 시작하거나, 끝날 수 없습니다.");
        }
    }

    public String getName(){
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return name.equals(name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Name{" +
                "name='" + name + '\'' +
                '}';
    }
}
