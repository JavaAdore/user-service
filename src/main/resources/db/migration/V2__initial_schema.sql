 CREATE SEQUENCE base_user_pk_seq START WITH 50;

create table base_user (
       id int8 not null,
        birthdate timestamp,
        first_name varchar(255),
        gender varchar(255),
        last_name varchar(255),
        profile_picture_url varchar(255),
        primary key (id)
);
 
CREATE SEQUENCE user_address_pk_seq START WITH 50;  
create table user_address (
       id int8 not null,
        building_number varchar(255),
        is_default_address int4,
        is_deleted int4,
        distict varchar(255),
        floor_number int4,
        latitude float8,
        location_id int8,
        longitude float8,
        street_name varchar(255),
        user_id int8 not null,
        primary key (id)
    );

alter table user_address 
add constraint user_address_usr_id_fk 
foreign key (user_id) 
references base_user;