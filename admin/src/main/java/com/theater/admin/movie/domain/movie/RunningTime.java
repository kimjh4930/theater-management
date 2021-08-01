package com.theater.admin.movie.domain.movie;

import java.util.Objects;

public class RunningTime {
    private final Integer runningTime;

    public RunningTime(Integer runningTime) {
        validate(runningTime);
        this.runningTime = runningTime;
    }

    private void validate (Integer runningTime){
        if(runningTime == null || runningTime <= 0){
            throw new IllegalArgumentException("상영시간을 1분 이상 설정해야 합니다.");
        }
    }

    public int valueOf (){
        return this.runningTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RunningTime that = (RunningTime) o;
        return runningTime.equals(that.runningTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(runningTime);
    }

    @Override
    public String toString() {
        return "RunningTime{" +
                "runningTime=" + runningTime +
                '}';
    }
}
