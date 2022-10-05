create table  cars (
id serial primary key,
name varchar(255),
body varchar(255),
engine varchar(255),
transmission varchar(255)
);

create table buyer (
    id serial primary key,
    name varchar(50),
    money float
);

create table shops (
    id serial primary key,
    name varchar(50),
    price float,
    cars_id integer references cars(id)
);

create table orders (
    id serial primary key,
    active boolean default true,
    buyer_id integer references buyer(id),
    shops_id integer references shops(id)
);

insert into cars (name, body, engine, transmission)
values ('Mercedes-Benz', 'Внедорожник', 'Бензиновые', 'Автоматическая'),
('kia optima', 'Седан', 'Бензиновые', 'Механическая'),
('Volkswagen Golf', 'Хэтчбек', 'Бензиновые', 'Роботизированная'),
('Audi R8 Spyder', 'Кабриолет', 'Бензиновые', 'Роботизированная');

insert into buyer (name, money)
values ('Иван Иванов', 900), ('Петр Петров', 200), ('Семен Семенов', 1500), ('Анна Соловьева', 700);

insert into shops (name, price, cars_id)
values  ('MEGA CARS', 534.3, 1), ('Auto', 674.3, 1), ('Dealer Cars', 325.3, 2), ('Major Expert', 756.6, 2);

insert into orders(buyer_id, shops_id)
values (1, 2), (1, 3), (1, 4), (2, 1), (2, 3), (2, 4), (3, 1), (3, 3), (4, 4);

select b.name Покупатель,
b.money as "Наличие денег",
s.name as "Название магазина",
s.price as "Цена автомобиля",
c.name as "Машина"
from shops s
left join  orders o on s.id = o.shops_id
left join  buyer b on b.id = o.buyer_id
left join cars c on c.id = s.cars_id
where b.money > s.price;

create view show_cars_which_buyer_can_afford
    as select b.name Покупатель,
b.money as "Наличие денег",
s.name as "Название магазина",
s.price as "Цена автомобиля",
c.name as "Машина"
from shops s
left join  orders o on s.id = o.shops_id
left join  buyer b on b.id = o.buyer_id
left join cars c on c.id = s.cars_id
where b.money > s.price;

select*from show_cars_which_buyer_can_afford;

