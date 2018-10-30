-- main tables

create table if not exists roles(
	id serial primary key,
	name varchar(100)
);

create table if not exists users(
	id serial primary key,
	name varchar(100),
	password varchar(100),
	role int references roles(id)
);

create table if not exists rules(
	id serial primary key,
	description varchar(5000)
);

create table if not exists states(
	id serial primary key,
	state varchar(100)
);

create table if not exists categories(
	id serial primary key,
	category varchar(100)
);

create table if not exists items(
	id serial primary key,
	author int references users(id),
	title varchar(1000),
	description varchar(10000),
	creation_date timestamp,
	state int references states(id),
	category int references categories(id)
);

create table if not exists comments(
	id serial primary key,
	item int references items(id),
	author int references users(id),
	comment varchar(5000),
	creation_date timestamp
);

create table if not exists attaches(
	id serial primary key,
	item int references items(id),
	-- comments can also have attachments
	comment int references comments(id),
	name varchar(100),
	path varchar(256)
);

--help tables

create table if not exists roles_rules(
	id serial primary key,
	role_id int references roles(id),
	rule_id int references rules(id)
);