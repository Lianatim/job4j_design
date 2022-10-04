create table teens (
id serial primary key,
name varchar(15),
gender varchar(255)
);

insert into teens (name, gender)
values ('Маша', 'Agender '), ('Паша', 'Androgyne'),('Максим', 'Trans Man'),('Даша', 'Cisgender Man'),
		('Саша', 'Cis Female'), ('Марк', 'Gender Nonconforming');

select t1.name, t1.gender,
t2.name, t2.gender
from teens t1 cross join teens t2
where t1.name != t2.name;