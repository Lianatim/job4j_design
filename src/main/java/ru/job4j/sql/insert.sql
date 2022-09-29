insert into role(name) values('Java junior');
insert into users(name, role_id) values('Иван Иванов', 1);
insert into rules(name) values('Знание Spring');
insert into category(name) values('Высший приоритет');
insert into state(name) values('На расмотрении');
insert into item(number, users_id, category_id, state_id) values(1, 1, 1, 1);
insert into comments(comment, item_id) values('Разговорный английский', 1);
insert into attachs(attach, item_id) values('Резюме', 1);
insert into role_rules(role_id, rules_id) values(1, 1);