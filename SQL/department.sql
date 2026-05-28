drop  table if exist department;

create table department (
    department_id integer primary key  auto_increment,
    department_name varchar(30)
);

insert into department (department_name) values ("開発部");
insert into department (department_name) values ("営業部");
insert into department (department_name) values ("事務部");

