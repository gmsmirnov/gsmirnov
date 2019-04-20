drop table if exists users_roles cascade;
drop table if exists users cascade;
drop table if exists roles cascade;
drop table if exists city cascade;
drop table if exists region cascade;
drop table if exists country cascade;

-- main tables

create table users (
	id serial primary key,
	login varchar(30),
	email varchar(50),
	password varchar(30),
	country varchar(30),
	city varchar(30)
);

create table roles (
	id serial primary key,
	role varchar(30)
);

--help tables

create table users_roles (
	id serial primary key,
	user_id int references users(id),
	role_id int references roles(id)
);

-- countries, regions, cities tables

create table country (
	id			bigint not null primary key,
	name		character varying(128) not null
);

create table region (
	id			bigint not null primary key,
	country_id	bigint not null references country (id),
	name		character varying(128) not null
);

create table city (
	id			bigserial not null primary key,
	region_id	bigint not null references region (id),
	name		character varying(128) not null
);

create or replace view country_city as
	(select city.name as city_name, country.name as country_name
	 from city join region on city.region_id = region.id join country on country.id = region.country_id);