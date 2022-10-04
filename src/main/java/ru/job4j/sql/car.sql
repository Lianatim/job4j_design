create table  body (
id serial primary key,
name varchar(255)
);

create table  engine (
id serial primary key,
name varchar(255)
);

create table  transmission (
id serial primary key,
name varchar(255)
);

create table  cars (
id serial primary key,
name varchar(255),
body_id int references body(id),
engine_id int references engine(id),
transmission_id int references transmission(id)
);

insert into body (name)
values ('Седан'), ('Хэтчбек'), ('Кабриолет'), ('Минивен'), ('Внедорожник'), ('Пикап');

insert into engine (name)
values ('Дизельный'), ('Бензиновые'), ('Газовые'), ('Роторные');

insert into transmission (name)
values ('Механическая '), ('Автоматическая'), ('Роботизированная');

insert into cars (name, body_id, engine_id, transmission_id)
values ('Mercedes-Benz', 5, 2, 2),
('kia optima', 1, 2, 1), ('Volkswagen Golf', 2, 2, 3),
('Citroen SpaceTourer', 4, 1, 1), ('Audi R8 Spyder', 3, 2, 3), ('Cadillac Escalade', 5, 1, 2)
;

--Вывести список всех машин и все привязанные к ним детали.
select cc.id Ключ, cc.name as "Название машины", b.name as "Тип кузова",
e.name as "Тип двигателя", t.name as "Тип коробки передач" from cars cc
left join body b on cc.body_id=b.id
left join engine e on cc.engine_id=e.id
left join transmission t on cc.transmission_id=t.id;

--Вывести список всех машин и все привязанные к ним детали.
select
cc.id Ключ,
cc.name as "Название машины",
b.name as "Тип кузова",
e.name as "Тип двигателя",
t.name as "Тип коробки передач"
from cars cc
left join body b on cc.body_id=b.id
left join engine e on cc.engine_id=e.id
left join transmission t on cc.transmission_id=t.id;

--Вывести кузовы, которые не используются НИ в одной машине.
select cc.id Ключ,
cc.name as "Название машины",
b.name as "Тип кузова"
from cars cc
right join body b on cc.body_id=b.id
where b.name is not null and cc.name is null;

--Вывести двигатели, которые не используются НИ в одной машине
select cc.id Ключ,
cc.name as "Название машины",
e.name as "Тип двигателя"
from cars cc
right join engine e on cc.engine_id=e.id
where e.name is not null and cc.name is null;

--Вывести коробки передач, которые не используются НИ в одной машине
select cc.id Ключ,
cc.name as "Название машины",
t.name as "Тип коробки передач"
from cars cc
right join transmission t on cc.transmission_id=t.id
where t.name is not null and cc.name is null;

