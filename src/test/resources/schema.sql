create table OJUG_MEMB (
  member_id number,
  first_name varchar(24),
  last_name varchar(24) not null,
  join_date DATE not null,
);

create table OJUG_MEETINGS (
  meeting_id number not null,
  meeting_date DATE not null,
  meeting_topic varchar(120)
);

create table OJUG_MEET_ATTEND (
  meeting_id number not null,
  member_id number,
  presented varchar(1)
);