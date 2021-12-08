insert into Customer(id, first_name, last_name) values (1001, 'John', 'Doe');
insert into Customer(id, first_name, last_name) values (1002, 'Jane', 'Doe');
insert into Phone_Number(id, customer_id, number, status) values (1001, 1001, '0411000111', 'inactive');
insert into Phone_Number(id, customer_id, number, status) values (1002, 1001, '0411000222', 'inactive');
insert into Phone_Number(id, customer_id, number, status) values (1003, 1002, '0411111111', 'inactive');