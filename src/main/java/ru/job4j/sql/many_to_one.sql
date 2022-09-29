create table car(
    id serial primary key,
    name varchar(255)
);

create table spare_parts(
    id serial primary key,
    name varchar(255),
    car_id int references car(id)
);

insert into car(name) values ('volvo');
insert into spare_parts(name, car_id) values ('wheel', 1);

select * from spare_parts;

select * from car where id in (select car_id from spare_parts);