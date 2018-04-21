DROP DATABASE IF EXISTS expense;
CREATE DATABASE expense; 

USE expense;

Create Table expense(
	id integer auto_increment,
	dateOfExp date,
	amount float,
	description varchar(255),
	Primary Key(id)
	);