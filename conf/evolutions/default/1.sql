# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table dealab_db.admin (
  id                        integer auto_increment not null,
  name                      varchar(255),
  email                     varchar(100) not null,
  password                  varchar(1000),
  image_url                 varchar(2000),
  token                     varchar(2000),
  constraint uq_admin_email unique (email),
  constraint uq_admin_1 unique (email),
  constraint pk_admin primary key (id))
;

create table dealab_db.adverticement (
  id                        bigint auto_increment not null,
  description               varchar(255),
  image_url                 varchar(2000),
  active                    tinyint(1) default 0,
  constraint pk_adverticement primary key (id))
;

create table dealab_db.beacon (
  id                        bigint auto_increment not null,
  branch_id                 bigint,
  name                      varchar(100),
  type                      integer,
  mac_address               integer,
  description               varchar(255),
  constraint pk_beacon primary key (id))
;

create table dealab_db.branch (
  id                        bigint auto_increment not null,
  company_id                bigint not null,
  name                      varchar(100),
  description               varchar(1000),
  address                   varchar(255),
  place_id                  varchar(100),
  lat                       decimal(15,10),
  lng                       decimal(15,10),
  contact                   varchar(25),
  landmark                  varchar(255),
  notes                     varchar(1000),
  status                    integer,
  constraint pk_branch primary key (id))
;

create table dealab_db.category (
  id                        integer auto_increment not null,
  name                      varchar(100) not null,
  description               varchar(1000),
  image_url                 varchar(2000),
  constraint pk_category primary key (id))
;

create table dealab_db.company (
  id                        bigint auto_increment not null,
  category_id               integer,
  name                      varchar(100) not null,
  logo                      varchar(2000),
  cover_image               varchar(2000),
  description               varchar(1000),
  contact_1                 varchar(25),
  cantact_2                 varchar(25),
  status                    integer,
  website                   varchar(500),
  hq_address                varchar(255),
  constraint pk_company primary key (id))
;

create table dealab_db.deal (
  id                        bigint auto_increment not null,
  template_id               integer,
  description               varchar(1000),
  note                      varchar(500),
  image_url                 varchar(2000),
  web_url                   varchar(2000),
  start_date                datetime(6),
  end_date                  datetime(6),
  status                    integer,
  constraint pk_deal primary key (id))
;

create table dealab_db.deal_branch (
  id                        bigint auto_increment not null,
  deal_id                   bigint not null,
  branch_id                 bigint not null,
  constraint pk_deal_branch primary key (id))
;

create table dealab_db.favourite (
  id                        bigint auto_increment not null,
  user_id                   bigint,
  branch_id                 bigint,
  constraint pk_favourite primary key (id))
;

create table dealab_db.image (
  id                        bigint auto_increment not null,
  branch_id                 bigint,
  company_id                bigint,
  url                       varchar(2000) not null,
  type                      integer,
  constraint pk_image primary key (id))
;

create table dealab_db.open_hours (
  id                        bigint auto_increment not null,
  branch_id                 bigint,
  day                       varchar(25) not null,
  hour                      varchar(50) not null,
  constraint pk_open_hours primary key (id))
;

create table dealab_db.review (
  id                        bigint auto_increment not null,
  user_id                   bigint,
  branch_id                 bigint,
  comment                   varchar(1000),
  rating                    integer,
  added_date                datetime(6),
  updateded_date            datetime(6),
  constraint pk_review primary key (id))
;

create table dealab_db.template (
  id                        integer auto_increment not null,
  bg_image                  varchar(2000),
  overlay_url               varchar(2000),
  constraint pk_template primary key (id))
;

create table dealab_db.user (
  id                        bigint auto_increment not null,
  company_id                bigint,
  name                      varchar(255) not null,
  email                     varchar(100) not null,
  password                  varchar(1000),
  image_url                 varchar(2000),
  mobile                    varchar(25),
  home_lat                  decimal(15,10),
  home_lng                  decimal(15,10),
  work_lat                  decimal(15,10),
  work_lng                  decimal(15,10),
  login_type                integer,
  device_token              varchar(2000),
  token                     varchar(1000),
  device_id                 varchar(255),
  constraint uq_user_email unique (email),
  constraint uq_user_1 unique (email),
  constraint pk_user primary key (id))
