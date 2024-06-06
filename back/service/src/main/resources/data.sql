
insert into app_user (active_flag, address, blocked_flag, email, last_name, name, password, phone, role)
values (true, 'Bulevar Oslobodjenja', false, 'nana@DEsi.com', 'Petrovic', 'Petar', '$2a$10$IXA3XB8wgTEXkJKIB5OCyOauVHACKU01elKgnVPcRMcXjZ56iZLEC', '0654324', 0);
insert into app_user (active_flag, address, blocked_flag, email, last_name, name, password, phone, role)
values (true, 'Bulevar Oslobodjenja', false, 'boki@DEsi.com', 'Petrovic', 'Bojan', '$2a$10$IXA3XB8wgTEXkJKIB5OCyOauVHACKU01elKgnVPcRMcXjZ56iZLEC', '0654324', 1);

INSERT INTO service_area (active_flag, available_flag, backup_flag, maximum_capacity, current_capacity)
VALUES (true, true, false, 100, 50);
-- #last_unavailable_timestamp

insert into client (id, service_area_id, premium) values (1, 1, false);
insert into adminn (id) values (2);

-- insert into passengers_rides (passenger_id, ride_id) values (1, 1);
-- insert into packages (parent, name, monthlyPrice, packageType, inOfferFlag)
-- values ();
--
-- insert into mobile_packages (id, minutes, internet, expiration)
-- values ();
--
-- insert into cabel_packages (id, chanelNumber) values ();
--
-- insert into internet_packages(id, internet, expiration) values ()