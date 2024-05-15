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
    vehicle_number character not null ,
    booking_id uuid not null ,
    constraint
)