--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.5
-- Dumped by pg_dump version 9.6.5

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = vstore, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: vs_product; Type: TABLE; Schema: vstore; Owner: vstore_adm
--

CREATE TABLE vs_product (
    id integer NOT NULL,
    items integer NOT NULL,
    name character varying[] NOT NULL,
    description text,
    value_id integer,
    image_url text
);


ALTER TABLE vs_product OWNER TO vstore_adm;

--
-- Name: product_id_seq; Type: SEQUENCE; Schema: vstore; Owner: vstore_adm
--

CREATE SEQUENCE product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE product_id_seq OWNER TO vstore_adm;

--
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: vstore; Owner: vstore_adm
--

ALTER SEQUENCE product_id_seq OWNED BY vs_product.id;


--
-- Name: vs_product_value; Type: TABLE; Schema: vstore; Owner: vstore_adm
--

CREATE TABLE vs_product_value (
    id integer NOT NULL,
    product_id integer NOT NULL,
    value money NOT NULL,
    creation_date timestamp without time zone NOT NULL
);


ALTER TABLE vs_product_value OWNER TO vstore_adm;

--
-- Name: product_value_id_seq; Type: SEQUENCE; Schema: vstore; Owner: vstore_adm
--

CREATE SEQUENCE product_value_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE product_value_id_seq OWNER TO vstore_adm;

--
-- Name: product_value_id_seq; Type: SEQUENCE OWNED BY; Schema: vstore; Owner: vstore_adm
--

ALTER SEQUENCE product_value_id_seq OWNED BY vs_product_value.id;


--
-- Name: vs_user; Type: TABLE; Schema: vstore; Owner: vstore_adm
--

CREATE TABLE vs_user (
    id integer NOT NULL,
    name character varying(40) NOT NULL,
    surname character varying(50) NOT NULL,
    cpf character varying(11),
    cnpj character varying(14),
    role integer DEFAULT 1 NOT NULL,
    email character varying(150) NOT NULL,
    username character varying(20)
);


ALTER TABLE vs_user OWNER TO vstore_adm;

--
-- Name: user_id_seq; Type: SEQUENCE; Schema: vstore; Owner: vstore_adm
--

CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_id_seq OWNER TO vstore_adm;

--
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: vstore; Owner: vstore_adm
--

ALTER SEQUENCE user_id_seq OWNED BY vs_user.id;


--
-- Name: vs_media; Type: TABLE; Schema: vstore; Owner: vstore_adm
--

CREATE TABLE vs_media (
    id uuid NOT NULL,
    data bytea NOT NULL
);


ALTER TABLE vs_media OWNER TO vstore_adm;

--
-- Name: vs_user_account; Type: TABLE; Schema: vstore; Owner: vstore_adm
--

CREATE TABLE vs_user_account (
    username character varying(20) NOT NULL,
    password public.chkpass NOT NULL,
    role integer DEFAULT 0
);


ALTER TABLE vs_user_account OWNER TO vstore_adm;

--
-- Name: vs_user_address; Type: TABLE; Schema: vstore; Owner: vstore_adm
--

CREATE TABLE vs_user_address (
    id integer NOT NULL,
    user_id integer NOT NULL,
    name text,
    neighbourhood character varying(40),
    postal_code character varying(8)
);


ALTER TABLE vs_user_address OWNER TO vstore_adm;

--
-- Name: vs_user_card; Type: TABLE; Schema: vstore; Owner: vstore_adm
--

CREATE TABLE vs_user_card (
    id integer NOT NULL,
    user_id integer NOT NULL,
    number character(12) NOT NULL,
    security_number integer NOT NULL
);


ALTER TABLE vs_user_card OWNER TO vstore_adm;

--
-- Name: vs_user_card_id_seq; Type: SEQUENCE; Schema: vstore; Owner: vstore_adm
--

CREATE SEQUENCE vs_user_card_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE vs_user_card_id_seq OWNER TO vstore_adm;

--
-- Name: vs_user_card_id_seq; Type: SEQUENCE OWNED BY; Schema: vstore; Owner: vstore_adm
--

