create table if not exists vacancy(
	id serial primary key,
	name varchar(2000),
	text varchar(10000),
	link varchar(1000)
);

create table if not exists last_update(
	id serial primary key,
	creation_date timestamp
);