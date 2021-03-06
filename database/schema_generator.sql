-- RUN THIS FILE TO CREATE THE SUITABLE SQL SCHEMA FOR THE APPLICATION

-- 1. CREATE USER TABLE
    create table "user"
    (
        id                  uuid not null
                            constraint user_pk
                            primary key,
        email               text default '',
        password            text,
        name                text default '',
        phone               text default '',
        role                text,
        session_token       text
    );

    create unique index user_id_uindex
        on "user" (id);

    create unique index user_email_uindex
        on "user" (email);

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
        name    text default '',
        zip     text default '',
        country text default '',
        city    text default '',
        address text default '',
        user_id uuid
                constraint shipping_address_user_id_fk
                references "user"
                on delete cascade
    );

-- 7. CREATE BILLING ADDRESS TABLE
    create table billing_address
    (
        name    text default '',
        zip     text default '',
        country text default '',
        city    text default '',
        address text default '',
        user_id uuid
                constraint billing_address_user_id_fk
                references "user"
                on delete cascade
    );

-- 8. CREATE ORDER TABLE
    create table "order"
    (
        id      uuid not null
                constraint order_pk
                primary key,
        user_id uuid not null
                constraint order_user_id_fk
                references "user"
                on delete cascade
    );

    create unique index order_id_uindex
        on "order" (id);

-- 9. CREATE ORDER CART TABLE
    create table order_cart
    (
        product_id          uuid,
        product_supplier    text,
        product_name        text,
        unit_price          numeric,
        currency            text,
        quantity            int,
        order_id            uuid not null
                            constraint order_cart_order_id_fk
                            references "order"
                            on delete cascade
    );

-- 10. CREATE ORDER CONTACT TABLE
    create table order_contact
    (
        name     text default '',
        email    text default '',
        phone    text default '',
        order_id uuid not null
                 constraint order_contact_order_id_fk
                 references "order"
                 on delete cascade
    );

    create unique index order_contact_order_id_uindex
        on order_contact (order_id);

-- 11. CREATE ORDER SHIPPING ADDRESS TABLE
    create table order_shipping_address
    (
        name     text default '',
        zip      text default '',
        country  text default '',
        city     text default '',
        address  text default '',
        order_id uuid not null
                 constraint order_shipping_address_order_id_fk
                 references "order"
                 on delete cascade
    );

    create unique index order_shipping_address_order_id_uindex
        on order_shipping_address (order_id);

-- 12. CREATE BILLING ADDRESS TABLE
    create table order_billing_address
    (
        name     text default '',
        zip      text default '',
        country  text default '',
        city     text default '',
        address  text default '',
        order_id uuid not null
                 constraint order_billing_address_order_id_fk
                 references "order"
                 on delete cascade
    );

    create unique index order_billing_address_order_id_uindex
        on order_billing_address (order_id);

-- 13. CREATE PAYMENT TABLE
    create table order_payment
    (
        payment_id      text not null,
        payment_state   text,
        start_timestamp timestamp,
        order_id        uuid not null
                        constraint order_payment_order_id_fk
                        references "order"
                        on delete cascade
    );

    create unique index order_payment_payment_id_uindex
        on order_payment (payment_id);