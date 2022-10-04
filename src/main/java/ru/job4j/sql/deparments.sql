create table deparments (
id serial primary key,
name varchar(255)
);

create table employees (
id serial primary key,
name varchar(255),
deparments_id int references employees(id)
);

insert into deparments (name)
values ('Бухгалтерия'), ('IT'), ('Руководство'),
('Технический'), ('Экономический'), ('Закупки');

insert into employees(name, deparments_id)
values ('employees 1', 1), ('employees 2', 2), ('employees 3', 3),
(null, null), ('employees 5', 4), ('employees 6', 5),
('employees 7', 6);


--Выполнить запросы с left, right, full, cross соединениями
--Используя left и right join написать запросы, которые давали бы
--одинаковый результат (порядок вывода колонок в эти запросах также должен быть идентичный).
select
e.name Работник,
d.name Отдел
from employees e
left join deparments d on e.deparments_id = d.id; -- 1 таблица


select
e.name Работник,
d.name Отдел
from employees e
right join deparments d on e.deparments_id = d.id; -- 2 таблица


select
e.name Работник,
d.name Отдел
from deparments d
left join employees e on  e.deparments_id = d.id; -- идентична 2 таблице


select
e.name Работник,
d.name Отдел
from deparments d
right join employees e on  e.deparments_id = d.id; -- идентична 1 таблице


select * from employees e
left join deparments d on e.deparments_id = d.id;


select * from employees e cross join deparments d;

--Используя left join найти департаменты, у которых нет работников
select * from deparments d  left join employees e on e.deparments_id = d.id
where e.name is null;