-- Test fixture users.  Note how we stay well away from the autogenerated ID space.
insert into serviceuser(id, active, challenge, email, login) values(1000000, true, 'password', 'admin@eccms.test', 'admin');
insert into userrole(id, rolename, login) values(1000000, 'HEADMASTER', 'admin');

insert into serviceuser(id, active, challenge, email, login) values(1000001, true, 'password-noroles', 'noroles@eccms.test', 'noroles');

-- Test fixture items.
insert into item(id, active, name, sku, description, price) values(1000000, true, 'MacBook Air', 135, 'Computer thingy.', 1199.98);

-- Test fixture services.
insert into service(id, active, name, sku, description, price) values(1000000, true, 'MacBook Air repair', 135, 'Computer fixin.', 1199.98);