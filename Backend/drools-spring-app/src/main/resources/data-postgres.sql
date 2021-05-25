insert into registered_user(id, email, password, role)
values ('3b00204e-1897-4b6e-a175-5d0595833ced', 'nikolapekovic@gmail.com', 'sifra123', 'ADMIN');
insert into registered_user(id, email, password, role)
values ('71df3a81-dbe4-451a-b0e0-6849ea1339fb', 'petarpetrovic@gmail.com', 'sifra123', 'SELLER');
insert into registered_user(id, email, password, role)
values ('fed07d31-6f28-4ff4-ab3b-65f87e011e4d', 'vojatankosic@gmail.com', 'sifra123', 'SELLER');

insert into property(id, build_date, lat, lon, address, number_of_rooms, price_per_squarem, surface, owner_id)
values ('4d77ea76-fb81-46b5-b1dc-901a11982c41', '1990', 45.255192, 19.804807, 'Jovana Ducica 22', 3, 1400, 80, '71df3a81-dbe4-451a-b0e0-6849ea1339fb');
insert into property(id, build_date, lat, lon, address, number_of_rooms, price_per_squarem, surface, owner_id)
values ('32aae70c-f1bd-4da9-9adb-210ced4b361e', '2001', 45.245520, 19.829966, 'Doza Djerdja 39', 2, 1600, 42.3, 'fed07d31-6f28-4ff4-ab3b-65f87e011e4d');