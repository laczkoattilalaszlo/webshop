-- RUN THIS FILE TO CREATE THE SUITABLE SQL SCHEMA FOR THE APPLICATION

-- 1. CREATE USER TABLE
    create table "user"
    (
        id                  uuid not null
                            constraint user_pk
                            primary key,
        email               text,
        password            text,
        name                text,
        phone               text
    );

    create unique index user_id_uindex
        on "user" (id);

-- 2. CREATE PRODUCT CATEGORY TABLE
    create table product_category
    (
        id   uuid not null
                  constraint product_category_pk
                  primary key,
        name text not null
    );

    create unique index product_category_id_uindex
        on product_category (id);

-- 3. CRETE PRODUCT SUPPLIER TABLE
    create table product_supplier
    (
        id   uuid not null
                  constraint product_supplier_pk
                  primary key,
        name text not null
    );

    create unique index product_supplier_id_uindex
        on product_supplier (id);

-- 4.  CREATE PRODUCT TABLE
    create table product
    (
        id             uuid not null
                       constraint product_pk
                       primary key,
        name           text,
        description    text,
        price          numeric,
        currency       text,
        supplier_id    uuid
                       constraint product_product_supplier_id_fk
                       references product_supplier
                       on delete set null,
        category_id    uuid
                       constraint product_product_category_id_fk
                       references product_category
                       on delete set null,
        picture        text
    );

    create unique index product_id_uindex
        on product (id);

-- 5. CREATE CART TABLE
    create table cart
    (
        product_id  uuid
                    constraint table_name_product_id_fk
                    references product
                    on delete cascade,
        quantity    int,
        user_id     uuid
                    constraint table_name_user_id_fk
                    references "user"
                    on delete cascade
    );

-- 6 CREATE SHIPPING ADDRESS TABLE
    create table shipping_address
    (
        zip     int,
        country text,
        city    text,
        address text,
        user_id uuid
                constraint shipping_address_user_id_fk
                references "user"
                on delete cascade
    );

-- 7. CREATE BILLING ADDRESS TABLE
    create table billing_address
    (
        zip     int,
        country text,
        city    text,
        address text,
        user_id uuid
                constraint billing_address_user_id_fk
                references "user"
                on delete cascade
    );

-- 8. CREATE BANK CARD TABLE
    create table bank_card
    (
        name            text,
        card_number     text,
        expiration_date date,
        security_code   int,
        user_id         uuid
                        constraint bank_cart_user_id_fk
                        references "user"
                        on delete cascade
    );
