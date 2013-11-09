create table auth_policy(
  id bigint unsigned not null auto_increment,
  user varchar(256) not null,
  objects varchar(2048) not null,
  operations varchar(1024) not null,
  conditions varchar(1024),
  primary key (id)
);