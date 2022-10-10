create table transport(
    id serial primary key,
    type varchar(50),
	name varchar(50),
	weight decimal(5,1),
	seats int
);
insert into transport (type, name, weight, seats)
values ('passenger car', 'audi', 543.5, 4), 
('passenger car', 'peugeot', 453.3, 4),
('truck', 'Hyundai', 900.5, 4);
insert into transport (type, name, weight, seats) 
values ('bus', 'kia', 323.0, 5);
update transport set name = 'volvo' where name = 'kia';
delete from  transport 
where name = 'volvo';
update transport set seats = 5;
select*from transport;
