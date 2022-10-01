create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price)
values ('phone', 321.0), ('watch', 50.1), ('computer', 12321.22);

insert into people (name)
values ('Аня'), ('Ваня'), ('Боря');

insert into devices_people(device_id, people_id)
values(1, 2), (1, 3), (2, 2), (3, 1), (3, 2), (2, 3);


select name, avg(price) from devices group by name; --средняя цена устройств

select p.name, avg(d.price) --средняя цена устройств для каждого человека
from people as p
join devices_people dp on dp.people_id = p.id
join devices d on dp.device_id = d.id
group by p.name;

select p.name, avg(d.price) --средняя стоимость устройств должна быть больше 5000
from devices as d
join people p
on d.id = p.id
group by p.name
having avg(d.price) > 5000;