;

alter table dealab_db.beacon add constraint fk_beacon_branch_1 foreign key (branch_id) references dealab_db.branch (id) on delete restrict on update restrict;
create index ix_beacon_branch_1 on dealab_db.beacon (branch_id);
alter table dealab_db.branch add constraint fk_branch_company_2 foreign key (company_id) references dealab_db.company (id) on delete restrict on update restrict;
create index ix_branch_company_2 on dealab_db.branch (company_id);
alter table dealab_db.company add constraint fk_company_category_3 foreign key (category_id) references dealab_db.category (id) on delete restrict on update restrict;
create index ix_company_category_3 on dealab_db.company (category_id);
alter table dealab_db.deal add constraint fk_deal_template_4 foreign key (template_id) references dealab_db.template (id) on delete restrict on update restrict;
create index ix_deal_template_4 on dealab_db.deal (template_id);
alter table dealab_db.deal_branch add constraint fk_deal_branch_deal_5 foreign key (deal_id) references dealab_db.deal (id) on delete restrict on update restrict;
create index ix_deal_branch_deal_5 on dealab_db.deal_branch (deal_id);
alter table dealab_db.deal_branch add constraint fk_deal_branch_branch_6 foreign key (branch_id) references dealab_db.branch (id) on delete restrict on update restrict;
create index ix_deal_branch_branch_6 on dealab_db.deal_branch (branch_id);
alter table dealab_db.favourite add constraint fk_favourite_user_7 foreign key (user_id) references dealab_db.user (id) on delete restrict on update restrict;
create index ix_favourite_user_7 on dealab_db.favourite (user_id);
alter table dealab_db.favourite add constraint fk_favourite_branch_8 foreign key (branch_id) references dealab_db.branch (id) on delete restrict on update restrict;
create index ix_favourite_branch_8 on dealab_db.favourite (branch_id);
alter table dealab_db.image add constraint fk_image_branch_9 foreign key (branch_id) references dealab_db.branch (id) on delete restrict on update restrict;
create index ix_image_branch_9 on dealab_db.image (branch_id);
alter table dealab_db.image add constraint fk_image_company_10 foreign key (company_id) references dealab_db.company (id) on delete restrict on update restrict;
create index ix_image_company_10 on dealab_db.image (company_id);
alter table dealab_db.open_hours add constraint fk_open_hours_branch_11 foreign key (branch_id) references dealab_db.branch (id) on delete restrict on update restrict;
create index ix_open_hours_branch_11 on dealab_db.open_hours (branch_id);
alter table dealab_db.review add constraint fk_review_user_12 foreign key (user_id) references dealab_db.user (id) on delete restrict on update restrict;
create index ix_review_user_12 on dealab_db.review (user_id);
alter table dealab_db.review add constraint fk_review_branch_13 foreign key (branch_id) references dealab_db.branch (id) on delete restrict on update restrict;
create index ix_review_branch_13 on dealab_db.review (branch_id);
alter table dealab_db.user add constraint fk_user_company_14 foreign key (company_id) references dealab_db.company (id) on delete restrict on update restrict;
create index ix_user_company_14 on dealab_db.user (company_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table dealab_db.admin;

drop table dealab_db.adverticement;

drop table dealab_db.beacon;

drop table dealab_db.branch;

drop table dealab_db.category;

drop table dealab_db.company;

drop table dealab_db.deal;

drop table dealab_db.deal_branch;

drop table dealab_db.favourite;

drop table dealab_db.image;

drop table dealab_db.open_hours;

drop table dealab_db.review;

drop table dealab_db.template;

drop table dealab_db.user;

SET FOREIGN_KEY_CHECKS=1;

