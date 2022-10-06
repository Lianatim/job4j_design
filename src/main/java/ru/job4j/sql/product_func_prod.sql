create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

--хранимая процедура, которая позволит вставлять данные в эту таблицу.
create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
    insert into products (name, producer, count, price)
    values (i_name, prod, i_count, i_price);
    END
$$;

call insert_data('product_2', 'producer_2', 15, 32);

--процедура для обновления данных в таблице.
create or replace procedure update_data(u_count integer, tax float, u_id integer)
language 'plpgsql'
as $$
    BEGIN
        if u_count > 0 THEN
            update products set count = count - u_count where id = u_id;
        end if;
        if tax > 0 THEN
            update products set price = price + price * tax;
        end if;
    END;
$$;

call update_data(10, 0, 1);

call insert_data('product_1', 'producer_1', 3, 50);
call insert_data('product_3', 'producer_3', 8, 115);

call update_data(0, 0.2, 0);



--процедура для удаления данных в таблице.
create or replace procedure delete_data(d_id integer)
language 'plpgsql'
as $$
   BEGIN
      if d_id > 5 THEN
          delete from products where id = 3;
      end if;
   END;
$$;

call delete_data(2);

call insert_data('product_4', 'producer_4', 5, 100);
call insert_data('product_5', 'producer_5', 4, 565);

call delete_data(10);


delete from products;
ALTER SEQUENCE products_id_seq RESTART WITH 1;

select * from products;

create or replace function f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
returns void
language 'plpgsql'
as
$$
    begin
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    end;
$$;

select f_insert_data('product_1', 'producer_1', 25, 50);

--процедура для редактирования записей в таблице на функцию
create or replace function f_update_data(u_count integer, tax float, u_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        if u_count > 0 THEN
            update products set count = count - u_count where id = u_id;
            select into result count from products where id = u_id;
        end if;
        if tax > 0 THEN
            update products set price = price + price * tax;
            select into result sum(price) from products;
        end if;
        return result;
    end;
$$;

select f_update_data(10, 0, 1);
select f_insert_data('product_2', 'producer_2', 15, 32);
select f_insert_data('product_3', 'producer_3', 8, 115);
select f_update_data(0, 0.2, 0);

--процедура для удаления записей в таблице на функцию
create or replace function d_delete_data(d_count integer, d_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        if d_count = 0 THEN
             delete from products where count=0;
        end if;
		return result;
    end;
$$;

select d_delete_data(5, 0);
select f_insert_data('product_5', 'producer_6', 0, 32);
select * from products;
select d_delete_data(0, 0);
select * from products;