ALTER SEQUENCE vs_user_card_id_seq OWNED BY vs_user_card.id;


--
-- Name: vs_user_cart; Type: TABLE; Schema: vstore; Owner: vstore_adm
--

CREATE TABLE vs_user_cart (
    user_id integer NOT NULL
);


ALTER TABLE vs_user_cart OWNER TO vstore_adm;

--
-- Name: vs_user_cart_product; Type: TABLE; Schema: vstore; Owner: vstore_adm
--

CREATE TABLE vs_user_cart_product (
    user_id integer NOT NULL,
    product_id integer NOT NULL,
    items integer
);


ALTER TABLE vs_user_cart_product OWNER TO vstore_adm;

--
-- Name: vs_user_payment; Type: TABLE; Schema: vstore; Owner: vstore_adm
--

CREATE TABLE vs_user_payment (
    id integer NOT NULL,
    user_id integer,
    type integer DEFAULT 0 NOT NULL,
    card_id integer,
    bill_number integer
);


ALTER TABLE vs_user_payment OWNER TO vstore_adm;

--
-- Name: vs_user_payment_id_seq; Type: SEQUENCE; Schema: vstore; Owner: vstore_adm
--

CREATE SEQUENCE vs_user_payment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE vs_user_payment_id_seq OWNER TO vstore_adm;

--
-- Name: vs_user_payment_id_seq; Type: SEQUENCE OWNED BY; Schema: vstore; Owner: vstore_adm
--

ALTER SEQUENCE vs_user_payment_id_seq OWNED BY vs_user_payment.id;


--
-- Name: vs_user_phone; Type: TABLE; Schema: vstore; Owner: vstore_adm
--

CREATE TABLE vs_user_phone (
    id integer NOT NULL,
    user_id integer NOT NULL,
    number character varying(20) NOT NULL
);


ALTER TABLE vs_user_phone OWNER TO vstore_adm;

--
-- Name: vs_user_phone_id_seq; Type: SEQUENCE; Schema: vstore; Owner: vstore_adm
--

CREATE SEQUENCE vs_user_phone_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE vs_user_phone_id_seq OWNER TO vstore_adm;

--
-- Name: vs_user_phone_id_seq; Type: SEQUENCE OWNED BY; Schema: vstore; Owner: vstore_adm
--

ALTER SEQUENCE vs_user_phone_id_seq OWNED BY vs_user_phone.id;


--
-- Name: vs_user_purchase; Type: TABLE; Schema: vstore; Owner: vstore_adm
--

CREATE TABLE vs_user_purchase (
    id integer NOT NULL,
    user_id integer NOT NULL,
    date timestamp without time zone NOT NULL,
    value money NOT NULL,
    status integer DEFAULT 0 NOT NULL,
    payment_id integer
);


ALTER TABLE vs_user_purchase OWNER TO vstore_adm;

--
-- Name: vs_user_purchase_product; Type: TABLE; Schema: vstore; Owner: vstore_adm
--

CREATE TABLE vs_user_purchase_product (
    purchase_id integer NOT NULL,
    user_id integer NOT NULL,
    product_id integer NOT NULL,
    product_value_id integer NOT NULL,
    items integer DEFAULT 1 NOT NULL
);


ALTER TABLE vs_user_purchase_product OWNER TO vstore_adm;

--
-- Name: vs_product id; Type: DEFAULT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_product ALTER COLUMN id SET DEFAULT nextval('product_id_seq'::regclass);


--
-- Name: vs_product_value id; Type: DEFAULT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_product_value ALTER COLUMN id SET DEFAULT nextval('product_value_id_seq'::regclass);


--
-- Name: vs_user id; Type: DEFAULT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_user ALTER COLUMN id SET DEFAULT nextval('user_id_seq'::regclass);


--
-- Name: vs_user_card id; Type: DEFAULT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_user_card ALTER COLUMN id SET DEFAULT nextval('vs_user_card_id_seq'::regclass);


--
-- Name: vs_user_payment id; Type: DEFAULT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_user_payment ALTER COLUMN id SET DEFAULT nextval('vs_user_payment_id_seq'::regclass);


