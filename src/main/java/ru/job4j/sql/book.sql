create table author(
    id serial primary key,
    first_name varchar(255),
    last_name varchar(255)
);

create table book(
    id serial primary key,
    name_book varchar(255),
    author_id int references author(id)
);

insert into author(first_name, last_name)
values ('Михаил', 'Булгаков');
insert into author(first_name, last_name)
values ('Александр', 'Пушкин');
insert into author(first_name, last_name)
values ('Лев', 'Толстой');


insert into book(name_book, author_id)
values ('Мастер и Маргарита', 1);
insert into book(name_book, author_id)
values ('Капитанская дочка', 2);
insert into book(name_book, author_id)
values ('Анна Каренина', 3);
insert into book(name_book, author_id)
values ('Детство', 3);

select b.name_book, a.first_name, a.last_name
from book b join author a on b.author_id=a.id;

select b.name_book Название, a.first_name Имя, a.last_name Фамилия
from book b join author a on b.author_id=a.id;

select b.name_book as "Название книги", a.first_name Имя, a.last_name Фамилия
from book b join author a on b.author_id=a.id;