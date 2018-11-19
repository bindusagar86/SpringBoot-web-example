drop table users;
drop table WIDGET;
--drop table users_widgets;

CREATE TABLE users (
  id        INTEGER(10) UNSIGNED IDENTITY PRIMARY KEY,
  firstname VARCHAR(30) NOT NULL,
  lastname	varchar(30) not null,
  email		varchar(45) not null,
  dob		date(10)	not null,
  password	varchar(30) not null 
);
  
  
CREATE TABLE WIDGET (
	ID INTEGER(10) UNSIGNED IDENTITY PRIMARY KEY,
	DESCRIPTION VARCHAR(255) NOT NULL,
	NAME VARCHAR(30) NOT NULL,
	CREATED_DATE TIMESTAMP NOT NULL,
	user_id integer(10) not null
);

ALTER TABLE WIDGET ADD CONSTRAINT fk_widget_users FOREIGN KEY (user_id) REFERENCES users (id);
--CREATE TABLE users_widgets (
--	USER_ID INTEGER(10) UNSIGNED REFERENCES users (id),
--	WIDGET_ID INTEGER(10) UNSIGNED REFERENCES widget (id)
--);


