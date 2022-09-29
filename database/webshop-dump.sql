--
-- PostgreSQL database dump
--

-- Dumped from database version 12.12 (Ubuntu 12.12-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.12 (Ubuntu 12.12-0ubuntu0.20.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: billing_address; Type: TABLE; Schema: public; Owner: laczkoattila
--

CREATE TABLE public.billing_address (
    name text DEFAULT ''::text,
    zip text DEFAULT ''::text,
    country text DEFAULT ''::text,
    city text DEFAULT ''::text,
    address text DEFAULT ''::text,
    user_id uuid
);


ALTER TABLE public.billing_address OWNER TO laczkoattila;

--
-- Name: cart; Type: TABLE; Schema: public; Owner: laczkoattila
--

CREATE TABLE public.cart (
    product_id uuid,
    quantity integer,
    user_id uuid
);


ALTER TABLE public.cart OWNER TO laczkoattila;

--
-- Name: order; Type: TABLE; Schema: public; Owner: laczkoattila
--

CREATE TABLE public."order" (
    id uuid NOT NULL,
    user_id uuid NOT NULL
);


ALTER TABLE public."order" OWNER TO laczkoattila;

--
-- Name: order_billing_address; Type: TABLE; Schema: public; Owner: laczkoattila
--

CREATE TABLE public.order_billing_address (
    name text DEFAULT ''::text,
    zip text DEFAULT ''::text,
    country text DEFAULT ''::text,
    city text DEFAULT ''::text,
    address text DEFAULT ''::text,
    order_id uuid NOT NULL
);


ALTER TABLE public.order_billing_address OWNER TO laczkoattila;

--
-- Name: order_cart; Type: TABLE; Schema: public; Owner: laczkoattila
--

CREATE TABLE public.order_cart (
    product_id uuid,
    product_supplier text,
    product_name text,
    unit_price numeric,
    currency text,
    quantity integer,
    order_id uuid NOT NULL
);


ALTER TABLE public.order_cart OWNER TO laczkoattila;

--
-- Name: order_contact; Type: TABLE; Schema: public; Owner: laczkoattila
--

CREATE TABLE public.order_contact (
    name text DEFAULT ''::text,
    email text DEFAULT ''::text,
    phone text DEFAULT ''::text,
    order_id uuid NOT NULL
);


ALTER TABLE public.order_contact OWNER TO laczkoattila;

--
-- Name: order_payment; Type: TABLE; Schema: public; Owner: laczkoattila
--

CREATE TABLE public.order_payment (
    payment_id text NOT NULL,
    payment_state text,
    start_timestamp timestamp without time zone,
    order_id uuid NOT NULL
);


ALTER TABLE public.order_payment OWNER TO laczkoattila;

--
-- Name: order_shipping_address; Type: TABLE; Schema: public; Owner: laczkoattila
--

CREATE TABLE public.order_shipping_address (
    name text DEFAULT ''::text,
    zip text DEFAULT ''::text,
    country text DEFAULT ''::text,
    city text DEFAULT ''::text,
    address text DEFAULT ''::text,
    order_id uuid NOT NULL
);


ALTER TABLE public.order_shipping_address OWNER TO laczkoattila;

--
-- Name: product; Type: TABLE; Schema: public; Owner: laczkoattila
--

CREATE TABLE public.product (
    id uuid NOT NULL,
    name text,
    description text,
    price numeric,
    currency text,
    supplier_id uuid,
    category_id uuid,
    picture text
);


ALTER TABLE public.product OWNER TO laczkoattila;

--
-- Name: product_category; Type: TABLE; Schema: public; Owner: laczkoattila
--

CREATE TABLE public.product_category (
    id uuid NOT NULL,
    name text NOT NULL
);


ALTER TABLE public.product_category OWNER TO laczkoattila;

--
-- Name: product_supplier; Type: TABLE; Schema: public; Owner: laczkoattila
--

CREATE TABLE public.product_supplier (
    id uuid NOT NULL,
    name text NOT NULL
);


ALTER TABLE public.product_supplier OWNER TO laczkoattila;

--
-- Name: shipping_address; Type: TABLE; Schema: public; Owner: laczkoattila
--

CREATE TABLE public.shipping_address (
    name text DEFAULT ''::text,
    zip text DEFAULT ''::text,
    country text DEFAULT ''::text,
    city text DEFAULT ''::text,
    address text DEFAULT ''::text,
    user_id uuid
);


ALTER TABLE public.shipping_address OWNER TO laczkoattila;

--
-- Name: user; Type: TABLE; Schema: public; Owner: laczkoattila
--

CREATE TABLE public."user" (
    id uuid NOT NULL,
    email text DEFAULT ''::text,
    password text,
    name text DEFAULT ''::text,
    phone text DEFAULT ''::text,
    role text,
    session_token text
);


ALTER TABLE public."user" OWNER TO laczkoattila;

--
-- Data for Name: billing_address; Type: TABLE DATA; Schema: public; Owner: laczkoattila
--

COPY public.billing_address (name, zip, country, city, address, user_id) FROM stdin;
\.


--
-- Data for Name: cart; Type: TABLE DATA; Schema: public; Owner: laczkoattila
--

COPY public.cart (product_id, quantity, user_id) FROM stdin;
\.


--
-- Data for Name: order; Type: TABLE DATA; Schema: public; Owner: laczkoattila
--

COPY public."order" (id, user_id) FROM stdin;
\.


--
-- Data for Name: order_billing_address; Type: TABLE DATA; Schema: public; Owner: laczkoattila
--

COPY public.order_billing_address (name, zip, country, city, address, order_id) FROM stdin;
\.


--
-- Data for Name: order_cart; Type: TABLE DATA; Schema: public; Owner: laczkoattila
--

COPY public.order_cart (product_id, product_supplier, product_name, unit_price, currency, quantity, order_id) FROM stdin;
\.


--
-- Data for Name: order_contact; Type: TABLE DATA; Schema: public; Owner: laczkoattila
--

COPY public.order_contact (name, email, phone, order_id) FROM stdin;
\.


--
-- Data for Name: order_payment; Type: TABLE DATA; Schema: public; Owner: laczkoattila
--

COPY public.order_payment (payment_id, payment_state, start_timestamp, order_id) FROM stdin;
\.


--
-- Data for Name: order_shipping_address; Type: TABLE DATA; Schema: public; Owner: laczkoattila
--

COPY public.order_shipping_address (name, zip, country, city, address, order_id) FROM stdin;
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: laczkoattila
--

COPY public.product (id, name, description, price, currency, supplier_id, category_id, picture) FROM stdin;
0b8aabe0-11ba-11ed-9369-159766dedf5e	Redmi 8A	32GB, White	125	USD	eda74880-11a6-11ed-ae7d-fde4a71237df	b5e36cc4-45d1-4bb2-9cec-ca7aae8ca286	xiaomiRedmi8Awhite.png
d4da3dd0-3f41-11ed-ad94-311eb01bfc57	iPhone 14	128GB, Blue	850	USD	9fb6440f-c45c-43b3-afc0-c55350fc7688	b5e36cc4-45d1-4bb2-9cec-ca7aae8ca286	appleIphone14blue.png
a597e4e0-3f42-11ed-ad94-311eb01bfc57	iPhone 12	128GB, Black	830	USD	9fb6440f-c45c-43b3-afc0-c55350fc7688	b5e36cc4-45d1-4bb2-9cec-ca7aae8ca286	appleIphone12black.png
594bec20-3f43-11ed-b8be-67eab5507d3e	Redmi 10C	64GB, Blue	150	USD	eda74880-11a6-11ed-ae7d-fde4a71237df	b5e36cc4-45d1-4bb2-9cec-ca7aae8ca286	xiaomiRedmi10Cblue.png
8202c8e0-3f44-11ed-b8be-67eab5507d3e	iPad 9th generation	256, Grey	460	USD	9fb6440f-c45c-43b3-afc0-c55350fc7688	fedd391c-b8ae-474b-8871-b9d1e165e633	appleIpad9grey.png
12119970-3f45-11ed-b8be-67eab5507d3e	iPad Air 5th generation	256, Blue	600	USD	9fb6440f-c45c-43b3-afc0-c55350fc7688	fedd391c-b8ae-474b-8871-b9d1e165e633	appleIpadAir5blue.png
dbc83da0-3f45-11ed-a424-7725db5977a8	iPad Pro 3rd generation	128GB, Silver	800	USD	9fb6440f-c45c-43b3-afc0-c55350fc7688	fedd391c-b8ae-474b-8871-b9d1e165e633	appleIpadPro3silver.png
3ce10080-3f47-11ed-a424-7725db5977a8	iPad Pro 3rd generation	128GB, Grey	800	USD	9fb6440f-c45c-43b3-afc0-c55350fc7688	fedd391c-b8ae-474b-8871-b9d1e165e633	appleIpadPro3grey.png
b13e6e40-3f47-11ed-8aaf-6909b5b8fd69	iPad Air 5th generation	256, Purple	600	USD	9fb6440f-c45c-43b3-afc0-c55350fc7688	fedd391c-b8ae-474b-8871-b9d1e165e633	appleIpadAir5purple.png
013b6ba0-3f48-11ed-8aaf-6909b5b8fd69	iPad 9th generation	256, Silver	460	USD	9fb6440f-c45c-43b3-afc0-c55350fc7688	fedd391c-b8ae-474b-8871-b9d1e165e633	appleIpad9silver.png
a40b44e0-3f57-11ed-bfd9-cd98d6de5a54	MateBook 13	13", Core i7, 8GB DDR4 RAM, 512 GB SSD	600	USD	0b9b4850-11a7-11ed-ae7d-fde4a71237df	4041cfe2-d491-409e-86c0-64f214ae852b	huaweiMateBook13grey.png
794c2b21-3f4d-11ed-8aaf-6909b5b8fd69	Pad 4	32GB, Pink	120	USD	eda74880-11a6-11ed-ae7d-fde4a71237df	fedd391c-b8ae-474b-8871-b9d1e165e633	xiaomiPad4pink.png
794c2b20-3f4d-11ed-8aaf-6909b5b8fd69	Pad 4	32GB, Black	120	USD	eda74880-11a6-11ed-ae7d-fde4a71237df	fedd391c-b8ae-474b-8871-b9d1e165e633	xiaomiPad4black.png
3ac93fe1-3f4e-11ed-bfd9-cd98d6de5a54	Pad 5	128GB, Black	400	USD	eda74880-11a6-11ed-ae7d-fde4a71237df	fedd391c-b8ae-474b-8871-b9d1e165e633	xiaomiPad5black.png
32b31e40-11ac-11ed-a1ef-c3a5840079dd	iPhone 12	128GB, White	830	USD	9fb6440f-c45c-43b3-afc0-c55350fc7688	b5e36cc4-45d1-4bb2-9cec-ca7aae8ca286	appleIphone12white.png
3bcde5f0-11ac-11ed-9369-159766dedf5e	iPhone 13	32GB, Pink	830	USD	9fb6440f-c45c-43b3-afc0-c55350fc7688	b5e36cc4-45d1-4bb2-9cec-ca7aae8ca286	appleIphone13pink.png
bce90120-11ab-11ed-a1ef-c3a5840079dd	iPhone X	32GB, Silver	245	USD	9fb6440f-c45c-43b3-afc0-c55350fc7688	b5e36cc4-45d1-4bb2-9cec-ca7aae8ca286	appleIphoneXwhite.png
d76873a0-11ab-11ed-a1ef-c3a5840079dd	iPhone 11	64GB, Purple	600	USD	9fb6440f-c45c-43b3-afc0-c55350fc7688	b5e36cc4-45d1-4bb2-9cec-ca7aae8ca286	appleIphone11purple.png
4d55e330-3f3e-11ed-ad94-311eb01bfc57	12 Pro	256GB, Grey	600	USD	eda74880-11a6-11ed-ae7d-fde4a71237df	b5e36cc4-45d1-4bb2-9cec-ca7aae8ca286	xiaomi12proGrey.png
0c6a4c90-11b1-11ed-9369-159766dedf5e	Galaxy A32	128GB, Black	245	USD	751dffd0-418b-4b48-af4b-0aa4336e2c60	b5e36cc4-45d1-4bb2-9cec-ca7aae8ca286	samsungGalaxyA32black.png
901f88b0-11b2-11ed-9369-159766dedf5e	Galaxy A51	128GB, Grey	400	USD	751dffd0-418b-4b48-af4b-0aa4336e2c60	b5e36cc4-45d1-4bb2-9cec-ca7aae8ca286	samsungGalaxyA51grey.png
20f9bf90-11b3-11ed-9369-159766dedf5e	Galaxy A52	128GB, White	425	USD	751dffd0-418b-4b48-af4b-0aa4336e2c60	b5e36cc4-45d1-4bb2-9cec-ca7aae8ca286	samsungGalaxyA52white.png
0bcb5b30-11b6-11ed-9369-159766dedf5e	Galaxy A30S	64GB, Green	250	USD	751dffd0-418b-4b48-af4b-0aa4336e2c60	b5e36cc4-45d1-4bb2-9cec-ca7aae8ca286	samsungGalaxyA30Sgreen.png
92d9a700-11af-11ed-9369-159766dedf5e	Galaxy A73	128GB, White	440	USD	751dffd0-418b-4b48-af4b-0aa4336e2c60	b5e36cc4-45d1-4bb2-9cec-ca7aae8ca286	samsungGalaxyA73white.png
0e1aff40-11b0-11ed-9369-159766dedf5e	Galaxy A13	128GB, Blue	385	USD	751dffd0-418b-4b48-af4b-0aa4336e2c60	b5e36cc4-45d1-4bb2-9cec-ca7aae8ca286	samsungGalaxyA13blue.png
77ea93b0-11b8-11ed-9369-159766dedf5e	Redmi 9C	128GB, Blue	175	USD	eda74880-11a6-11ed-ae7d-fde4a71237df	b5e36cc4-45d1-4bb2-9cec-ca7aae8ca286	xiaomiRedmi9Cblue.png
4a5e6bb0-11b8-11ed-9369-159766dedf5e	Redmi 9	32GB, Green	120	USD	eda74880-11a6-11ed-ae7d-fde4a71237df	b5e36cc4-45d1-4bb2-9cec-ca7aae8ca286	xiaomiRedmi9green.png
ac49c0d0-11b9-11ed-9369-159766dedf5e	Redmi Note 9T	128GB, Purple	220	USD	eda74880-11a6-11ed-ae7d-fde4a71237df	b5e36cc4-45d1-4bb2-9cec-ca7aae8ca286	xiaomiRedmiNote9Tpurple.png
3ac93fe0-3f4e-11ed-bfd9-cd98d6de5a54	Pad 5	128GB, White	400	USD	eda74880-11a6-11ed-ae7d-fde4a71237df	fedd391c-b8ae-474b-8871-b9d1e165e633	xiaomiPad5white.png
91e69f10-3f4f-11ed-bfd9-cd98d6de5a54	Pad 5	128GB, Green	400	USD	eda74880-11a6-11ed-ae7d-fde4a71237df	fedd391c-b8ae-474b-8871-b9d1e165e633	xiaomiPad5grern.png
d861b910-3f50-11ed-bfd9-cd98d6de5a54	Pad 5 Pro	256GB, Black	800	USD	eda74880-11a6-11ed-ae7d-fde4a71237df	fedd391c-b8ae-474b-8871-b9d1e165e633	xiaomiPadPro5black.png
71ba8230-3f52-11ed-bfd9-cd98d6de5a54	MateBook 14	14", Core i5, 8GB DDR4 RAM, 512 GB SSD	1050	USD	0b9b4850-11a7-11ed-ae7d-fde4a71237df	4041cfe2-d491-409e-86c0-64f214ae852b	huaweiMateBook14grey.png
71baa940-3f52-11ed-bfd9-cd98d6de5a54	MateBook D14	14", Core i5, 8GB DDR4 RAM, 512 GB SSD	810	USD	0b9b4850-11a7-11ed-ae7d-fde4a71237df	4041cfe2-d491-409e-86c0-64f214ae852b	huaweiMateBookD14grey.png
475f8070-3f53-11ed-bfd9-cd98d6de5a54	MateBook D16	16", Core i5, 16GB DDR4 RAM, 512 GB SSD	1200	USD	0b9b4850-11a7-11ed-ae7d-fde4a71237df	4041cfe2-d491-409e-86c0-64f214ae852b	huaweiMateBookD16grey.png
bf47047e-c805-4b52-b533-a3b22a83536d	MacBook Air M1 2020	13", 8GB RAM, 256 GB SSD, Silver	1000	USD	9fb6440f-c45c-43b3-afc0-c55350fc7688	4041cfe2-d491-409e-86c0-64f214ae852b	appleMacBookAir2020silver.png
993b8c21-3f55-11ed-bfd9-cd98d6de5a54	MateBook 13	13", Core i5, 16GB DDR4 RAM, 512 GB SSD	500	USD	0b9b4850-11a7-11ed-ae7d-fde4a71237df	4041cfe2-d491-409e-86c0-64f214ae852b	huaweiMateBook13grey.png
53a3ecf1-3f57-11ed-bfd9-cd98d6de5a54	MateBook 16S	16", Core i5, 18GB DDR4 RAM, 256 GB SSD	800	USD	0b9b4850-11a7-11ed-ae7d-fde4a71237df	4041cfe2-d491-409e-86c0-64f214ae852b	huaweiMateBook16Sgrey.png
a40b44e2-3f57-11ed-bfd9-cd98d6de5a54	MateBook 14	14", Core i3, 4GB DDR4 RAM, 512 GB SSD	850	USD	0b9b4850-11a7-11ed-ae7d-fde4a71237df	4041cfe2-d491-409e-86c0-64f214ae852b	huaweiMateBook14grey.png
a40b44e4-3f57-11ed-bfd9-cd98d6de5a54	MateBook 16S	16", Core i7, 16GB DDR4 RAM, 512 GB SSD	1500	USD	0b9b4850-11a7-11ed-ae7d-fde4a71237df	4041cfe2-d491-409e-86c0-64f214ae852b	huaweiMateBook16Sgrey.png
993b8c23-3f55-11ed-bfd9-cd98d6de5a54	MateBook D15	15.6", Core i5, 16GB DDR4 RAM, 256 GB SSD	850	USD	0b9b4850-11a7-11ed-ae7d-fde4a71237df	4041cfe2-d491-409e-86c0-64f214ae852b	huaweiMateBookD15grey.png
a0e93d97-3369-434f-ba3b-f10c7abdfb9d	MateBook D14	14", Core i7, 8GB DDR4 RAM, 512 GB SSD	1010	USD	0b9b4850-11a7-11ed-ae7d-fde4a71237df	4041cfe2-d491-409e-86c0-64f214ae852b	huaweiMateBookD14grey.png
5de98b8a-5fd0-44b6-8a1e-4bf990ca945f	MateBook D15	15.6", Core i5, 8GB DDR4 RAM, 256 GB SSD	750	USD	0b9b4850-11a7-11ed-ae7d-fde4a71237df	4041cfe2-d491-409e-86c0-64f214ae852b	huaweiMateBookD15grey.png
d6cdb584-e4e3-49ae-81c7-9496e843320e	MateBook D16	16", Core i3, 16GB DDR4 RAM, 256 GB SSD	700	USD	0b9b4850-11a7-11ed-ae7d-fde4a71237df	4041cfe2-d491-409e-86c0-64f214ae852b	huaweiMateBookD16grey.png
4e00fa2e-c563-45f1-a8a3-c408d60d5c87	MacBook Air M1 2020	13", 8GB RAM, 256 GB SSD, Grey	1000	USD	9fb6440f-c45c-43b3-afc0-c55350fc7688	4041cfe2-d491-409e-86c0-64f214ae852b	appleMacBookAir2020grey.png
f9e15118-b763-4bbf-80f9-56a0c9e76b11	MacBook Air M1 2020	13", 8GB RAM, 256 GB SSD, Pink	1000	USD	9fb6440f-c45c-43b3-afc0-c55350fc7688	4041cfe2-d491-409e-86c0-64f214ae852b	appleMacBookAir2020pink.png
25b00d59-a57b-48e5-93c6-ad9bdf68e880	MacBook Pro 2021	16", 16GB RAM, 512 GB SSD, Silver	2535	USD	9fb6440f-c45c-43b3-afc0-c55350fc7688	4041cfe2-d491-409e-86c0-64f214ae852b	appleMacBookPro2021silver.png
4699a8e0-cb6a-47e2-8b9c-fa4a6e7218d9	MacBook Pro 2021	16", 16GB RAM, 512 GB SSD, Grey	2535	USD	9fb6440f-c45c-43b3-afc0-c55350fc7688	4041cfe2-d491-409e-86c0-64f214ae852b	appleMacBookPro2021grey.png
19c846e7-2d57-43e3-a429-87f70e9e4cfe	MacBook Air M2 2022	13.6", 8GB RAM, 256 GB SSD, Grey	1355	USD	9fb6440f-c45c-43b3-afc0-c55350fc7688	4041cfe2-d491-409e-86c0-64f214ae852b	appleMacBookAir2022grey.png
1082719f-ce28-4847-8c83-954a1c7a5e2b	MacBook Air M2 2022	13.6", 8GB RAM, 256 GB SSD, Silver	1355	USD	9fb6440f-c45c-43b3-afc0-c55350fc7688	4041cfe2-d491-409e-86c0-64f214ae852b	appleMacBookAir2022silver.png
75f1ba6e-0d0d-478c-9a8e-bb7aa85d69a6	MacBook Air M2 2022	13.6", 8GB RAM, 256 GB SSD, Black	1355	USD	9fb6440f-c45c-43b3-afc0-c55350fc7688	4041cfe2-d491-409e-86c0-64f214ae852b	appleMacBookPro2021black.png
c007abb1-3f5d-11ed-aeae-df0d1226af99	75PUS8007	75", 4K, LED	1150	USD	aa4c02d0-3f5d-11ed-a2c3-b39ae1eef5d3	c8774959-d720-4574-a6a1-e6028e4f7f4d	philipsAmbilight.png
2d366a51-3f5e-11ed-b943-db9cadf6b4c4	75PUS8807	75", 4K, LED	1300	USD	aa4c02d0-3f5d-11ed-a2c3-b39ae1eef5d3	c8774959-d720-4574-a6a1-e6028e4f7f4d	philipsTheOneAmbilight.png
c007abb0-3f5d-11ed-aeae-df0d1226af99	65PUS8007	65", 4K, LED	900	USD	aa4c02d0-3f5d-11ed-a2c3-b39ae1eef5d3	c8774959-d720-4574-a6a1-e6028e4f7f4d	philipsAmbilight.png
2d366a50-3f5e-11ed-b943-db9cadf6b4c4	65PUS8507	65", 4K, LED	1150	USD	aa4c02d0-3f5d-11ed-a2c3-b39ae1eef5d3	c8774959-d720-4574-a6a1-e6028e4f7f4d	philipsTheOneAmbilight.png
f6eb6b60-3f5f-11ed-b943-db9cadf6b4c4	65PUS7607	65", 4K, LED	870	USD	aa4c02d0-3f5d-11ed-a2c3-b39ae1eef5d3	c8774959-d720-4574-a6a1-e6028e4f7f4d	philips4K.png
f6eb6b61-3f5f-11ed-b943-db9cadf6b4c4	55PUS7607	55", 4K, LED	680	USD	aa4c02d0-3f5d-11ed-a2c3-b39ae1eef5d3	c8774959-d720-4574-a6a1-e6028e4f7f4d	philips4K.png
ce02ac61-3f62-11ed-a1e0-5d715e97aa65	Bravia KD-43X72KPAEP	43", 4K, LED	536	USD	fc6d7f20-3f63-11ed-af20-01dfb0e3fd4e	c8774959-d720-4574-a6a1-e6028e4f7f4d	sonyBraviaKD-43X72KPAEP.png
ce02ac62-3f62-11ed-a1e0-5d715e97aa65	Bravia XR-75X95KAEP	75", 4K, Mini LED	3650	USD	fc6d7f20-3f63-11ed-af20-01dfb0e3fd4e	c8774959-d720-4574-a6a1-e6028e4f7f4d	sonyBraviaXR-75X95KAEP.png
ce02ac63-3f62-11ed-a1e0-5d715e97aa65	Bravia XR-65A80KAEP	65", 4K, OLED	2050	USD	fc6d7f20-3f63-11ed-af20-01dfb0e3fd4e	c8774959-d720-4574-a6a1-e6028e4f7f4d	sonyBraviaXR-65A80KAEP.png
ce02ac64-3f62-11ed-a1e0-5d715e97aa65	Bravia XR-55A90JAEP	55", 4K, OLED	1570	USD	fc6d7f20-3f63-11ed-af20-01dfb0e3fd4e	c8774959-d720-4574-a6a1-e6028e4f7f4d	sonyBraviaXR-55A90JAEP.png
ce02ac65-3f62-11ed-a1e0-5d715e97aa65	Bravia XR-75X93JAEP	75", 4K, LED	1300	USD	fc6d7f20-3f63-11ed-af20-01dfb0e3fd4e	c8774959-d720-4574-a6a1-e6028e4f7f4d	sonyBraviaXR-75X93JAEP.png
ce02ac60-3f62-11ed-a1e0-5d715e97aa65	Bravia KD-50X85KAEP	50", 4K, LED	915	USD	fc6d7f20-3f63-11ed-af20-01dfb0e3fd4e	c8774959-d720-4574-a6a1-e6028e4f7f4d	sonyBraviaKD-50X85KAEP.png
\.


--
-- Data for Name: product_category; Type: TABLE DATA; Schema: public; Owner: laczkoattila
--

COPY public.product_category (id, name) FROM stdin;
c8774959-d720-4574-a6a1-e6028e4f7f4d	Television
4041cfe2-d491-409e-86c0-64f214ae852b	Notebook
fedd391c-b8ae-474b-8871-b9d1e165e633	Tablet
b5e36cc4-45d1-4bb2-9cec-ca7aae8ca286	Mobile
\.


--
-- Data for Name: product_supplier; Type: TABLE DATA; Schema: public; Owner: laczkoattila
--

COPY public.product_supplier (id, name) FROM stdin;
9fb6440f-c45c-43b3-afc0-c55350fc7688	Apple
751dffd0-418b-4b48-af4b-0aa4336e2c60	Samsung
eda74880-11a6-11ed-ae7d-fde4a71237df	Xiaomi
0b9b4850-11a7-11ed-ae7d-fde4a71237df	Huawei
aa4c02d0-3f5d-11ed-a2c3-b39ae1eef5d3	Philips
fc6d7f20-3f63-11ed-af20-01dfb0e3fd4e	Sony
\.


--
-- Data for Name: shipping_address; Type: TABLE DATA; Schema: public; Owner: laczkoattila
--

COPY public.shipping_address (name, zip, country, city, address, user_id) FROM stdin;
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: laczkoattila
--

COPY public."user" (id, email, password, name, phone, role, session_token) FROM stdin;
\.


--
-- Name: order order_pk; Type: CONSTRAINT; Schema: public; Owner: laczkoattila
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_pk PRIMARY KEY (id);


--
-- Name: product_category product_category_pk; Type: CONSTRAINT; Schema: public; Owner: laczkoattila
--

ALTER TABLE ONLY public.product_category
    ADD CONSTRAINT product_category_pk PRIMARY KEY (id);


--
-- Name: product product_pk; Type: CONSTRAINT; Schema: public; Owner: laczkoattila
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pk PRIMARY KEY (id);


--
-- Name: product_supplier product_supplier_pk; Type: CONSTRAINT; Schema: public; Owner: laczkoattila
--

ALTER TABLE ONLY public.product_supplier
    ADD CONSTRAINT product_supplier_pk PRIMARY KEY (id);


--
-- Name: user user_pk; Type: CONSTRAINT; Schema: public; Owner: laczkoattila
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pk PRIMARY KEY (id);


--
-- Name: order_contact_order_id_uindex; Type: INDEX; Schema: public; Owner: laczkoattila
--

CREATE UNIQUE INDEX order_contact_order_id_uindex ON public.order_contact USING btree (order_id);


--
-- Name: order_id_uindex; Type: INDEX; Schema: public; Owner: laczkoattila
--

CREATE UNIQUE INDEX order_id_uindex ON public."order" USING btree (id);


--
-- Name: order_payment_payment_id_uindex; Type: INDEX; Schema: public; Owner: laczkoattila
--

CREATE UNIQUE INDEX order_payment_payment_id_uindex ON public.order_payment USING btree (payment_id);


--
-- Name: order_shipping_address_order_id_uindex; Type: INDEX; Schema: public; Owner: laczkoattila
--

CREATE UNIQUE INDEX order_shipping_address_order_id_uindex ON public.order_shipping_address USING btree (order_id);


--
-- Name: product_category_id_uindex; Type: INDEX; Schema: public; Owner: laczkoattila
--

CREATE UNIQUE INDEX product_category_id_uindex ON public.product_category USING btree (id);


--
-- Name: product_id_uindex; Type: INDEX; Schema: public; Owner: laczkoattila
--

CREATE UNIQUE INDEX product_id_uindex ON public.product USING btree (id);


--
-- Name: product_supplier_id_uindex; Type: INDEX; Schema: public; Owner: laczkoattila
--

CREATE UNIQUE INDEX product_supplier_id_uindex ON public.product_supplier USING btree (id);


--
-- Name: user_email_uindex; Type: INDEX; Schema: public; Owner: laczkoattila
--

CREATE UNIQUE INDEX user_email_uindex ON public."user" USING btree (email);


--
-- Name: user_id_uindex; Type: INDEX; Schema: public; Owner: laczkoattila
--

CREATE UNIQUE INDEX user_id_uindex ON public."user" USING btree (id);


--
-- Name: billing_address billing_address_user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: laczkoattila
--

ALTER TABLE ONLY public.billing_address
    ADD CONSTRAINT billing_address_user_id_fk FOREIGN KEY (user_id) REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: order_billing_address order_billing_address_order_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: laczkoattila
--

ALTER TABLE ONLY public.order_billing_address
    ADD CONSTRAINT order_billing_address_order_id_fk FOREIGN KEY (order_id) REFERENCES public."order"(id) ON DELETE CASCADE;


--
-- Name: order_cart order_cart_order_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: laczkoattila
--

ALTER TABLE ONLY public.order_cart
    ADD CONSTRAINT order_cart_order_id_fk FOREIGN KEY (order_id) REFERENCES public."order"(id) ON DELETE CASCADE;


--
-- Name: order_contact order_contact_order_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: laczkoattila
--

ALTER TABLE ONLY public.order_contact
    ADD CONSTRAINT order_contact_order_id_fk FOREIGN KEY (order_id) REFERENCES public."order"(id) ON DELETE CASCADE;


--
-- Name: order_payment order_payment_order_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: laczkoattila
--

ALTER TABLE ONLY public.order_payment
    ADD CONSTRAINT order_payment_order_id_fk FOREIGN KEY (order_id) REFERENCES public."order"(id) ON DELETE CASCADE;


--
-- Name: order_shipping_address order_shipping_address_order_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: laczkoattila
--

ALTER TABLE ONLY public.order_shipping_address
    ADD CONSTRAINT order_shipping_address_order_id_fk FOREIGN KEY (order_id) REFERENCES public."order"(id) ON DELETE CASCADE;


--
-- Name: order order_user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: laczkoattila
--

ALTER TABLE ONLY public."order"
    ADD CONSTRAINT order_user_id_fk FOREIGN KEY (user_id) REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: product product_product_category_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: laczkoattila
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_product_category_id_fk FOREIGN KEY (category_id) REFERENCES public.product_category(id) ON DELETE SET NULL;


--
-- Name: product product_product_supplier_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: laczkoattila
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_product_supplier_id_fk FOREIGN KEY (supplier_id) REFERENCES public.product_supplier(id) ON DELETE SET NULL;


--
-- Name: shipping_address shipping_address_user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: laczkoattila
--

ALTER TABLE ONLY public.shipping_address
    ADD CONSTRAINT shipping_address_user_id_fk FOREIGN KEY (user_id) REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: cart table_name_product_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: laczkoattila
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT table_name_product_id_fk FOREIGN KEY (product_id) REFERENCES public.product(id) ON DELETE CASCADE;


--
-- Name: cart table_name_user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: laczkoattila
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT table_name_user_id_fk FOREIGN KEY (user_id) REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

