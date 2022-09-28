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
