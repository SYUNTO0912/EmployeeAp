drop  table if exists employees;

create table employees(
    employee_id integer primary key auto_increment,
    employee_name varchar(30),
    age integer,
    department_id integer
);

insert into employees (employee_name, age, department_id) values ("坂本",24,1);
insert into employees (employee_name, age, department_id) values ("田中",24,2);
insert into employees (employee_name, age, department_id) values ("小畠",22,1);
insert into employees (employee_name, age, department_id) values ("原田",21,3);
insert into employees (employee_name, age, department_id) values ("高村",28,1);