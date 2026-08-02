create table EVENTS (
    id bigserial not null,
    type varchar(30) not null,
    name varchar(100) not null,
    description varchar(200) not null,
    date date,

    primary key(id)
);