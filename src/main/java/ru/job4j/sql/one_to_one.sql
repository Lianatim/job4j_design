create table passwords(
    id serial primary key,
    text varchar(255)
);

create table login(
    id serial primary key,
    name varchar(255),
    passwords_id int references passwords(id) unique
);

insert into passwords(text) values ('qwerty');
insert into login(name, passwords_id) VALUES ('user007', 1);

select * from login;