--
-- Name: vs_user_phone id; Type: DEFAULT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_user_phone ALTER COLUMN id SET DEFAULT nextval('vs_user_phone_id_seq'::regclass);


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: vstore; Owner: vstore_adm
--

SELECT pg_catalog.setval('product_id_seq', 1, false);


--
-- Name: product_value_id_seq; Type: SEQUENCE SET; Schema: vstore; Owner: vstore_adm
--

SELECT pg_catalog.setval('product_value_id_seq', 1, false);


--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: vstore; Owner: vstore_adm
--

SELECT pg_catalog.setval('user_id_seq', 1, false);


--
-- Data for Name: vs_media; Type: TABLE DATA; Schema: vstore; Owner: vstore_adm
--

COPY vs_media (id, data) FROM stdin;
\.


--
-- Data for Name: vs_product; Type: TABLE DATA; Schema: vstore; Owner: vstore_adm
--

COPY vs_product (id, items, name, description, value_id, image_url) FROM stdin;
\.


--
-- Data for Name: vs_product_value; Type: TABLE DATA; Schema: vstore; Owner: vstore_adm
--

COPY vs_product_value (id, product_id, value, creation_date) FROM stdin;
\.


--
-- Data for Name: vs_user; Type: TABLE DATA; Schema: vstore; Owner: vstore_adm
--

COPY vs_user (id, name, surname, cpf, cnpj, role, email, username) FROM stdin;
\.


--
-- Data for Name: vs_user_account; Type: TABLE DATA; Schema: vstore; Owner: vstore_adm
--

COPY vs_user_account (username, password, role) FROM stdin;
\.


--
-- Data for Name: vs_user_address; Type: TABLE DATA; Schema: vstore; Owner: vstore_adm
--

COPY vs_user_address (id, user_id, name, neighbourhood, postal_code) FROM stdin;
\.


--
-- Data for Name: vs_user_card; Type: TABLE DATA; Schema: vstore; Owner: vstore_adm
--

COPY vs_user_card (id, user_id, number, security_number) FROM stdin;
\.


--
-- Name: vs_user_card_id_seq; Type: SEQUENCE SET; Schema: vstore; Owner: vstore_adm
--

SELECT pg_catalog.setval('vs_user_card_id_seq', 1, false);


--
-- Data for Name: vs_user_cart; Type: TABLE DATA; Schema: vstore; Owner: vstore_adm
--

COPY vs_user_cart (user_id) FROM stdin;
\.


--
-- Data for Name: vs_user_cart_product; Type: TABLE DATA; Schema: vstore; Owner: vstore_adm
--

COPY vs_user_cart_product (user_id, product_id, items) FROM stdin;
\.


--
-- Data for Name: vs_user_payment; Type: TABLE DATA; Schema: vstore; Owner: vstore_adm
--

COPY vs_user_payment (id, user_id, type, card_id, bill_number) FROM stdin;
\.


--
-- Name: vs_user_payment_id_seq; Type: SEQUENCE SET; Schema: vstore; Owner: vstore_adm
--

SELECT pg_catalog.setval('vs_user_payment_id_seq', 1, false);


--
-- Data for Name: vs_user_phone; Type: TABLE DATA; Schema: vstore; Owner: vstore_adm
--

COPY vs_user_phone (id, user_id, number) FROM stdin;
\.


--
-- Name: vs_user_phone_id_seq; Type: SEQUENCE SET; Schema: vstore; Owner: vstore_adm
--

SELECT pg_catalog.setval('vs_user_phone_id_seq', 1, false);


--
-- Data for Name: vs_user_purchase; Type: TABLE DATA; Schema: vstore; Owner: vstore_adm
--

COPY vs_user_purchase (id, user_id, date, value, status, payment_id) FROM stdin;
\.


--
-- Data for Name: vs_user_purchase_product; Type: TABLE DATA; Schema: vstore; Owner: vstore_adm
--

COPY vs_user_purchase_product (purchase_id, user_id, product_id, product_value_id, items) FROM stdin;
\.


