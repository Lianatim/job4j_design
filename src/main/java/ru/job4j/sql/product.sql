create table type(
id serial primary key,
name varchar(255)
);

create table product(
id serial primary key,
name varchar(255),
type_id int references type(id),
expired_date date,
price float
);

insert into type(name)
values ('мясо'), ('рыба'), ('кисломолочные'), ('выпечка'), ('сладости');

insert into product(name, type_id, expired_date, price)
values ('курица', 1, '10-02-2022', 205), ('говядина', 1, '10-02-2022', 305),
('лосось', 2, '09-28-2022', 633), ('сыр адыгейский', 3, '10-02-2022', 120),
('сыр моцарелла', 3, '10-03-2022', 220), ('сыр камабер', 3, '09-28-2022', 250),
('сыр плавленый', 3, '09-29-2022', 120), ('круассан', 4, '09-29-2022', 70),
('мороженое', 5, '10-02-2022', 120), ('круассан', 4, '09-28-2022', 120);

--Написать запрос получение всех продуктов с типом "кисломолочные"
select t.name,  pr.name
from product pr join type t on pr.type_id=t.id
where t.name = 'кисломолочные';

--Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое" :
select * from product where name like  '%мороженое%';

--Написать запрос, который выводит все продукты, срок годности которых уже истек
select * from product where expired_date < '10-01-2022';

--Написать запрос, который выводит самый дорогой продукт.
--Запрос должен быть универсальный и находить все продукты с максимальной ценой.
select name, price
from product p
where price = (select max(price) from product);

-- Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих.
--В виде имя_типа, количество
select t.name as "Тип продукта",
count(pr.name) Количество
from product pr
join type t on pr.type_id=t.id
group by t.name;

--Написать запрос получение всех продуктов с типом "кисломолочные" и "выпечка"
select pr.name as "Название продукта",
t.name "Тип продукта" from product pr
join type t on pr.type_id=t.id
where t.name = 'кисломолочные' or t.name ='выпечка';

--Написать запрос, который выводит тип продуктов, которых осталось меньше 2 штук.
select t.name as "Тип продукта", count(t.name) Количество
from product pr
join type t on pr.type_id=t.id
group by t.name
having count(t.name) < 2;

--Вывести все продукты и их тип.
select pr.name as "Название продукта", t.name as "Тип продукта",
pr.expired_date as "Срок годности", pr.price Цена
from product pr
join type t on pr.type_id=t.id;
