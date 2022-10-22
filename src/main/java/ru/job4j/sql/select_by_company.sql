create TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

create TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company (id, name)
values (1, 'apple'), (2, 'microsoft'), (3,'xiaomi');


insert into person (id, name, company_id)
values (1, 'john', 1), (2, 'mark', 1), (3,'masha', 3), (4,'ivan', 2);

-- В одном запросе получить
--- имена всех person, которые не состоят в компании с id = 5;
--- название компании для каждого человека.

select p.name, c.name
from person p
join company c on p.company_id=c.id
where p.company_id!=5;

--Необходимо выбрать название компании с максимальным количеством человек + количество человек в этой компании
--нужно учесть, что таких компаний может быть несколько, и вывести надо информацию о каждой компании.

select c.name, count(p.company_id) maximum
from person p
join company c on p.company_id=c.id
group by c.name
having COUNT(p.id) = (
 	select max(m.max_id)
 	from (
 		select company_id, count(id) max_id
 		from person
 		group by company_id
 	) m);

