create table buyer (
    id serial primary key,
    name varchar(50),
    money float
);

create table shops (
    id serial primary key,
    name varchar(50),
    price float
);

create table  cars (
id serial primary key,
name varchar(255),
body varchar(255),
engine varchar(255),
transmission varchar(255),
shops_id integer references shops(id)
);

create table orders (
    id serial primary key,
    active boolean default true,
    buyer_id integer references buyer(id),
    cars_id integer references cars(id)
);

insert into shops (name, price)
values  ('MEGA CARS', 534.3), ('Auto', 674.3), ('Dealer Cars', 325.3), ('Major Expert', 756.6);

insert into cars (name, body, engine, transmission, shops_id)
values ('Mercedes-Benz', 'Внедорожник', 'Бензиновые', 'Автоматическая', 2),
('kia optima', 'Седан', 'Бензиновые', 'Механическая', 1),
('Volkswagen Golf', 'Хэтчбек', 'Бензиновые', 'Роботизированная', 4),
('Audi R8 Spyder', 'Кабриолет', 'Бензиновые', 'Роботизированная', 3);

insert into buyer (name, money)
values ('Иван Иванов', 900), ('Петр Петров', 200), ('Семен Семенов', 1500), ('Анна Соловьева', 700);

insert into orders(buyer_id, cars_id)
values (1, 2), (1, 3), (1, 4), (2, 1), (2, 3), (2, 4), (3, 1), (3, 3), (4, 4);

select b.name Покупатель,
b.money as "Наличие денег",
s.name as "Название магазина",
s.price as "Цена автомобиля",
c.name as "Машина"
from cars c
left join  orders o on c.id = o.cars_id
left join  buyer b on b.id = o.buyer_id
left join shops s on s.id = c.shops_id
where b.money > s.price;

create view show_cars_which_buyer_can_afford
    as select b.name Покупатель,
b.money as "Наличие денег",
s.name as "Название магазина",
s.price as "Цена автомобиля",
c.name as "Машина"
from cars c
left join  orders o on c.id = o.cars_id
left join  buyer b on b.id = o.buyer_id
left join shops s on s.id = c.shops_id
where b.money > s.price;

select*from show_cars_which_buyer_can_afford;

