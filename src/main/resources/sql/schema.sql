create table if not exists users (
    id serial primary key,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    email varchar(100) unique not null,
    password varchar not null
);

create table if not exists authentications (
   id serial primary key,
   date timestamp not null,
   ip varchar(64) not null,
   user_id integer references users (id)
);