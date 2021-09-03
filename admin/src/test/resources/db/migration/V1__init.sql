drop table if exists movie_entity_actors;
drop table if exists movie;
drop table if exists account;

create table movie (
   movie_id bigint auto_increment not null,
   director varchar(255),
   grade varchar(255),
   opening_date date,
   running_time integer not null,
   title varchar(255),
   version integer,
   created_at timestamp,
   updated_at timestamp,
   primary key (movie_id)
);

create table movie_entity_actors (
     movie_entity_movie_id bigint not null,
     actors varchar(255)
);

create table account (
     account_id bigint auto_increment not null,
     email varchar(255),
     name varchar(255),
     nickname varchar(255),
     account_role varchar(255) default 'UNAUTHORIZED',
     password varchar(255),
     joined_at timestamp,
     primary key (account_id)
);

alter table movie_entity_actors
    add foreign key (movie_entity_movie_id)
    references movie(movie_id)




