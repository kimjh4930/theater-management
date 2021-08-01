package com.theater.admin.movie.domain.movie

import java.time.LocalDate

//test
class MovieKt {
    private var id : Long?
    private val title : MovieTitle
    private val director : Name
    private val openingDate : LocalDate
    private val actors : Actors
    private val grade : Grade
    private val runningTime : RunningTime

    constructor(
        id : Long,
        title : String,
        director : String,
        openingDate : LocalDate,
        actors : List<String>,
        grade : String,
        runningTime: Int
    ){
        this.id = id;
        this.title = MovieTitle(title)
        this.director = Name(director)
        this.openingDate = openingDate
        this.actors = Actors(actors)
        this.grade = Grade.get(grade)
        this.runningTime = RunningTime(runningTime)
    }
}