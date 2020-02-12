create table airdata
(
    id        integer auto_increment primary key,
    city      varchar(10),
    save_time timestamp not null default current_timestamp,
    pm2_5     double,
    pm10      double,
    no2       double,
    o3        double
);
create table user
(
    id                 bigint auto_increment primary key,
    username           varchar(100) unique,
    encrypted_password varchar(100),
    tel                varchar(15),
    mail               varchar(30)
);
insert into airdata(city, pm2_5, pm10, no2, o3)
values ('成都', 0.223, 0.355, 0.25, 0.17),
       ('遂宁', 0.13, 0.75, 0.95, 0.22),
       ('北京', 0.1233, 0.35, 0.65, 0.19);
insert into user(id, username, encrypted_password, tel)
values (1, 'tql', 123456, 13412311234567)
