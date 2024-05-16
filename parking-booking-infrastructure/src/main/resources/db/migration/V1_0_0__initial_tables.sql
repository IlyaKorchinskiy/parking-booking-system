create schema if not exists booking;

create
    extension if not exists "uuid-ossp";

create table booking.bookings
(
    id         uuid not null,
    parking_id uuid not null,
    user_id    uuid not null,
    constraint bookings_pkey primary key (id)
);

create table booking.booking_items
(
    start_date_time timestamp with time zone not null,
    vehicle_number  varchar(7)               not null,
    booking_id      uuid                     not null,
    constraint booking_items_pkey primary key (start_date_time, vehicle_number),
    constraint bookings_fk foreign key (booking_id) references bookings (id) on delete cascade
);

create table booking.parkings
(
    id           uuid          not null,
    address      varchar(50)   not null,
    coordinate_x numeric(8, 6) not null,
    coordinate_y numeric(8, 6) not null,
    total_places smallint      not null,
    constraint parkings_pkey primary key (id)
);

create table booking.slots
(
    start_date_time timestamp with time zone not null,
    parking_id      uuid                     not null,
    available       smallint                 not null,
    constraint slots_pkey primary key (start_date_time, parking_id),
    constraint parkings_fk foreign key (parking_id) references parkings (id)
);