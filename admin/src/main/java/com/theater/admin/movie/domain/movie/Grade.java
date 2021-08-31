package com.theater.admin.movie.domain.movie;

import java.util.List;

public enum Grade {
    ALL(0, "ALL", "전체관람가"),
    PG12(1, "PG12", "12세이상관람가"),
    PG15(2, "PG15", "15세이상관람가"),
    PG18(3, "PG18", "18세이상관람가");

    private static final int ALL_VALUE = 0;
    private static final int PG12_VALUE = 1;
    private static final int PG15_VALUE = 2;
    private static final int PG18_VALUE = 3;

    private final int value;
    private final String grade;
    private final String description;

    Grade (int value, String grade, String description){
        this.value = value;
        this.grade = grade;
        this.description = description;
    }

    private static final Grade[] VALUES_ARRAY =
            new Grade[] {
              ALL, PG12, PG15, PG18
            };

    public static final List<Grade> VALUES = List.of(VALUES_ARRAY);

    public static Grade get(String grade){
        for(int i=0; i<VALUES.size(); i++){
            Grade result = VALUES_ARRAY[i];
            if(result.toString().equals(grade)){
                return result;
            }
        }
        return null;
    }

    public static Grade get(int value){
        switch (value){
            case ALL_VALUE : return ALL;
            case PG12_VALUE : return PG12;
            case PG15_VALUE : return PG15;
            case PG18_VALUE : return PG18;
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public String getGrade() {
        return grade;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return grade;
    }
}
