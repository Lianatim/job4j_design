create table names (
id serial primary key,
name varchar(255)
);

create table genders (
id serial primary key,
name varchar(255)
);

insert into names (name)
values ('Маша'), ('Паша'),('Максим'),('Даша'),
		('Саша'),('Марк'),('Матвей'),('Яна'),('Зоя');

insert into genders(name)
values ('Agender '), ('Androgyne'),('Cis Female'),('Cisgender Man'),
		('Gender Nonconforming'),('Trans Man'),('Transgender Male'),('Transmasculine'),('Two-spirit');

select g.name гендер, n.name имя from genders g cross join names n;