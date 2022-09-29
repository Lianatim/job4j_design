create table users(
     id serial primary key,
     name varchar(255)
 );
 
 create table phones(
     id serial primary key,
     name varchar(255)
 );
 
 create table users_phones(
     id serial primary key,
     users_id int references users (id),
     phones_id int references phones (id)
 );
 
 insert into users(name) values ('Sasha');
insert into users(name) values ('Mike');
insert into users(name) values ('July');
insert into users(name) values ('Maddy');

insert into phones(name) values ('Iphone 14');
insert into phones(name) values ('Samsung');
insert into phones(name) values ('Fly');
insert into phones(name) values ('Panasonic');

insert into users_phones (users_id, phones_id) values (1, 1);
insert into users_phones (users_id, phones_id) values (1, 2);
insert into users_phones (users_id, phones_id) values (2, 3);
insert into users_phones (users_id, phones_id) values (2, 4);
insert into users_phones (users_id, phones_id) values (3, 1);
insert into users_phones (users_id, phones_id) values (4, 2);

select * from users;
select * from phones;
select * from users_phones;