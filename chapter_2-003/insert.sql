delete from items;
delete from categories;
delete from states;
delete from roles_rules;
delete from rules;
delete from users;
delete from roles;

insert into roles(name) values('admin');
insert into roles(name) values('user');

insert into users(name, password, role) values('gsmirnov', '123', '1');
insert into users(name, password, role) values('akaleganov', '123', '2');
insert into users(name, password, role) values('guest', '123', '2');

insert into states(state) values('accepted');
insert into states(state) values('analyze');
insert into states(state) values('in work');
insert into states(state) values('done');
insert into states(state) values('new');

insert into categories(category) values('normal');
insert into categories(category) values('urgent');
insert into categories(category) values('critical');
insert into categories(category) values('default');

insert into items(author, title, description, creation_date, state, category)
values('1', 'test', 'test description', '2018-10-20 00:00:00','1', '1');

insert into items(author, title, description, creation_date, state, category)
values('3', 'test2', 'test description 2', '2018-10-25 00:00:00','3', '1');

insert into items(author, title, description, creation_date, state, category)
values('3', 'test3', 'test description 3', '2018-10-22 00:00:00','1', '3');

insert into items(author, title, description, creation_date, state, category)
values('1', 'request1', 'request description 1', '2018-10-28 00:00:00','5', '4');

insert into items(author, title, description, creation_date, state, category)
values('4', 'request', 'request description', '2018-10-27 00:00:00','5', '4');