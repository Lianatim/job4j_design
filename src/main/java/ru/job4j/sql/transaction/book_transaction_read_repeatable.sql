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

-- 1, 2 транзакция
begin transaction isolation level repeatable read;

-- 1, 2 транзакция
select * from book;

-- 1 транзакция
insert into book(name, author, price, count)
values ('Триумфальная Арка', 'Эрих Мария Ремарк', 1564.5, 10);
delete from book where count = 0;
update book set price = 736.6 where name = 'Мастер и Маргарита';

--2 транзакция
update book set price = 736.6 where name = 'Мастер и Маргарита';

--первая транзакция
commit;

--2 транзакция: вывод об ошибке





