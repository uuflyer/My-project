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
insert into airdata(city, pm2_5, pm10, no2, o3)
values ('成都', 0.223, 0.355, 0.25, 0.17),
       ('遂宁', 0.13, 0.75, 0.95, 0.22)
