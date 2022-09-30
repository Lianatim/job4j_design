create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date)
values ('fish_shark', 5, '0001-01-01');
insert into fauna(name, avg_age, discovery_date)
values ('bird_hawk', 10, '0100-02-01');
insert into fauna(name, avg_age, discovery_date)
values ('bird_own', 17, '2100-12-01');
insert into fauna(name, avg_age, discovery_date)
values ('animal_moose', 15000, '2005-11-01');
insert into fauna(name, avg_age, discovery_date)
values ('insect_mantis', 134000, '2025-11-01');

select * from fauna where name like '%fish%';

select * from fauna where avg_age > 10000 and avg_age < 21000;

select * from fauna where avg_age is null;

select * from fauna where discovery_date < '1950-01-01';