-- auto-generated definition
create table programmer
(
    id         serial not null
        constraint programmer_pkey
            primary key,
    first_name varchar(20),
    last_name  varchar(20),
    age        integer
);

-- auto-generated definition
create table devices
(
    idstock       serial not null
        constraint devices_pkey
            primary key,
    device        varchar(20),
    programmer_id integer
        constraint devices_programmer_id_fkey
            references programmer
);

INSERT INTO public.programmer (id, first_name, last_name, age) VALUES (1, 'Илья', 'Савкин', 23);
INSERT INTO public.programmer (id, first_name, last_name, age) VALUES (2, 'Илья', 'Латкин', 23);
INSERT INTO public.programmer (id, first_name, last_name, age) VALUES (4, 'Олег', 'Нелюбитхолод', 23);
INSERT INTO public.programmer (id, first_name, last_name, age) VALUES (5, 'Лера', 'Андревич', 23);
INSERT INTO public.programmer (id, first_name, last_name, age) VALUES (6, 'Павел', 'Грохов', 24);
INSERT INTO public.programmer (id, first_name, last_name, age) VALUES (7, 'Иван', 'Иванов', 30);
INSERT INTO public.programmer (id, first_name, last_name, age) VALUES (3, 'Михаил', 'Андревич', 24);
INSERT INTO public.programmer (id, first_name, last_name, age) VALUES (11, 'JOpa', 'vvvvv vvvvvv vvvv', 20);
INSERT INTO public.programmer (id, first_name, last_name, age) VALUES (12, 'Gena', 'Bukin', 54);

INSERT INTO public.devices (id, device, programmer_id) VALUES (1, 'Macbook', 3);
INSERT INTO public.devices (id, device, programmer_id) VALUES (2, 'Windows 10 PC', 2);
INSERT INTO public.devices (id, device, programmer_id) VALUES (3, 'Linux Neochen', 2);
INSERT INTO public.devices (id, device, programmer_id) VALUES (4, 'Graphical tablet', 5);
INSERT INTO public.devices (id, device, programmer_id) VALUES (5, 'Iphone 10', 3);
INSERT INTO public.devices (id, device, programmer_id) VALUES (6, 'Warm oil radiator', 4);
INSERT INTO public.devices (id, device, programmer_id) VALUES (7, 'Krisa OS PC', 7);
INSERT INTO public.devices (id, device, programmer_id) VALUES (8, 'Xiomi redmi 5x', 1);
INSERT INTO public.devices (id, device, programmer_id) VALUES (9, 'Headphones Sony 9000', null);
INSERT INTO public.devices (id, device, programmer_id) VALUES (10, 'Windows XP Laptop', 6);