insert into movie (director, grade, opening_date, running_time, title, version, created_at, updated_at)
values ('봉준호', 'PG15', '2019-05-30', 131, '기생충', 0, '2021-08-30T00:40:56.070410', '2021-08-30T00:40:56.070410');

insert into movie (director, grade, opening_date, running_time, title, version, created_at, updated_at)
values ('최동훈', 'PG18', '2006-09-28', 139, '타짜', 0, '2021-08-30T00:40:56.070410', '2021-08-30T00:40:56.070410');

insert into movie (director, grade, opening_date, running_time, title, version, created_at, updated_at)
values ('James Francis Cameron', 'PG12', '2009-12-17', 162, '아바타', 0, '2021-08-30T00:40:56.070410', '2021-08-30T00:40:56.070410');

insert into movie_entity_actors (movie_entity_movie_id, actors) values (1, '송강호');
insert into movie_entity_actors (movie_entity_movie_id, actors) values (1, '이선균');
insert into movie_entity_actors (movie_entity_movie_id, actors) values (1, '조여정');
insert into movie_entity_actors (movie_entity_movie_id, actors) values (1, '최우식');
insert into movie_entity_actors (movie_entity_movie_id, actors) values (1, '박소담');
insert into movie_entity_actors (movie_entity_movie_id, actors) values (1, '이정은');
insert into movie_entity_actors (movie_entity_movie_id, actors) values (1, '장혜진');

insert into movie_entity_actors (movie_entity_movie_id, actors) values (2, '조승우');
insert into movie_entity_actors (movie_entity_movie_id, actors) values (2, '김혜수');
insert into movie_entity_actors (movie_entity_movie_id, actors) values (2, '백윤식');
insert into movie_entity_actors (movie_entity_movie_id, actors) values (2, '유해진');

insert into movie_entity_actors (movie_entity_movie_id, actors) values (3, 'Sam Wort타hington');
insert into movie_entity_actors (movie_entity_movie_id, actors) values (3, 'Jo Saldana');

insert into account(email, name, nickname, account_role, password, joined_at)
values ('kimjh4930@gmail.com', '김준하', 'jhkim', 'ADMIN', '12345678','2021-08-30T00:40:56.070410' );

insert into account(email, name, nickname, account_role, password, joined_at)
values ('kimjh4930@nate.com', '김준하', 'jhkim1', 'UNAUTHORIZED', '12345678','2021-08-30T00:40:56.070410');