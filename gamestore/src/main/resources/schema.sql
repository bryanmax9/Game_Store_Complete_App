drop database if exists game_store;
create database game_store;
use game_store;

create table game (
                      game_id int primary key auto_increment,
                      title varchar(50) not null,
                      esrb_rating varchar(50) not null,
                      description varchar(255) not null,
                      price decimal(5, 2) not null,
                      studio varchar(50) not null,
                      quantity int
);

create table console (
                         console_id int primary key auto_increment,
                         model varchar(50) not null,
                         manufacturer varchar(50) not null,
                         memory_amount varchar(20),
                         processor varchar(20),
                         price decimal(5, 2) not null,
                         quantity int not null
);

create table tshirt (
                        tshirt_id int primary key auto_increment,
                        size varchar(20) not null,
                        color varchar(20) not null,
                        description varchar(255) not null,
                        price decimal(5,2) not null,
                        quantity int not null
);

create table invoice (
                         invoice_id int primary key auto_increment,
                         name varchar(50) not null,
                         street varchar(100) null,
                         city varchar(50) not null,
                         state varchar(20) not null,
                         zipcode varchar(10) null,
                         item_type varchar(50) not null,
                         item_id int not null, -- links to either game, console, or t_shirt ids
                         unit_price decimal(8,2) not null,
                         quantity int not null,
                         subtotal decimal(8,2) not null,
                         tax decimal(8,2) not null,
                         processing_fee decimal(8,2) not null,
                         total decimal(8,2) not null
);

create table fee (
                     product_type varchar(50) primary key,
                     fee decimal(8,2) not null
);

create table tax (
                     state char(2) primary key,
                     rate decimal(8,2) not null
);



-- New Schema with URL images and video implemented
drop database if exists game_store;
create database game_store;
use game_store;

create table game (
                      game_id int primary key auto_increment,
                      title varchar(50) not null,
                      esrb_rating varchar(50) not null,
                      description varchar(255) not null,
                      price decimal(5, 2) not null,
                      studio varchar(50) not null,
                      quantity int,
                      image_url varchar(255),
                      youtube_id varchar(50) not null
);

create table console (
                         console_id int primary key auto_increment,
                         model varchar(50) not null,
                         manufacturer varchar(50) not null,
                         memory_amount varchar(20),
                         processor varchar(20),
                         price decimal(5, 2) not null,
                         quantity int not null,
                         image_url varchar(255),
                         youtube_id varchar(50) not null
);

create table tshirt (
                        tshirt_id int primary key auto_increment,
                        size varchar(20) not null,
                        color varchar(20) not null,
                        description varchar(255) not null,
                        price decimal(5,2) not null,
                        quantity int not null,
                        youtube_id varchar(50) not null
);


create table invoice (
                         invoice_id int primary key auto_increment,
                         name varchar(50) not null,
                         street varchar(100) null,
                         city varchar(50) not null,
                         state varchar(20) not null,
                         zipcode varchar(10) null,
                         item_type varchar(50) not null,
                         item_id int not null, -- links to either game, console, or t_shirt ids
                         unit_price decimal(8,2) not null,
                         quantity int not null,
                         subtotal decimal(8,2) not null,
                         tax decimal(8,2) not null,
                         processing_fee decimal(8,2) not null,
                         total decimal(8,2) not null
);

create table fee (
                     product_type varchar(50) primary key,
                     fee decimal(8,2) not null
);

create table tax (
                     state char(2) primary key,
                     rate decimal(8,2) not null
);
insert into fee
(product_type, fee)
values ('Console', 14.99),('Game', 1.49),('T-Shirt', 1.98);
insert into tax
(state, rate)
values ('AK',0.06),('AL',0.05),('AR',0.06),('AZ',0.04),('CA',0.06),('CO',0.04),('CT',0.03),('DE',0.05),('FL',0.06),('GA',0.07),('HI',0.05),('IA',0.04),('ID',0.03),('IL',0.05),('IN',0.05),('KS',0.06),('KY',0.04),('LA',0.05),('MA',0.05),('MD',0.07),('ME',0.03),('MI',0.06),('MN',0.06),('MO',0.05),('MS',0.05),('MT',0.03),('NC',0.05),('ND',0.05),('NE',0.04),('NH',0.06),('NJ',0.05),('NM',0.05),('NV',0.04),('NY',0.06),('OH',0.04),('OK',0.04),('OR',0.07),('PA',0.06),('RI',0.06),('SC',0.06),('SD',0.06),('TN',0.05),('TX',0.03),('UT',0.04),('VA',0.06),('VT',0.07),('WA',0.05),('WI',0.03),('WV',0.05),('WY',0.04);