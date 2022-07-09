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

    create unique index user_session_token_uindex
        on "user" (session_token);

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
        zip     text,
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
        zip     text,
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
        security_code   text,
        user_id         uuid
                        constraint bank_cart_user_id_fk
                        references "user"
                        on delete cascade
    );

-- 9. CREATE ORDER CONTACT TABLE
    create table order_contact
    (
        id    uuid not null
              constraint order_contact_pk
              primary key,
        name  text,
        email text,
        phone text
    );

    create unique index order_contact_id_uindex
        on order_contact (id);

-- 10. CREATE ORDER SHIPPING ADDRESS TABLE
    create table order_shipping_address
    (
        id      uuid not null
                constraint order_shipping_address_pk
                primary key,
        zip     text,
        country text,
        city    text,
        address text
    );

    create unique index order_shipping_address_id_uindex
        on order_shipping_address (id);

-- 11. CREATE ORDER BILLING ADDRESS TABLE
    create table order_billing_address
    (
        id      uuid not null
                constraint order_billing_address_pk
                primary key,
        zip     text,
        country text,
        city    text,
        address text
    );

    create unique index order_billing_address_id_uindex
        on order_billing_address (id);

-- 12. CREATE ORDER TABLE
    create table "order"
    (
        id                          uuid  not null
                                    constraint order_pk
                                    primary key,
        order_contact               uuid
                                    constraint order_order_contact_id_fk
                                    references order_contact
                                    on delete cascade,
        order_shipping_address      uuid
                                    constraint order_order_shipping_address_id_fk
                                    references order_shipping_address
                                    on delete cascade,
        order_billing_address       uuid
                                    constraint order_order_billing_address_id_fk
                                    references order_billing_address
                                    on delete cascade,
        transaction_code            text,
        date                        date,
        status                      text,
        user_id                     uuid not null
                                    constraint order_user_id_fk
                                    references "user"
                                    on delete cascade
    );

    create unique index order_id_uindex
        on "order" (id);

-- 13. CREATE ORDER CART TABLE
    create table order_cart
    (
        product_id uuid,
        name       text,
        unit_price numeric,
        currency   text,
        quantity   int,
        order_id   uuid not null
                   constraint order_cart_order_id_fk
                   references "order"
                   on delete cascade
    );
