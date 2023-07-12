CREATE table if not exists users (
  id bigint primary key not null ,
  first_name varchar(30) not null ,
  last_name varchar(30) not null ,
  date_of_birth date not null ,
  phone_number varchar(20) not null ,
  email varchar(50) not null

);