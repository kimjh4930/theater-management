drop table if exists movie_entity_actors;
drop table if exists movie;

create table movie (
   movie_id bigint auto_increment not null,
   director varchar(255),
   grade varchar(255),
   opening_date date,
   running_time integer not null,
   title varchar(255),
   version integer,
   primary key (movie_id)
);

create table movie_entity_actors (
     movie_entity_movie_id bigint not null,
     actors varchar(255)
);

alter table movie_entity_actors
    add foreign key (movie_entity_movie_id)
    references movie(movie_id)


