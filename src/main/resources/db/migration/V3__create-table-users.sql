create table users(
	id bigint not null auto_increment,
	login_user varchar(100) not null,
	login_code varchar(255) not null,
	
	primary key(id)
);

insert into users (login_user, login_code) values ('user', '$2a$12$WNVPP0eBtdmZmK/aCP.feuNMBRYQX9t4HMG1WN21ngv0b/tYQmEfe');