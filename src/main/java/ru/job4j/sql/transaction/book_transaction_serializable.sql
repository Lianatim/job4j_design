create table book(
    id serial primary key,
    name  varchar(255),
    author varchar(255),
    price float,
    count integer
);

insert into book(name, author, price, count)
values ('Мастер и Маргарита', 'Михаил Булгаков', 564.5, 5),
('Капитанская дочка', 'Александр Пушкин', 435.7, 0),
('Анна Каренина', 'Лев Толстой', 764.5, 5);

begin transaction isolation level serializable;

--1я транзакция
select sum(price) from book;
update book set price = 736.6 where name = 'Мастер и Маргарита';

--2я транзакция
select sum(price) from book;
update book set price = 222.1 where name = 'Капитанская дочка';
commit ;

--1я транзакция
commit ; --ошибка
