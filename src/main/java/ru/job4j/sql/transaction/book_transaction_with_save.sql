--username=postgres
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

begin transaction;

insert into book(name, author, price, count)
values ('Триумфальная Арка', 'Эрих Мария Ремарк', 1564.5, 10);
delete from book where count = 0;
update book set price = 736.6 where name = 'Мастер и Маргарита';

insert into book(name, author, price, count)
values ('1984', 'Джордж Оруэлл', 564.5, 5);

commit;

select * from book;

begin transaction;

delete from book;

update book set name = 'Собчаье сердце' where name = 'Мастер и Маргарита';

rollback transaction;

select * from book;

begin transaction;

insert into book(name, author, price, count)
values ('Три товарища', 'Эрих Мария Ремарк', 2064.5, 5);

savepoint first_savepoint;

delete from book where name = 'Анна Каренина';
update book set price = 345 where name = 'Три товарища';

select * from book;

rollback to first_savepoint;

select * from book;

savepoint second_savepoint;

insert into book(name, author, price, count)
values ('Ведьмак', 'Анджей Сапковский', 6521.5, 100);
delete from book where name = 'Мастер и Маргарита';

select * from book;

rollback to second_savepoint;

select * from book;

commit transaction;