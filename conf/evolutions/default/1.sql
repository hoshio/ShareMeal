# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table tweet (
  id                        bigint not null,
  todo_name                 varchar(255),
  todo_detail               varchar(255),
  author                    varchar(255),
  postdate                  timestamp not null,
  constraint pk_tweet primary key (id))
;

create sequence tweet_seq;




# --- !Downs

drop table if exists tweet cascade;

drop sequence if exists tweet_seq;

