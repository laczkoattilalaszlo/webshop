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
        phone               text,
        picture             text
    );

    create unique index user_id_uindex
        on "user" (id);

-- 2.  CREATE PRODUCT TABLE
    create table product
    (
        id          uuid not null
                    constraint product_pk
                    primary key,
        description text,
        price       numeric,
        currency    text,
        supplier    text,
        category    text,
        picture     text
    );

    create unique index product_id_uindex
        on product (id);

-- 3. CREATE CART TABLE
    create table cart
    (
        product_id uuid
                   constraint table_name_product_id_fk
                   references product
                   on delete cascade,
        quantity   int,
        user_id    uuid
                   constraint table_name_user_id_fk
                   references "user"
                   on delete cascade
    );

-- 4. CREATE SHIPPING ADDRESS TABLE
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

-- 5. CREATE BILLING ADDRESS TABLE
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

-- 6. CREATE BANK CARD TABLE
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
