create table users(
	id bigint not null auto_increment,
	login_user varchar(100) not null,
	login_code varchar(255) not null,
	
	primary key(id)
);