--
-- PostgreSQL database dump
--

-- Dumped from database version 11.2 (Debian 11.2-1.pgdg90+1)
-- Dumped by pg_dump version 11.2 (Debian 11.2-1.pgdg90+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: document; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.document (
    id character varying(255) NOT NULL,
    authority character varying(255),
    city character varying(255),
    code_issue_state character varying(255),
    country character varying(255),
    date_birth timestamp without time zone,
    date_creation timestamp without time zone,
    date_expiry timestamp without time zone,
    date_issue timestamp without time zone,
    date_validity timestamp without time zone,
    description character varying(255),
    holder_sign character varying(255),
    natinality character varying(255),
    number character varying(255),
    place_birth character varying(255),
    serial character varying(255),
    title character varying(255),
    type character varying(255),
    user_sex integer
);


ALTER TABLE public.document OWNER TO socialhelper;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: socialhelper
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO socialhelper;

--
-- Name: organization; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.organization (
    id bigint NOT NULL,
    date_creation timestamp without time zone,
    description character varying(255),
    name character varying(255),
    notes character varying(255),
    type_of character varying(255)
);


ALTER TABLE public.organization OWNER TO socialhelper;

--
-- Name: organization_address; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.organization_address (
    organization_id bigint NOT NULL,
    address character varying(255)
);


ALTER TABLE public.organization_address OWNER TO socialhelper;

--
-- Name: organization_coordinates; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.organization_coordinates (
    organization_id bigint NOT NULL,
    coordinates character varying(255)
);


ALTER TABLE public.organization_coordinates OWNER TO socialhelper;

--
-- Name: organization_open_hours; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.organization_open_hours (
    organization_id bigint NOT NULL,
    open_hours character varying(255)
);


ALTER TABLE public.organization_open_hours OWNER TO socialhelper;

--
-- Name: organization_other_contacts; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.organization_other_contacts (
    organization_id bigint NOT NULL,
    other_contacts character varying(255)
);


ALTER TABLE public.organization_other_contacts OWNER TO socialhelper;

--
-- Name: organization_phone; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.organization_phone (
    organization_id bigint NOT NULL,
    phone character varying(255)
);


ALTER TABLE public.organization_phone OWNER TO socialhelper;

--
-- Name: organization_photo_urls; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.organization_photo_urls (
    organization_id bigint NOT NULL,
    photo_urls character varying(255)
);


ALTER TABLE public.organization_photo_urls OWNER TO socialhelper;

--
-- Name: organization_users; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.organization_users (
    organization_id bigint NOT NULL,
    users_id bigint NOT NULL
);


ALTER TABLE public.organization_users OWNER TO socialhelper;

--
-- Name: person_persons; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.person_persons (
    relation_id bigint NOT NULL,
    person_id bigint NOT NULL
);


ALTER TABLE public.person_persons OWNER TO socialhelper;

--
-- Name: service; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.service (
    id bigint NOT NULL,
    cityprice real NOT NULL,
    cost real NOT NULL,
    countryprice real NOT NULL,
    date_creation timestamp without time zone,
    date_enable timestamp without time zone,
    description character varying(255),
    duration bigint,
    enable boolean NOT NULL,
    one_time boolean NOT NULL,
    periods integer NOT NULL,
    title character varying(255)
);


ALTER TABLE public.service OWNER TO socialhelper;

--
-- Name: social_service; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.social_service (
    id bigint NOT NULL,
    cityprice real NOT NULL,
    cost real NOT NULL,
    countryprice real NOT NULL,
    date_creation timestamp without time zone,
    date_enable timestamp without time zone,
    description character varying(255),
    duration bigint,
    enable boolean NOT NULL,
    one_time boolean NOT NULL,
    periods integer NOT NULL,
    title character varying(1024),
    version bigint,
    social_service_catalog_id bigint
);


ALTER TABLE public.social_service OWNER TO socialhelper;

--
-- Name: social_service_catalog; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.social_service_catalog (
    id bigint NOT NULL,
    date_creation timestamp without time zone,
    date_enable timestamp without time zone,
    description character varying(255),
    enable boolean NOT NULL,
    free boolean NOT NULL,
    title character varying(1024),
    organization_id bigint
);


ALTER TABLE public.social_service_catalog OWNER TO socialhelper;

--
-- Name: social_service_date_enable; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.social_service_date_enable (
    social_service_id character varying(255) NOT NULL,
    date_enable timestamp without time zone
);


ALTER TABLE public.social_service_date_enable OWNER TO socialhelper;

--
-- Name: task_social_service; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.task_social_service (
    id bigint NOT NULL,
    date_create timestamp without time zone,
    date_start timestamp without time zone,
    date_stop timestamp without time zone,
    duration bigint,
    enable boolean NOT NULL,
    version bigint,
    employee_id bigint,
    needy_id bigint,
    social_service_id bigint,
    status integer,
    priority integer,
    task_status integer,
    CONSTRAINT task_social_service_priority_check CHECK (((priority >= 1) AND (priority <= 10)))
);


ALTER TABLE public.task_social_service OWNER TO socialhelper;

--
-- Name: test_person; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.test_person (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE public.test_person OWNER TO socialhelper;

--
-- Name: user_personal; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.user_personal (
    id bigint NOT NULL,
    active boolean NOT NULL,
    date_of_birth timestamp without time zone,
    place_birth character varying(255),
    place_residence character varying(255),
    user_sex integer
);


ALTER TABLE public.user_personal OWNER TO socialhelper;

--
-- Name: user_personal_address; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.user_personal_address (
    user_personal_id bigint NOT NULL,
    address character varying(255)
);


ALTER TABLE public.user_personal_address OWNER TO socialhelper;

--
-- Name: user_personal_documents; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.user_personal_documents (
    user_personal_id bigint NOT NULL,
    documents_id character varying(255) NOT NULL
);


ALTER TABLE public.user_personal_documents OWNER TO socialhelper;

--
-- Name: user_personal_employment; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.user_personal_employment (
    user_personal_id bigint NOT NULL,
    employment integer
);


ALTER TABLE public.user_personal_employment OWNER TO socialhelper;

--
-- Name: user_personal_other_contacts; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.user_personal_other_contacts (
    user_personal_id bigint NOT NULL,
    other_contacts character varying(255)
);


ALTER TABLE public.user_personal_other_contacts OWNER TO socialhelper;

--
-- Name: user_personal_phone; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.user_personal_phone (
    user_personal_id bigint NOT NULL,
    phone character varying(255)
);


ALTER TABLE public.user_personal_phone OWNER TO socialhelper;

--
-- Name: user_personal_social_factors; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.user_personal_social_factors (
    user_personal_id bigint NOT NULL,
    social_factors character varying(255)
);


ALTER TABLE public.user_personal_social_factors OWNER TO socialhelper;

--
-- Name: usr; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.usr (
    id bigint NOT NULL,
    address character varying(255),
    date_creation timestamp without time zone,
    descriptions character varying(255),
    email character varying(255),
    enable boolean NOT NULL,
    firstname character varying(255),
    lastname character varying(255),
    locale character varying(255),
    middlename character varying(255),
    mobile character varying(255),
    nickname character varying(255),
    notes character varying(255),
    password character varying(255),
    phone character varying(255),
    role integer,
    sub character varying(255),
    version bigint,
    organization_id bigint,
    personal_id bigint
);


ALTER TABLE public.usr OWNER TO socialhelper;

--
-- Name: usr_auth_date_time; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.usr_auth_date_time (
    usr_id bigint NOT NULL,
    auth_date_time timestamp without time zone
);


ALTER TABLE public.usr_auth_date_time OWNER TO socialhelper;

--
-- Name: usr_date_enable; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.usr_date_enable (
    usr_id bigint NOT NULL,
    date_enable timestamp without time zone
);


ALTER TABLE public.usr_date_enable OWNER TO socialhelper;

--
-- Name: usr_employee; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.usr_employee (
    id bigint NOT NULL,
    role integer,
    version bigint
);


ALTER TABLE public.usr_employee OWNER TO socialhelper;

--
-- Name: usr_needy; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.usr_needy (
    id bigint NOT NULL,
    role integer,
    version bigint
);


ALTER TABLE public.usr_needy OWNER TO socialhelper;

--
-- Name: usr_organizations; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.usr_organizations (
    usr_id bigint NOT NULL,
    organizations_id bigint NOT NULL
);


ALTER TABLE public.usr_organizations OWNER TO socialhelper;

--
-- Name: usr_roles; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.usr_roles (
    usr_id bigint NOT NULL,
    roles integer
);


ALTER TABLE public.usr_roles OWNER TO socialhelper;

--
-- Name: usr_users; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.usr_users (
    usr_id bigint NOT NULL,
    users_id bigint NOT NULL
);


ALTER TABLE public.usr_users OWNER TO socialhelper;

--
-- Name: usr_users2; Type: TABLE; Schema: public; Owner: socialhelper
--

CREATE TABLE public.usr_users2 (
    usr_id bigint NOT NULL,
    users2_id bigint NOT NULL
);


ALTER TABLE public.usr_users2 OWNER TO socialhelper;

--
-- Data for Name: document; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.document (id, authority, city, code_issue_state, country, date_birth, date_creation, date_expiry, date_issue, date_validity, description, holder_sign, natinality, number, place_birth, serial, title, type, user_sex) FROM stdin;
\.


--
-- Data for Name: organization; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.organization (id, date_creation, description, name, notes, type_of) FROM stdin;
35	\N	\N	КГАУСО ПЦСОН	\N	Социальное обслуживание
40	\N	\N	Седанка	\N	Социальное обслуживание
\.


--
-- Data for Name: organization_address; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.organization_address (organization_id, address) FROM stdin;
35	Иртышская, 8
40	Маковского
\.


--
-- Data for Name: organization_coordinates; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.organization_coordinates (organization_id, coordinates) FROM stdin;
\.


--
-- Data for Name: organization_open_hours; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.organization_open_hours (organization_id, open_hours) FROM stdin;
\.


--
-- Data for Name: organization_other_contacts; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.organization_other_contacts (organization_id, other_contacts) FROM stdin;
\.


--
-- Data for Name: organization_phone; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.organization_phone (organization_id, phone) FROM stdin;
35	8(423)2604314
40	8(423)2604314
\.


--
-- Data for Name: organization_photo_urls; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.organization_photo_urls (organization_id, photo_urls) FROM stdin;
\.


--
-- Data for Name: organization_users; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.organization_users (organization_id, users_id) FROM stdin;
\.


--
-- Data for Name: person_persons; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.person_persons (relation_id, person_id) FROM stdin;
1	2
2	1
\.


--
-- Data for Name: service; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.service (id, cityprice, cost, countryprice, date_creation, date_enable, description, duration, enable, one_time, periods, title) FROM stdin;
\.


--
-- Data for Name: social_service; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.social_service (id, cityprice, cost, countryprice, date_creation, date_enable, description, duration, enable, one_time, periods, title, version, social_service_catalog_id) FROM stdin;
2	143.539993	0	157.889999	\N	2019-06-28 15:24:10.959	\N	\N	t	f	0	покупка за счет средств получателя социальных услуг и доставка на дом продуктов питания, промышленных товаров первой необходимости, средств санитарии и гигиены, средств ухода, книг, газет, журналов	\N	1
3	143.539993	0	157.889999	\N	2019-06-28 15:24:10.986	\N	\N	t	f	0	помощь в приготовлении пищи	\N	1
4	71.7699966	0	78.9499969	\N	2019-06-28 15:24:10.996	\N	\N	t	f	0	оплата за счет средств получателя социальных услуг жилищно-коммунальных услуг и услуг связи	\N	1
5	215.320007	0	236.850006	\N	2019-06-28 15:24:11.005	\N	\N	t	f	0	сдача за счет средств получателя социальных услуг вещей в стирку, химчистку, ремонт, обратная их доставка	\N	1
6	215.320007	0	236.850006	\N	2019-06-28 15:24:11.015	\N	\N	t	f	0	покупка за счет средств получателя социальных услуг топлива, содействие в обеспечении топливом (в жилых помещениях без центрального отопления)	\N	1
7	95.6999969	0	105.269997	\N	2019-06-28 15:24:11.025	\N	\N	t	f	0	Топка печей	\N	1
8	143.539993	0	157.889999	\N	2019-06-28 15:24:11.035	\N	\N	t	f	0	обеспечение водой (в жилых помещениях без центрального водоснабжения)	\N	1
9	47.8499985	0	52.6399994	\N	2019-06-28 15:24:11.044	\N	\N	t	f	0	организация помощи в проведении ремонта жилых помещений	\N	1
10	861.27002	0	947.400024	\N	2019-06-28 15:24:11.054	\N	\N	t	f	0	обеспечение кратковременного присмотра за детьми	\N	1
11	287.089996	0	315.799988	\N	2019-06-28 15:24:11.064	\N	\N	t	f	0	уборка жилых помещений	\N	1
12	191.389999	0	210.529999	\N	2019-06-28 15:24:11.073	\N	\N	t	f	0	содействие в организации предоставления услуг организациями торговли, коммунально-бытового обслуживания, связи и другими организациями, оказывающими  услуги населению, в пределах района проживания	\N	1
13	71.7699966	0	78.9499969	\N	2019-06-28 15:24:11.083	\N	\N	t	f	0	оказание помощи в написании и прочтении писем и иных документов	\N	1
14	430.630005	0	473.690002	\N	2019-06-28 15:24:11.093	\N	\N	t	f	0	предоставление гигиенических услуг лицам, не способным по состоянию здоровья самостоятельно осуществлять за собой уход	\N	1
15	95.6999969	0	105.269997	\N	2019-06-28 15:24:11.104	\N	\N	t	f	0	отправка за счет средств получателя социальных услуг почтовой корреспонденции	\N	1
16	167.470001	0	184.220001	\N	2019-06-28 15:24:11.116	\N	\N	t	f	0	помощь в приеме пищи (кормление)	\N	1
17	0	0	0	\N	2019-06-28 15:24:11.13	\N	\N	t	f	0	организация ритуальных услуг (при отсутствии у умерших клиентов родственников или в связи с их нежеланием заниматься погребением)	\N	1
19	71.7699966	0	78.9499969	\N	2019-06-28 15:24:11.16	\N	\N	t	f	0	выполнение процедур, связанных с организацией ухода, наблюдением за состоянием здоровья получателей социальных услуг (измерение температуры тела, артериального давления, контроль за приемом лекарств и\nдр.)	\N	18
20	95.6999969	0	105.269997	\N	2019-06-28 15:24:11.168	\N	\N	t	f	0	оказание содействия в проведении оздоровительных мероприятий	\N	18
21	71.7699966	0	78.9499969	\N	2019-06-28 15:24:11.176	\N	\N	t	f	0	систематическое наблюдение за получателями социальных услуг в целях выявления отклонений в состоянии их здоровья	\N	18
22	143.550003	0	157.910004	\N	2019-06-28 15:24:11.185	\N	\N	t	f	0	консультирование по социально-медицинским вопросам (поддержания и сохранения здоровья получателей социальных услуг, проведения оздоровительных мероприятий, наблюдения за получателями социальных\nуслуг в целях выявления отклонений в состоянии их здоровья)	\N	18
23	382.790009	0	421.070007	\N	2019-06-28 15:24:11.195	\N	\N	t	f	0	оказание или содействие в оказании медицинской помощи\nв медицинских организациях в объеме, предусмотренном территориальной программой государственных гарантий бесплатного оказания гражданам медицинской помощи	\N	18
24	143.539993	0	157.889999	\N	2019-06-28 15:24:11.206	\N	\N	t	f	0	содействие в обеспечении по медицинским показаниям лекарственными препаратами и изделиями медицинского назначения, в том числе за счет средств получателя социальных услуг	\N	18
53	144.330002	0	158.759995	\N	2019-06-28 15:24:11.443	\N	\N	t	f	0	проведение социально-реабилитационных мероприятий в сфере социального обслуживания	\N	51
54	96.2200012	0	105.839996	\N	2019-06-28 15:24:11.45	\N	\N	t	f	0	обучение навыкам поведения в быту и общественных местах	\N	51
55	72.1500015	0	79.3700027	\N	2019-06-28 15:24:11.457	\N	\N	t	f	0	оказание помощи в обучении навыкам компьютерной грамотности	\N	51
25	47.8499985	0	52.6399994	\N	2019-06-28 15:24:11.217	\N	\N	t	f	0	проведение в соответствии с назначением лечащего врача медицинских процедур (подкожные и внутримышечные инъекции лекарственных препаратов, наложение компрессов, перевязка, обработка пролежней, раневых поверхностей, выполнение очистительных клизм, забор материалов\nдля проведения лабораторных исследований, оказание помощи в пользовании катетерами и другими медицинскими изделиями) при наличии лицензии на осуществление медицинской деятельности данного вида	\N	18
26	143.539993	0	157.889999	\N	2019-06-28 15:24:11.225	\N	\N	t	f	0	оказание экстренной доврачебной помощи, вызов врача на дом, сопровождение обслуживаемых граждан в медицинских организациях, подведомственных уполномоченному органу исполнительной власти Приморского края в сфере здравоохранения, и посещение их в этих организациях в случае госпитализации	\N	18
28	444.839996	0	489.320007	\N	2019-06-28 15:24:11.242	\N	\N	t	f	0	социально-психологическое консультирование (в том числе по вопросам внутрисемейных отношений)	\N	27
29	88.9700012	0	97.8700027	\N	2019-06-28 15:24:11.249	\N	\N	t	f	0	  социально-психологический патронаж	\N	27
30	185.360001	0	203.899994	\N	2019-06-28 15:24:11.256	\N	\N	t	f	0	оказание консультационной психологической помощи анонимно	\N	27
31	95.6999969	0	105.269997	\N	2019-06-28 15:24:11.262	\N	\N	t	f	0	оказание психологической помощи, в том числе беседы, общение, выслушивание, подбадривание, мотивация к активности, психологическая поддержка жизненного тонуса клиентов, обслуживаемых на дому	\N	27
33	192.429993	0	211.669998	\N	2019-06-28 15:24:11.279	\N	\N	t	f	0	социально-педагогическая коррекция, включая диагностику и\nконсультирование	\N	32
34	144.330002	0	158.759995	\N	2019-06-28 15:24:11.286	\N	\N	t	f	0	формирование позитивных интересов (в том числе в сфере досуга)	\N	32
35	432.980011	0	476.279999	\N	2019-06-28 15:24:11.293	\N	\N	t	f	0	организация досуга (праздники, экскурсии и другие культурные мероприятия)	\N	32
36	72.1500015	0	79.3700027	\N	2019-06-28 15:24:11.301	\N	\N	t	f	0	обучение практическим навыкам общего ухода за тяжелобольными получателями социальных услуг, получателями социальных услуг, имеющими ограничения жизнедеятельности, в том числе за детьми-инвалидами	\N	32
37	0	0	0	\N	2019-06-28 15:24:11.308	\N	\N	t	f	0	организация помощи родителям и иным законным представителям детей-инвалидов, воспитываемых дома, в обучении таких детей навыкам самообслуживания, общения, направленным на развитие личности	\N	32
39	96.2200012	0	105.839996	\N	2019-06-28 15:24:11.327	\N	\N	t	f	0	оказание помощи в трудоустройстве	\N	38
40	216.460007	0	238.110001	\N	2019-06-28 15:24:11.334	\N	\N	t	f	0	проведение мероприятий по использованию трудовых возможностей и обучению доступным профессиональным навыкам	\N	38
41	0	0	0	\N	2019-06-28 15:24:11.341	\N	\N	t	f	0	организация помощи в получении образования, в том числе профессионального образования, инвалидами (детьми-инвалидами) в соответствии с их способностями	\N	38
43	216.460007	0	238.110001	\N	2019-06-28 15:24:11.359	\N	\N	t	f	0	оказание помощи в оформлении и восстановлении утраченных документов получателей социальных услуг	\N	42
44	81.7699966	0	89.9499969	\N	2019-06-28 15:24:11.367	\N	\N	t	f	0	оказание помощи в получении юридических услуг	\N	42
45	245.360001	0	269.899994	\N	2019-06-28 15:24:11.374	\N	\N	t	f	0	оказание помощи в защите прав и законных интересов получателей социальных услуг	\N	42
46	72.1500015	0	79.3700027	\N	2019-06-28 15:24:11.382	\N	\N	t	f	0	консультирование по вопросам, связанным с правом граждан на социальное обслуживание и защиту своих интересов	\N	42
47	95.6999969	0	105.269997	\N	2019-06-28 15:24:11.392	\N	\N	t	f	0	оказание помощи в вопросах, связанных с пенсионным обеспечением	\N	42
49	0	0	0	\N	2019-06-28 15:24:11.409	\N	\N	t	f	0	оказание материальной помощи, в соответствии с порядком, утвержденным Губернатором Приморского края 	\N	48
50	216.470001	0	238.119995	\N	2019-06-28 15:24:11.416	\N	\N	t	f	0	содействие населению в получении полагающихся льгот, пособий, компенсаций, алиментов и других выплат, улучшении жилищных условий в соответствии с федеральным законодательством и законодательством\nПриморского края	\N	48
52	96.2200012	0	105.839996	\N	2019-06-28 15:24:11.437	\N	\N	t	f	0	обучение инвалидов пользованию средствами ухода и техническими средствами реабилитации	\N	51
\.


--
-- Data for Name: social_service_catalog; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.social_service_catalog (id, date_creation, date_enable, description, enable, free, title, organization_id) FROM stdin;
1	\N	2019-06-28 15:24:10.879	\N	t	f	Социально-бытовые услуги	\N
18	\N	2019-06-28 15:24:11.141	\N	t	f	Социально-медицинские услуги	\N
27	\N	2019-06-28 15:24:11.233	\N	t	f	Социально-психологические услуги	\N
32	\N	2019-06-28 15:24:11.27	\N	t	f	Социально-педагогические услуги	\N
38	\N	2019-06-28 15:24:11.315	\N	t	f	Социально-трудовые услуги	\N
42	\N	2019-06-28 15:24:11.348	\N	t	f	Социально-правовые услуги	\N
48	\N	2019-06-28 15:24:11.401	\N	t	f	Социально-экономические услуги	\N
51	\N	2019-06-28 15:24:11.424	\N	t	f	Услуги в целях повышения коммуникативного потенциала получателей социальных услуг, имеющих ограничения жизнедеятельности, в том числе детей-инвалидов	\N
\.


--
-- Data for Name: social_service_date_enable; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.social_service_date_enable (social_service_id, date_enable) FROM stdin;
\.


--
-- Data for Name: task_social_service; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.task_social_service (id, date_create, date_start, date_stop, duration, enable, version, employee_id, needy_id, social_service_id, status, priority, task_status) FROM stdin;
371	2019-11-12 12:06:52.489	\N	2019-11-20 16:59:02.123	\N	f	8	57	52	19	\N	\N	\N
372	2019-11-20 17:18:06.436	\N	\N	\N	t	0	57	52	12	\N	\N	\N
357	2019-10-22 11:27:18.391	\N	2019-11-20 17:23:47.891	\N	f	1	57	53	19	\N	\N	\N
374	2019-11-30 06:51:17.012	\N	\N	\N	t	0	\N	52	2	\N	\N	\N
375	2019-12-01 08:35:40.106	\N	\N	\N	t	0	57	52	\N	\N	\N	\N
377	2019-12-01 08:39:34.559	\N	\N	\N	t	0	57	52	2	\N	\N	\N
254	2019-10-10 09:11:23.419	\N	\N	\N	t	0	65	66	15	\N	\N	\N
270	2019-10-10 12:56:48.368	\N	\N	\N	t	0	65	66	12	\N	\N	\N
271	2019-10-10 12:56:59.772	\N	\N	\N	t	0	65	66	7	\N	\N	\N
272	2019-10-10 12:58:34.018	\N	\N	\N	t	0	65	66	7	\N	\N	\N
274	2019-10-10 13:00:53.622	\N	\N	\N	t	0	65	66	15	\N	\N	\N
366	2019-11-07 14:51:42.939	\N	\N	\N	t	0	65	66	5	\N	\N	\N
367	2019-11-07 15:15:24.6	\N	\N	\N	t	0	65	66	19	\N	\N	\N
273	2019-10-10 13:00:44.39	\N	\N	\N	f	0	65	66	19	\N	\N	\N
256	2019-10-10 09:13:13.638	\N	\N	\N	f	0	65	66	21	\N	\N	\N
253	2019-10-10 09:06:17.2	\N	2019-11-15 16:19:58.572	\N	t	4	65	66	6	\N	\N	\N
361	2019-11-06 08:21:39.276	\N	\N	\N	t	2	57	52	9	\N	\N	\N
358	2019-11-02 08:24:19.839	\N	2019-11-20 16:31:05.099	\N	f	2	57	52	8	\N	\N	\N
370	2019-11-12 12:04:40.443	\N	2019-11-20 16:36:23.068	\N	f	2	57	52	21	\N	\N	\N
355	2019-10-22 09:48:08.312	\N	2019-11-20 16:42:07.542	\N	f	4	57	52	15	\N	\N	\N
360	2019-11-03 05:23:27.428	\N	2019-11-20 16:43:58.914	\N	f	6	57	52	14	\N	\N	\N
363	2019-11-06 13:33:59.565	\N	\N	\N	t	1	57	52	9	\N	\N	\N
364	2019-11-07 14:04:26.151	\N	2019-11-20 16:45:40.896	\N	f	2	57	52	4	\N	\N	\N
368	2019-11-09 07:45:38.807	\N	2019-11-20 16:56:15.962	\N	f	2	57	52	13	\N	\N	\N
369	2019-11-09 08:23:07.337	\N	\N	\N	t	7	57	52	3	\N	\N	\N
362	2019-11-06 13:32:54.383	\N	\N	\N	t	5	57	52	9	\N	\N	\N
359	2019-11-03 04:11:35.954	\N	\N	\N	t	8	57	52	7	\N	\N	\N
\.


--
-- Data for Name: test_person; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.test_person (id, name) FROM stdin;
1	one
2	two
\.


--
-- Data for Name: user_personal; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.user_personal (id, active, date_of_birth, place_birth, place_residence, user_sex) FROM stdin;
\.


--
-- Data for Name: user_personal_address; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.user_personal_address (user_personal_id, address) FROM stdin;
\.


--
-- Data for Name: user_personal_documents; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.user_personal_documents (user_personal_id, documents_id) FROM stdin;
\.


--
-- Data for Name: user_personal_employment; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.user_personal_employment (user_personal_id, employment) FROM stdin;
\.


--
-- Data for Name: user_personal_other_contacts; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.user_personal_other_contacts (user_personal_id, other_contacts) FROM stdin;
\.


--
-- Data for Name: user_personal_phone; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.user_personal_phone (user_personal_id, phone) FROM stdin;
\.


--
-- Data for Name: user_personal_social_factors; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.user_personal_social_factors (user_personal_id, social_factors) FROM stdin;
\.


--
-- Data for Name: usr; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.usr (id, address, date_creation, descriptions, email, enable, firstname, lastname, locale, middlename, mobile, nickname, notes, password, phone, role, sub, version, organization_id, personal_id) FROM stdin;
52	Владивосток	\N	\N	lonshakovata@mail.ru	t	 Тамара	Лошанкова	\N	Александровна	\N	lonshakovata	\N	12345678		0	\N	0	\N	\N
53	Владивосток	\N	\N	gutnikgg@mail.ru	t	Галина	Гутник	\N	Григорьевна	\N	gutnikgg	\N	12345678		0	\N	0	\N	\N
58	\N	\N	\N	valuevaea@pcson.ru	t	Елена	Валуева	\N	Анатольевна	\N	valuevaea	\N	12345678	\N	2	\N	3	\N	\N
57	\N	\N	\N	sulimaag@pcson.ru	t	Анна	Сулима	\N	Геннадьевна	\N	sulimaag	\N	12345678	\N	1	\N	2	\N	\N
66	Владивосток	\N	\N	krulevvg@pcson.ru	t	Виктор	Крулев	\N	Григорьевич	\N	krulevvg	\N	12345678		0	\N	0	\N	\N
67	Владивосток	\N	\N	mironenkogk@pcson.ru	t	Галина	Мироненко	\N	Константиновна	\N	mironenkogk	\N	12345678		0	\N	0	\N	\N
65	Владивосток	\N	\N	doroninan@pcson.ru	t	Александр	Доронин	\N	Николаевич	\N	doroninan	\N	12345678		1	\N	1	\N	\N
42	\N	\N	Temprorary user	habarovauv@pcson.ru	t	Юлия	Хабарова	\N	Вячеславовна	\N	habarovauv	\N	12345678	\N	1	\N	1	35	\N
38	\N	\N	\N	polyakovaas@pcson.ru	t	Анна	Полякова	\N	Сергеевна	\N	polyakovaas	\N	12345678	\N	2	\N	0	\N	\N
39	\N	\N	\N	tangasovaua@pcson.ru	t	Юлия	Тангасова	\N	Юрьевна	\N	tangasovaua	\N	12345678	\N	2	\N	2	\N	\N
36	\N	\N	\N	kasakinaou@pcson.ru	t	Олеся	Касакина	\N	Юрьевна	\N	kasakinaou	\N	12345678	\N	2	\N	0	\N	\N
37	\N	\N	\N	koshevayaov@pcson.ru	t	Ольга	Кошевая	\N	Владимировна	\N	koshevayaov	\N	12345678	\N	2	\N	2	35	\N
\.


--
-- Data for Name: usr_auth_date_time; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.usr_auth_date_time (usr_id, auth_date_time) FROM stdin;
\.


--
-- Data for Name: usr_date_enable; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.usr_date_enable (usr_id, date_enable) FROM stdin;
\.


--
-- Data for Name: usr_employee; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.usr_employee (id, role, version) FROM stdin;
\.


--
-- Data for Name: usr_needy; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.usr_needy (id, role, version) FROM stdin;
\.


--
-- Data for Name: usr_organizations; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.usr_organizations (usr_id, organizations_id) FROM stdin;
37	40
58	35
\.


--
-- Data for Name: usr_roles; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.usr_roles (usr_id, roles) FROM stdin;
39	2
39	3
52	0
53	0
58	2
58	4
57	1
66	0
67	0
65	1
42	1
\.


--
-- Data for Name: usr_users; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.usr_users (usr_id, users_id) FROM stdin;
57	52
57	53
65	66
\.


--
-- Data for Name: usr_users2; Type: TABLE DATA; Schema: public; Owner: socialhelper
--

COPY public.usr_users2 (usr_id, users2_id) FROM stdin;
42	52
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: socialhelper
--

SELECT pg_catalog.setval('public.hibernate_sequence', 379, true);


--
-- Name: document document_pkey; Type: CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.document
    ADD CONSTRAINT document_pkey PRIMARY KEY (id);


--
-- Name: organization organization_pkey; Type: CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.organization
    ADD CONSTRAINT organization_pkey PRIMARY KEY (id);


--
-- Name: service service_pkey; Type: CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.service
    ADD CONSTRAINT service_pkey PRIMARY KEY (id);


--
-- Name: social_service_catalog social_service_catalog_pkey; Type: CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.social_service_catalog
    ADD CONSTRAINT social_service_catalog_pkey PRIMARY KEY (id);


--
-- Name: social_service social_service_pkey; Type: CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.social_service
    ADD CONSTRAINT social_service_pkey PRIMARY KEY (id);


--
-- Name: task_social_service task_social_service_pkey; Type: CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.task_social_service
    ADD CONSTRAINT task_social_service_pkey PRIMARY KEY (id);


--
-- Name: test_person test_person_pkey; Type: CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.test_person
    ADD CONSTRAINT test_person_pkey PRIMARY KEY (id);


--
-- Name: user_personal_documents uk_7qtr0hd0s6etk8laddp6pyepd; Type: CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.user_personal_documents
    ADD CONSTRAINT uk_7qtr0hd0s6etk8laddp6pyepd UNIQUE (documents_id);


--
-- Name: organization_users uk_ag03e4qasggh4qxd93rfod6wk; Type: CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.organization_users
    ADD CONSTRAINT uk_ag03e4qasggh4qxd93rfod6wk UNIQUE (users_id);


--
-- Name: usr_organizations uk_g1fpheegp5v8hjgbs3gfu6q5j; Type: CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.usr_organizations
    ADD CONSTRAINT uk_g1fpheegp5v8hjgbs3gfu6q5j UNIQUE (organizations_id);


--
-- Name: user_personal user_personal_pkey; Type: CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.user_personal
    ADD CONSTRAINT user_personal_pkey PRIMARY KEY (id);


--
-- Name: usr_employee usr_employee_pkey; Type: CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.usr_employee
    ADD CONSTRAINT usr_employee_pkey PRIMARY KEY (id);


--
-- Name: usr_needy usr_needy_pkey; Type: CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.usr_needy
    ADD CONSTRAINT usr_needy_pkey PRIMARY KEY (id);


--
-- Name: usr usr_pkey; Type: CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.usr
    ADD CONSTRAINT usr_pkey PRIMARY KEY (id);


--
-- Name: organization_users fk15s2npsq3mkltwy8r6fuc3yky; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.organization_users
    ADD CONSTRAINT fk15s2npsq3mkltwy8r6fuc3yky FOREIGN KEY (organization_id) REFERENCES public.organization(id);


--
-- Name: organization_open_hours fk26635gmxhkoi5lkk4i4p8nr93; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.organization_open_hours
    ADD CONSTRAINT fk26635gmxhkoi5lkk4i4p8nr93 FOREIGN KEY (organization_id) REFERENCES public.organization(id);


--
-- Name: user_personal_documents fk2s49anhfqph01c7jodiynb82s; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.user_personal_documents
    ADD CONSTRAINT fk2s49anhfqph01c7jodiynb82s FOREIGN KEY (user_personal_id) REFERENCES public.user_personal(id);


--
-- Name: organization_address fk4bxk7tixquspybhj8mdi6vyhi; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.organization_address
    ADD CONSTRAINT fk4bxk7tixquspybhj8mdi6vyhi FOREIGN KEY (organization_id) REFERENCES public.organization(id);


--
-- Name: usr_users2 fk5orycvv2lhlonlbbllcinmkwd; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.usr_users2
    ADD CONSTRAINT fk5orycvv2lhlonlbbllcinmkwd FOREIGN KEY (usr_id) REFERENCES public.usr(id);


--
-- Name: user_personal_employment fk69jn0bmhgsh97xskojqq9u0mn; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.user_personal_employment
    ADD CONSTRAINT fk69jn0bmhgsh97xskojqq9u0mn FOREIGN KEY (user_personal_id) REFERENCES public.user_personal(id);


--
-- Name: user_personal_other_contacts fk6dw4re5vpw476c04dl7m96okk; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.user_personal_other_contacts
    ADD CONSTRAINT fk6dw4re5vpw476c04dl7m96okk FOREIGN KEY (user_personal_id) REFERENCES public.user_personal(id);


--
-- Name: user_personal_documents fk6vyan9rqvqnjdj4tghp073f5v; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.user_personal_documents
    ADD CONSTRAINT fk6vyan9rqvqnjdj4tghp073f5v FOREIGN KEY (documents_id) REFERENCES public.document(id);


--
-- Name: usr_roles fk79fgl9xi3vdyj5q7bq1484kf0; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.usr_roles
    ADD CONSTRAINT fk79fgl9xi3vdyj5q7bq1484kf0 FOREIGN KEY (usr_id) REFERENCES public.usr(id);


--
-- Name: task_social_service fkcv5wqtbndegiisn7e5nb4s3fo; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.task_social_service
    ADD CONSTRAINT fkcv5wqtbndegiisn7e5nb4s3fo FOREIGN KEY (needy_id) REFERENCES public.usr(id);


--
-- Name: usr_auth_date_time fkea2jsd5u6xgienc38308fm2fu; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.usr_auth_date_time
    ADD CONSTRAINT fkea2jsd5u6xgienc38308fm2fu FOREIGN KEY (usr_id) REFERENCES public.usr(id);


--
-- Name: usr_users fkedbfkr4k60kwmgspgmxop2lsf; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.usr_users
    ADD CONSTRAINT fkedbfkr4k60kwmgspgmxop2lsf FOREIGN KEY (users_id) REFERENCES public.usr(id);


--
-- Name: organization_other_contacts fkejfdrmorxgwtj3yf208qgynv3; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.organization_other_contacts
    ADD CONSTRAINT fkejfdrmorxgwtj3yf208qgynv3 FOREIGN KEY (organization_id) REFERENCES public.organization(id);


--
-- Name: user_personal_social_factors fkex9tk912mawj7p06yehtkpnsc; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.user_personal_social_factors
    ADD CONSTRAINT fkex9tk912mawj7p06yehtkpnsc FOREIGN KEY (user_personal_id) REFERENCES public.user_personal(id);


--
-- Name: user_personal_address fkfn7x6i6odgq0xftd6vbsm6iwc; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.user_personal_address
    ADD CONSTRAINT fkfn7x6i6odgq0xftd6vbsm6iwc FOREIGN KEY (user_personal_id) REFERENCES public.user_personal(id);


--
-- Name: usr fkix2aswvfp3u4rgnmx62fkegdu; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.usr
    ADD CONSTRAINT fkix2aswvfp3u4rgnmx62fkegdu FOREIGN KEY (personal_id) REFERENCES public.user_personal(id);


--
-- Name: usr_organizations fkk93fb2gnw1949lo7qm7ri6dua; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.usr_organizations
    ADD CONSTRAINT fkk93fb2gnw1949lo7qm7ri6dua FOREIGN KEY (organizations_id) REFERENCES public.organization(id);


--
-- Name: user_personal_phone fkk9s4rd4y0hboa1r6cuyo8n6b1; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.user_personal_phone
    ADD CONSTRAINT fkk9s4rd4y0hboa1r6cuyo8n6b1 FOREIGN KEY (user_personal_id) REFERENCES public.user_personal(id);


--
-- Name: task_social_service fkl5767p7jr9rsiousvkjing76v; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.task_social_service
    ADD CONSTRAINT fkl5767p7jr9rsiousvkjing76v FOREIGN KEY (employee_id) REFERENCES public.usr(id);


--
-- Name: person_persons fkl86a7w97ojen72x745m99hudw; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.person_persons
    ADD CONSTRAINT fkl86a7w97ojen72x745m99hudw FOREIGN KEY (person_id) REFERENCES public.test_person(id);


--
-- Name: organization_users fklbjsfrosy0f08esargo27qxs8; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.organization_users
    ADD CONSTRAINT fklbjsfrosy0f08esargo27qxs8 FOREIGN KEY (users_id) REFERENCES public.usr(id);


--
-- Name: usr_date_enable fkoep5j64d0mw89d8xrdxeknj0; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.usr_date_enable
    ADD CONSTRAINT fkoep5j64d0mw89d8xrdxeknj0 FOREIGN KEY (usr_id) REFERENCES public.usr(id);


--
-- Name: social_service fkohprndk19opack8po2uu30ob3; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.social_service
    ADD CONSTRAINT fkohprndk19opack8po2uu30ob3 FOREIGN KEY (social_service_catalog_id) REFERENCES public.social_service_catalog(id);


--
-- Name: organization_photo_urls fkoj5r3kdny9bt3jsmyocf10eli; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.organization_photo_urls
    ADD CONSTRAINT fkoj5r3kdny9bt3jsmyocf10eli FOREIGN KEY (organization_id) REFERENCES public.organization(id);


--
-- Name: usr fkp96ghcoiahdqm697daggv2hdq; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.usr
    ADD CONSTRAINT fkp96ghcoiahdqm697daggv2hdq FOREIGN KEY (organization_id) REFERENCES public.organization(id);


--
-- Name: task_social_service fkq37sbhppj2ofipejq8kb9uufd; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.task_social_service
    ADD CONSTRAINT fkq37sbhppj2ofipejq8kb9uufd FOREIGN KEY (social_service_id) REFERENCES public.social_service(id);


--
-- Name: usr_organizations fkq4h6y573gub4jk6e5tpi463ow; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.usr_organizations
    ADD CONSTRAINT fkq4h6y573gub4jk6e5tpi463ow FOREIGN KEY (usr_id) REFERENCES public.usr(id);


--
-- Name: organization_coordinates fkqft6h2cjel04ss7jpjgytom7j; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.organization_coordinates
    ADD CONSTRAINT fkqft6h2cjel04ss7jpjgytom7j FOREIGN KEY (organization_id) REFERENCES public.organization(id);


--
-- Name: person_persons fkqgwrrfj8rq2auqmbicqnk2ghk; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.person_persons
    ADD CONSTRAINT fkqgwrrfj8rq2auqmbicqnk2ghk FOREIGN KEY (relation_id) REFERENCES public.test_person(id);


--
-- Name: usr_users fkqndrppju6n8vxh9wxufsmaagu; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.usr_users
    ADD CONSTRAINT fkqndrppju6n8vxh9wxufsmaagu FOREIGN KEY (usr_id) REFERENCES public.usr(id);


--
-- Name: social_service_catalog fksrwslb674kea45jky6cig0r14; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.social_service_catalog
    ADD CONSTRAINT fksrwslb674kea45jky6cig0r14 FOREIGN KEY (organization_id) REFERENCES public.organization(id);


--
-- Name: usr_users2 fksxyl1pr84ypar8abjfh6cq33v; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.usr_users2
    ADD CONSTRAINT fksxyl1pr84ypar8abjfh6cq33v FOREIGN KEY (users2_id) REFERENCES public.usr(id);


--
-- Name: organization_phone fktqp37ctjfciegsn731sa27tg2; Type: FK CONSTRAINT; Schema: public; Owner: socialhelper
--

ALTER TABLE ONLY public.organization_phone
    ADD CONSTRAINT fktqp37ctjfciegsn731sa27tg2 FOREIGN KEY (organization_id) REFERENCES public.organization(id);


--
-- PostgreSQL database dump complete
--

