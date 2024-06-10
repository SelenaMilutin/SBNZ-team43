-- ALTER TABLE app_user ADD CONSTRAINT email UNIQUE (email);


insert into app_user (active_flag, address, blocked_flag, email, last_name, name, password, phone, role)
values (true, 'Bulevar Oslobodjenja', false, 'nana@DEsi.com', 'Petrovic', 'Petar', '$2a$10$IXA3XB8wgTEXkJKIB5OCyOauVHACKU01elKgnVPcRMcXjZ56iZLEC', '0654324', 0)
    ON CONFLICT (email) DO NOTHING;
insert into app_user (active_flag, address, blocked_flag, email, last_name, name, password, phone, role)
values (true, 'Bulevar Oslobodjenja', false, 'boki@DEsi.com', 'Petrovic', 'Bojan', '$2a$10$IXA3XB8wgTEXkJKIB5OCyOauVHACKU01elKgnVPcRMcXjZ56iZLEC', '0654324', 1)
    ON CONFLICT (email) DO NOTHING;

INSERT INTO service_area (id, active_flag, available_flag, backup_flag, maximum_capacity, current_capacity)
VALUES (1, true, true, false, 100, 1) ON CONFLICT (id) DO NOTHING; -- jedan da jeste jedan da nije
INSERT INTO service_area (id, active_flag, available_flag, backup_flag, maximum_capacity, current_capacity)
VALUES (2, false, false, true, 100, 0) ON CONFLICT (id) DO NOTHING;
-- #last_unavailable_timestamp

insert into client (id, service_area_id, premium) values (1, 1, false) ON CONFLICT (id) DO NOTHING;;
insert into adminn (id) values (2) ON CONFLICT (id) DO NOTHING;

-- TRUNCATE TABLE mobile_packages;
-- TRUNCATE TABLE internet_packages;
-- TRUNCATE TABLE cable_packages;
TRUNCATE TABLE packages CASCADE;
TRUNCATE TABLE mobile_packages;
TRUNCATE TABLE internet_packages;
TRUNCATE TABLE cable_packages;
ALTER SEQUENCE packages_id_seq RESTART WITH 1;

-- insert into passengers_rides (passenger_id, ride_id) values (1, 1);
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (NULL, 'All internet packages', 49.99, 2, true);
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (NULL, 'All mobile packages', 49.99, 0, true);
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (NULL, 'All cable packages', 49.99, 1, true);

INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (2, 'Postpejd', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (2, 'Pripejd', 49.99, 0, true); --5
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (2, 'Rooming', 49.99, 0, true); --6
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (4, 'Tariff', 49.99, 0, true);  --7
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (4, 'Tariff dodaci', 49.99, 0, true); --8
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (4, 'Mobilni internet', 49.99, 0, true); --9
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (7, 'Standardni', 49.99, 0, true); --10
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (7, 'Generacijski', 49.99, 0, true); --11
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (7, 'Sokolovic', 49.99, 0, true); --12
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (8, 'Internet', 49.99, 0, true); --13
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (8, 'Deljeni net', 49.99, 0, true); --14
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (8, 'Drustvene mreze i aplikacije', 49.99, 0, true);
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (8, 'Razgovori', 49.99, 0, true); --16
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (9, 'Net kilo', 49.99, 0, true); --17
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (9, 'Net mega', 49.99, 0, true); --18
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (9, 'Net giga', 49.99, 0, true); --19
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (9, 'Net tera', 49.99, 0, true); --20
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (10, 'Morava', 49.99, 0, true); --21
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (10, 'Omorika', 49.99, 0, true); --22
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (10, 'Soko', 49.99, 0, true); --23
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (11, 'Kontrola toka', 49.99, 0, true); --24
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (11, 'Junior', 49.99, 0, true);  --25
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (11, 'Senior', 49.99, 0, true);  --26
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (12, 'Sokolovic', 49.99, 0, true);  --27
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (13, 'Net 500MB', 49.99, 2, true);
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (13, 'Net 3BG', 49.99, 0, true);
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (13, 'Net 10GB', 49.99, 1, true); --30
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (14, 'Deljeni net 12GB', 49.99, 2, true);
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (14, 'Deljeni net 24GB', 49.99, 0, true);
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (15, 'Viber', 49.99, 1, true);
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (15, 'Facebook, Instagram, WhatsApp, Twitter', 49.99, 2, true);
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (15, 'Youtube i Twitch', 49.99, 0, true);
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (16, 'Medjunarodni minuti', 49.99, 1, true); --36
VALUES (5, 'Tariff', 49.99, 0, true);  --37
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (5, 'Tariff dodaci', 49.99, 0, true); --38
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (5, 'Mobilni internet', 49.99, 0, true); --39
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (37, 'Bonus dobrodoslice', 49.99, 0, true); --40
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (37, 'Bonus mesecni plan', 49.99, 0, true); --41
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (38, 'Plan 100 i 200', 49.99, 0, true); --42
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (38, 'Pripejd internet', 49.99, 0, true); --43
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (38, 'Povoljni razgovori', 49.99, 0, true); --44
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (38, 'Domaci minuti i poruke', 49.99, 0, true); --45
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (38, 'Turist sim', 49.99, 0, true); --46
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (39, '1GB + 200MB na 1 dan', 49.99, 0, true); --47
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (39, '1GB + 500MB na 7 dan', 49.99, 0, true); --48
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (39, '1GB + 700MB na 7 dan', 49.99, 0, true); --49
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (39, '4GB + 1GB na 7 dan', 49.99, 0, true); --50
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (39, '6GB + 1,5GB na 10 dan', 49.99, 0, true); --51
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (40, 'Welcome 1', 49.99, 0, true); --52
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (40, 'Welcome 2', 49.99, 0, true); --53
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (40, 'Welcome 3', 49.99, 0, true); --54
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (41, 'Mesoviti bonus 1', 49.99, 0, true); --55
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (41, 'Mesoviti bonus 2', 49.99, 0, true); --56
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (42, 'Plan 100', 49.99, 0, true); --57
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (42, 'Plan 200', 49.99, 0, true); --58
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (42, 'Dodatak uz plan 100', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (42, 'Dodatak uz plan 200', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (43, '24GB na 30 dana', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (43, '50GB na 30 dana', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (43, '100GB na 60 dana', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (44, 'Tri omiljena broja', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (44, 'Pripejd najdrazi broj', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (45, 'Kombo mix 50', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (45, 'Kombo mix 100', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (45, 'Kombo mix komplet', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (46, '3GB', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (46, '5GB', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (46, 'Super', 49.99, 0, true); --70
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (6, 'Postpejd roming', 49.99, 0, true); --71
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (6, 'Pripejd roming', 49.99, 0, true); --72
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (71, 'Roming internet', 49.99, 0, true); --73
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (71, 'Roming pricaj', 49.99, 0, true); --74
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (71, 'TEL roming', 49.99, 0, true); --75
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (72, 'Roming internet', 49.99, 0, true); --76
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (72, 'Roming pricaj', 49.99, 0, true); --77
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (72, 'TEL roming', 49.99, 0, true); --78
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (73, 'Evropa net 1', 49.99, 0, true); --79
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (73, 'Planeta net 1', 49.99, 0, true); --80
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (73, 'Evropa net 2', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (73, 'Planeta net 2', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (73, 'Evropa net 3', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (73, 'Planeta net 3', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (74, 'Evropa pricaj 1', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (74, 'Planeta pricaj 1', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (74, 'Evropa pricaj 2', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (74, 'Planeta pricaj 2', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (74, 'Evropa pricaj 3', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (74, 'Planeta pricaj 3', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (75, 'Plus 10', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (75, 'Plus 20', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (76, 'Evropa net 1', 49.99, 0, true); --79
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (76, 'Planeta net 1', 49.99, 0, true); --80
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (76, 'Evropa net 2', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (76, 'Planeta net 2', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (76, 'Evropa net 3', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (76, 'Planeta net 3', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (77, 'Evropa pricaj 1', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (77, 'Planeta pricaj 1', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (77, 'Evropa pricaj 2', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (77, 'Planeta pricaj 2', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (77, 'Evropa pricaj 3', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (77, 'Planeta pricaj 3', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (78, 'Plus 10', 49.99, 0, true); --
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (78, 'Plus 20', 49.99, 0, true); --106 KRAJ MOBILNIH
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (3, 'Iris', 49.99, 0, true); --107
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (3, 'Cloud', 49.99, 0, true); --108
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (107, 'Iris paketi', 49.99, 0, true); --109
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (107, 'Iris uz dopune', 49.99, 0, true); --110
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (109, 'Start TV', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (109, 'Plus TV', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (109, 'Max TV', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (110, 'Red ponuda', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (110, 'Blue ponuda', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (110, 'White ponuda', 49.99, 0, true); --116
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (108, 'Sportski paketi', 49.99, 0, true); --117
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (108, 'Porodicni paketi', 49.99, 0, true); --118
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (117, 'Sport light', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (117, 'Sport plus', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (117, 'Sport max', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (118, 'Porodicni light', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (118, 'Porodicni plus', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (118, 'Porodicni max', 49.99, 0, true); --124 KRAJ KABLOVKE
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (1, 'Kucni internet', 49.99, 0, true); --125
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (1, 'Mobilni internet', 49.99, 0, true); --126
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (1, 'Usluge', 49.99, 0, true); --127
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (125, 'Net 20', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (125, 'Net 50', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (126, 'Postpejd', 49.99, 0, true); --130
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (126, 'Pripejd', 49.99, 0, true); --131
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (126, 'Putuj Evropom', 49.99, 0, true); --132
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (130, 'Net plus', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (130, 'Net Max', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (131, '1GB na 1 dan', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (131, '1GB na 10 dana', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (131, '4GB na 7 dana', 49.99, 0, true); --137
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (127, 'Hosting', 49.99, 0, true); --138
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (127, 'Registracija domena', 49.99, 0, true); --139
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (138, 'Web Hosting', 49.99, 0, true); --140
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (138, 'Mail hosting', 49.99, 0, true); --141
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (140, 'Host light', 49.99, 0, true); --142
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (140, 'Host basic', 49.99, 0, true); --143
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (140, 'Host Advanced', 49.99, 0, true); --144
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (140, 'Host Large', 49.99, 0, true); --145
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (142, '120 RSD', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (142, '240 RSD', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (142, '360 RSD', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (143, '120 RSD', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (143, '240 RSD', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (143, '360 RSD', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (144, '120 RSD', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (144, '240 RSD', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (144, '360 RSD', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (145, '120 RSD', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (145, '240 RSD', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (145, '360 RSD', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (141, 'L50-5', 49.99, 0, true); --4
INSERT INTO packages (parent_id, name, monthly_price, package_type, in_offer_flag)
VALUES (141, 'L50-10', 49.99, 0, true); --159 KRAJ INTERNETA

insert into mobile_packages (id, minutes, internet, expiration) values (2, 0, 0, 0);

insert into cable_packages (id, chanel_number) values (3, 0);

insert into internet_packages(id, internet, expiration) values (1, 0, 0);

INSERT INTO contract (start_date, expiration_date, active_flag, client_id, packages_id, discount, proccessed_flag) VALUES ('2022-06-08 14:30:00', '2024-12-08 14:30:00', true, 1, 21, 0.0, true);
