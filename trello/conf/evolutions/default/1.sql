# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table trello (
  id                        integer auto_increment not null,
  email_id                  varchar(255),
  title                     varchar(255),
  task                      varchar(255),
  date                      varchar(255),
  constraint pk_trello primary key (id))
;

create table users (
  email_id                  varchar(255) not null,
  user_name                 varchar(255),
  password                  varchar(255),
  constraint pk_users primary key (email_id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table trello;

drop table users;

SET FOREIGN_KEY_CHECKS=1;

