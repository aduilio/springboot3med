create table appointments(
	id bigint not null auto_increment,
    doctor_id bigint not null,
	patient_name varchar(100) not null,
	patient_phone varchar(20) not null,
    date datetime not null,
	
	primary key(id),
    constraint fk_appointments_doctor_id foreign key(doctor_id) references doctors(id)
);