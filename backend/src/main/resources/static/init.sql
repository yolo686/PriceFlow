drop table if exists user;
create table user (
    id int auto_increment primary key,
    username varchar(50) not null, unique,
    password varchar(50) not null,
    email varchar(50) not null,
    key username (username)
);

