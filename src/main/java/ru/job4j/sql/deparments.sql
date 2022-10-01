create table employees (
id serial primary key,
name varchar(255)
);

create table deparments (
id serial primary key,
name varchar(255),
employees_id int references deparments(id)
);

insert into employees(name)
values ('employees 1'), ('employees 2'), ('employees 3'), ('employees 4');

insert into deparments (name, employees_id)
values ('Бухгалтерия', 1), ('IT', 2), ('Руководство', 3),
('Технический', null), ('Экономический', 4), ('Закупки', 1);

--Выполнить запросы с left, right, full, cross соединениями
--Используя left и right join написать запросы, которые давали бы
--одинаковый результат (порядок вывода колонок в эти запросах также должен быть идентичный).
select e.name Работник, d.name Отдел from deparments d left join employees e on d.employees_id = e.id; -- 1 таблица

select e.name Работник, d.name Отдел from deparments d right join employees e on d.employees_id = e.id; -- 2 таблица

select e.name Работник, d.name Отдел from employees e left join deparments d on  d.employees_id = e.id; -- идентична 2 таблице

select e.name Работник, d.name Отдел from employees e right join deparments d on d.employees_id = e.id; -- идентична 1 таблице


select * from deparments d full join employees e on d.employees_id = e.id;

select * from deparments d cross join employees e;

--Используя left join найти департаменты, у которых нет работников
select * from deparments d left join employees e  on e.id = d.employees_id
where e.name is null;