--
-- Name: vs_media media_pkey; Type: CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_media
    ADD CONSTRAINT media_pkey PRIMARY KEY (id);


--
-- Name: vs_product product_pkey; Type: CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: vs_product_value product_value_pkey; Type: CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_product_value
    ADD CONSTRAINT product_value_pkey PRIMARY KEY (id, product_id);


--
-- Name: vs_user user_pkey; Type: CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_user
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- Name: vs_user_account vs_user_account_pkey; Type: CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_user_account
    ADD CONSTRAINT vs_user_account_pkey PRIMARY KEY (username);


--
-- Name: vs_user_address vs_user_address_id_user_id_pk; Type: CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_user_address
    ADD CONSTRAINT vs_user_address_id_user_id_pk PRIMARY KEY (id, user_id);


--
-- Name: vs_user_card vs_user_card_id_user_id_pk; Type: CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_user_card
    ADD CONSTRAINT vs_user_card_id_user_id_pk PRIMARY KEY (id, user_id);


--
-- Name: vs_user_cart_product vs_user_card_product_user_id_product_id_pk; Type: CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_user_cart_product
    ADD CONSTRAINT vs_user_card_product_user_id_product_id_pk PRIMARY KEY (user_id, product_id);


--
-- Name: vs_user_cart vs_user_cart_pkey; Type: CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_user_cart
    ADD CONSTRAINT vs_user_cart_pkey PRIMARY KEY (user_id);


--
-- Name: vs_user_payment vs_user_payment_pkey; Type: CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_user_payment
    ADD CONSTRAINT vs_user_payment_pkey PRIMARY KEY (id);


--
-- Name: vs_user_phone vs_user_phone_id_user_idid_pk; Type: CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_user_phone
    ADD CONSTRAINT vs_user_phone_id_user_idid_pk PRIMARY KEY (id, user_id);


--
-- Name: vs_user_purchase vs_user_purchase_id_user_id_pk; Type: CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_user_purchase
    ADD CONSTRAINT vs_user_purchase_id_user_id_pk PRIMARY KEY (id, user_id);


--
-- Name: vs_user_purchase_product vs_user_purchase_product_purchase_id_user_id_product_id_product; Type: CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_user_purchase_product
    ADD CONSTRAINT vs_user_purchase_product_purchase_id_user_id_product_id_product PRIMARY KEY (purchase_id, user_id, product_id, product_value_id);


--
-- Name: product_value_id_uindex; Type: INDEX; Schema: vstore; Owner: vstore_adm
--

CREATE UNIQUE INDEX product_value_id_uindex ON vs_product USING btree (value_id);


--
-- Name: user_email_uindex; Type: INDEX; Schema: vstore; Owner: vstore_adm
--

CREATE UNIQUE INDEX user_email_uindex ON vs_user USING btree (email);


--
-- Name: user_id_uindex; Type: INDEX; Schema: vstore; Owner: vstore_adm
--

CREATE UNIQUE INDEX user_id_uindex ON vs_user USING btree (id);


--
-- Name: vs_product_value_id_uindex; Type: INDEX; Schema: vstore; Owner: vstore_adm
--

CREATE UNIQUE INDEX vs_product_value_id_uindex ON vs_product_value USING btree (id);


--
-- Name: vs_user_payment_type_index; Type: INDEX; Schema: vstore; Owner: vstore_adm
--

CREATE INDEX vs_user_payment_type_index ON vs_user_payment USING btree (type);


--
-- Name: vs_user_purchase_id_user_id_uindex; Type: INDEX; Schema: vstore; Owner: vstore_adm
--

CREATE UNIQUE INDEX vs_user_purchase_id_user_id_uindex ON vs_user_purchase USING btree (id, user_id);


--
-- Name: vs_user_purchase_payment_id_uindex; Type: INDEX; Schema: vstore; Owner: vstore_adm
--

CREATE UNIQUE INDEX vs_user_purchase_payment_id_uindex ON vs_user_purchase USING btree (payment_id);


