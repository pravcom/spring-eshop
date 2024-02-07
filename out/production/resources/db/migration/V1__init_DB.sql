create sequence bucket_seq start with 1 increment by 1;
create sequence category_seq start with 1 increment by 1;
create sequence order_details_seq start with 1 increment by 1;
create sequence order_seq start with 1 increment by 1;
create sequence product_seq start with 1 increment by 1;
create sequence user_seq start with 1 increment by 1;
create table buckets
(
    id      bigint not null,
    user_id bigint unique,
    primary key (id)
);
create table buckets_product
(
    bucket_id  bigint not null,
    product_id bigint not null
);
create table categories
(
    id    bigint not null,
    title varchar(255),
    primary key (id)
);
create table orders
(
    sum     numeric(38, 2),
    created timestamp(6),
    id      bigint not null,
    updated timestamp(6),
    user_id bigint,
    address varchar(255),
    status  varchar(255) check (status in ('NEW', 'APPROVED', 'CANCELED', 'PAID', 'CLOSED')),
    primary key (id)
);
create table orders_details
(
    amount     numeric(38, 2),
    price      numeric(38, 2),
    details_id bigint not null unique,
    id         bigint not null,
    order_id   bigint,
    product_id bigint,
    primary key (id)
);
create table product_categories
(
    category_id bigint not null,
    product_id  bigint not null
);
create table products
(
    price numeric(38, 2),
    id    bigint not null,
    title varchar(255),
    primary key (id)
);
create table users
(
    archive   boolean not null,
    bucket_id bigint unique,
    id        bigint  not null,
    email     varchar(255),
    name      varchar(255),
    password  varchar(255),
    role      varchar(255) check (role in ('CLIENT', 'MANAGER', 'ADMIN')),
    primary key (id)
);
alter table if exists buckets add constraint fk_user_id foreign key (user_id) references users;
alter table if exists buckets_product add constraint fk_product_id foreign key (product_id) references products;
alter table if exists buckets_product add constraint fk_bucket_id foreign key (bucket_id) references buckets;
alter table if exists orders add constraint fk_user_id foreign key (user_id) references users;
alter table if exists orders_details add constraint fk_order_id foreign key (order_id) references orders;
alter table if exists orders_details add constraint fk_product_id foreign key (product_id) references products;
alter table if exists orders_details add constraint fk_details_id foreign key (details_id) references orders_details;
alter table if exists product_categories add constraint fk_category_id foreign key (category_id) references categories;
alter table if exists product_categories add constraint fk_product_id foreign key (product_id) references products;
alter table if exists users add constraint fk_bucket_id foreign key (bucket_id) references buckets;