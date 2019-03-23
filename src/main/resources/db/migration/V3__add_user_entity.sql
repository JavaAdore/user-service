CREATE SEQUENCE user_entity_pk_seq START WITH 50;  
create table user_entity
(
       id int8 not null,
       user_id int8 not null,
       entity_id int8 not null
);
       