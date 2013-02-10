# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table avarage_lateness (
  id                        varchar(255) not null,
  date                      timestamp,
  late_on_avarage           bigint,
  service_provider_name     varchar(255),
  constraint pk_avarage_lateness primary key (id))
;

create sequence avarage_lateness_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists avarage_lateness;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists avarage_lateness_seq;

