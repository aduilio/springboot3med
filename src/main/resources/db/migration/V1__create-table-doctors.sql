create table doctors(
	id bigint not null auto_increment,
	name varchar(100) not null,
	crm varchar(6) not null unique,
	email varchar(100) not null unique,
	phone varchar(20) not null,
	specialty varchar(100) not null,
	city varchar(100) not null,
	state varchar(100) not null,
	postal_code varchar(10) not null,
	street varchar(100) not null,
	number varchar(10) not null,
	
	primary key(id)
);