--
-- Name: vs_user_username_uindex; Type: INDEX; Schema: vstore; Owner: vstore_adm
--

CREATE UNIQUE INDEX vs_user_username_uindex ON vs_user USING btree (username);


--
-- Name: vs_product_value product_value_vstore.product_id_fk; Type: FK CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_product_value
    ADD CONSTRAINT "product_value_vstore.product_id_fk" FOREIGN KEY (product_id) REFERENCES vs_product(id) ON DELETE CASCADE;


--
-- Name: vs_user_cart user_cart_user_id_fk; Type: FK CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_user_cart
    ADD CONSTRAINT user_cart_user_id_fk FOREIGN KEY (user_id) REFERENCES vs_user(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: vs_product vs_product_vs_product_value_id_fk; Type: FK CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_product
    ADD CONSTRAINT vs_product_vs_product_value_id_fk FOREIGN KEY (value_id) REFERENCES vs_product_value(id);


--
-- Name: vs_user_address vs_user_address_vs_user_id_fk; Type: FK CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_user_address
    ADD CONSTRAINT vs_user_address_vs_user_id_fk FOREIGN KEY (user_id) REFERENCES vs_user(id) ON DELETE CASCADE;


--
-- Name: vs_user_cart_product vs_user_card_product_vs_product_id_fk; Type: FK CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_user_cart_product
    ADD CONSTRAINT vs_user_card_product_vs_product_id_fk FOREIGN KEY (product_id) REFERENCES vs_product(id);


--
-- Name: vs_user_card vs_user_card_vs_user_id_fk; Type: FK CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_user_card
    ADD CONSTRAINT vs_user_card_vs_user_id_fk FOREIGN KEY (user_id) REFERENCES vs_user(id) ON DELETE CASCADE;


--
-- Name: vs_user_cart_product vs_user_cart_product_vs_user_cart_user_id_fk; Type: FK CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_user_cart_product
    ADD CONSTRAINT vs_user_cart_product_vs_user_cart_user_id_fk FOREIGN KEY (user_id) REFERENCES vs_user_cart(user_id);


--
-- Name: vs_user_payment vs_user_payment_vs_user_card_id_user_id_fk; Type: FK CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_user_payment
    ADD CONSTRAINT vs_user_payment_vs_user_card_id_user_id_fk FOREIGN KEY (card_id, user_id) REFERENCES vs_user_card(id, user_id);


--
-- Name: vs_user_payment vs_user_payment_vs_user_id_fk; Type: FK CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_user_payment
    ADD CONSTRAINT vs_user_payment_vs_user_id_fk FOREIGN KEY (user_id) REFERENCES vs_user(id) ON DELETE CASCADE;


--
-- Name: vs_user_phone vs_user_phone_vs_user_id_fk; Type: FK CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_user_phone
    ADD CONSTRAINT vs_user_phone_vs_user_id_fk FOREIGN KEY (user_id) REFERENCES vs_user(id) ON DELETE CASCADE;


--
-- Name: vs_user_purchase_product vs_user_purchase_product_vs_product_id_fk; Type: FK CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_user_purchase_product
    ADD CONSTRAINT vs_user_purchase_product_vs_product_id_fk FOREIGN KEY (product_id) REFERENCES vs_product(id) ON DELETE CASCADE;


--
-- Name: vs_user_purchase_product vs_user_purchase_product_vs_product_value_id_product_id_fk; Type: FK CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_user_purchase_product
    ADD CONSTRAINT vs_user_purchase_product_vs_product_value_id_product_id_fk FOREIGN KEY (product_value_id, product_id) REFERENCES vs_product_value(id, product_id) ON DELETE CASCADE;


--
-- Name: vs_user_purchase_product vs_user_purchase_product_vs_user_purchase_id_user_id_fk; Type: FK CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_user_purchase_product
    ADD CONSTRAINT vs_user_purchase_product_vs_user_purchase_id_user_id_fk FOREIGN KEY (purchase_id, user_id) REFERENCES vs_user_purchase(id, user_id) ON DELETE CASCADE;


--
-- Name: vs_user_purchase vs_user_purchase_vs_user_id_fk; Type: FK CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_user_purchase
    ADD CONSTRAINT vs_user_purchase_vs_user_id_fk FOREIGN KEY (user_id) REFERENCES vs_user(id);


--
-- Name: vs_user_purchase vs_user_purchase_vs_user_payment_id_fk; Type: FK CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_user_purchase
    ADD CONSTRAINT vs_user_purchase_vs_user_payment_id_fk FOREIGN KEY (payment_id) REFERENCES vs_user_payment(id) ON DELETE SET NULL;


--
-- Name: vs_user vs_user_vs_user_account_username_fk; Type: FK CONSTRAINT; Schema: vstore; Owner: vstore_adm
--

ALTER TABLE ONLY vs_user
    ADD CONSTRAINT vs_user_vs_user_account_username_fk FOREIGN KEY (username) REFERENCES vs_user_account(username);


--
-- Name: vs_product; Type: ACL; Schema: vstore; Owner: vstore_adm
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vs_product TO vstore_usr;


--
-- Name: product_id_seq; Type: ACL; Schema: vstore; Owner: vstore_adm
--

GRANT ALL ON SEQUENCE product_id_seq TO vstore_usr;


--
-- Name: vs_product_value; Type: ACL; Schema: vstore; Owner: vstore_adm
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vs_product_value TO vstore_usr;


--
-- Name: product_value_id_seq; Type: ACL; Schema: vstore; Owner: vstore_adm
--

GRANT ALL ON SEQUENCE product_value_id_seq TO vstore_usr;


--
-- Name: vs_user; Type: ACL; Schema: vstore; Owner: vstore_adm
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vs_user TO vstore_usr;


--
-- Name: user_id_seq; Type: ACL; Schema: vstore; Owner: vstore_adm
--

GRANT ALL ON SEQUENCE user_id_seq TO vstore_usr;


--
-- Name: vs_media; Type: ACL; Schema: vstore; Owner: vstore_adm
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vs_media TO vstore_usr;


--
-- Name: vs_user_account; Type: ACL; Schema: vstore; Owner: vstore_adm
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vs_user_account TO vstore_usr;


--
-- Name: vs_user_address; Type: ACL; Schema: vstore; Owner: vstore_adm
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vs_user_address TO vstore_usr;


--
-- Name: vs_user_card; Type: ACL; Schema: vstore; Owner: vstore_adm
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vs_user_card TO vstore_usr;


--
-- Name: vs_user_card_id_seq; Type: ACL; Schema: vstore; Owner: vstore_adm
--

GRANT ALL ON SEQUENCE vs_user_card_id_seq TO vstore_usr;


--
-- Name: vs_user_cart; Type: ACL; Schema: vstore; Owner: vstore_adm
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vs_user_cart TO vstore_usr;


--
-- Name: vs_user_cart_product; Type: ACL; Schema: vstore; Owner: vstore_adm
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vs_user_cart_product TO vstore_usr;


--
-- Name: vs_user_payment; Type: ACL; Schema: vstore; Owner: vstore_adm
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vs_user_payment TO vstore_usr;


--
-- Name: vs_user_payment_id_seq; Type: ACL; Schema: vstore; Owner: vstore_adm
--

GRANT ALL ON SEQUENCE vs_user_payment_id_seq TO vstore_usr;


--
-- Name: vs_user_phone; Type: ACL; Schema: vstore; Owner: vstore_adm
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vs_user_phone TO vstore_usr;


--
-- Name: vs_user_phone_id_seq; Type: ACL; Schema: vstore; Owner: vstore_adm
--

GRANT ALL ON SEQUENCE vs_user_phone_id_seq TO vstore_usr;


--
-- Name: vs_user_purchase; Type: ACL; Schema: vstore; Owner: vstore_adm
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vs_user_purchase TO vstore_usr;


--
-- Name: vs_user_purchase_product; Type: ACL; Schema: vstore; Owner: vstore_adm
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE vs_user_purchase_product TO vstore_usr;


--
-- PostgreSQL database dump complete
--

