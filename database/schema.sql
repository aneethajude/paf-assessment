create database bgg;
use bgg;

CREATE TABLE user (
    user_id varchar(8),
    username varchar(30) NOT NULL,
    name varchar(50),
    constraint user_pk primary key(user_id)
);
INSERT INTO user (user_id, username, name)
VALUES 
('1b80114c', 'fred', 'Fred'),
('cf66dae3', 'wilma', 'Wilma'),
('a8b9800d', 'barney', 'Barney'),
('66223e28', 'betty', 'Betty');

create table task (
	task_id int auto_increment,
    user_id varchar(8),
	description varchar(255),
	priority int,
	due_date date,
	primary key(task_id),
	constraint fk_user_id foreign key(user_id)
		references user(user_id)
);