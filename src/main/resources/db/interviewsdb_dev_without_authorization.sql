--
-- PostgreSQL database dump
--

-- Dumped from database version 12.0
-- Dumped by pg_dump version 12.0

-- Started on 2019-11-17 16:10:32

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 16796)
-- Name: candidates; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.candidates (
    id bigint NOT NULL,
    additional_information character varying(255),
    additional_languages character varying(255),
    bachelor_average_grade character varying(255),
    bachelor_current_year character varying(255),
    bachelor_faculity character varying(255),
    bachelor_field character varying(255),
    bachelor_finish_year character varying(255),
    bachelor_last_average_grade character varying(255),
    bachelor_mode_of_study character varying(255),
    bachelor_start_year character varying(255),
    bachelor_university character varying(255),
    birthday date NOT NULL,
    c_plus_plus_level character varying(255),
    c_sharp_level character varying(255),
    citizenship character varying(255),
    city character varying(255) NOT NULL,
    creation_date date NOT NULL,
    email character varying(255) NOT NULL,
    english_reading character varying(255),
    english_speaking character varying(255),
    english_writing character varying(255),
    informamtion_aboutnc character varying(255),
    java_level character varying(255),
    master_average_grade character varying(255),
    master_current_year character varying(255),
    master_faculity character varying(255),
    master_field character varying(255),
    master_finish_year character varying(255),
    master_last_average_grade character varying(255),
    master_mode_of_study character varying(255),
    master_start_year character varying(255),
    master_university character varying(255),
    name character varying(255) NOT NULL,
    patronymic character varying(255),
    phone character varying(255) NOT NULL,
    projects character varying(255),
    skype character varying(255),
    sql_level character varying(255),
    study_continuation character varying(255),
    study_fieldnc character varying(255),
    surname character varying(255) NOT NULL,
    web_level character varying(255),
    work_company character varying(255),
    work_duration character varying(255),
    work_position character varying(255)
);


ALTER TABLE public.candidates OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16802)
-- Name: employee_reports; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employee_reports (
    interview_id bigint NOT NULL,
    employee_id bigint NOT NULL,
    report_id bigint
);


ALTER TABLE public.employee_reports OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16805)
-- Name: employee_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employee_roles (
    employee_id bigint NOT NULL,
    roles character varying(255)
);


ALTER TABLE public.employee_roles OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16808)
-- Name: employees; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employees (
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    surname character varying(255) NOT NULL
);


ALTER TABLE public.employees OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16814)
-- Name: hibernate_sequences; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.hibernate_sequences (
    sequence_name character varying(255) NOT NULL,
    sequence_next_hi_value bigint
);


ALTER TABLE public.hibernate_sequences OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 16817)
-- Name: interviews; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.interviews (
    id bigint NOT NULL,
    comments character varying(255),
    duration timestamp without time zone,
    final_decision character varying(255),
    final_grade double precision,
    start_time timestamp(6) with time zone NOT NULL,
    candidate_id bigint NOT NULL
);


ALTER TABLE public.interviews OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 16823)
-- Name: reports; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reports (
    id bigint NOT NULL,
    additional_skills character varying(255) NOT NULL,
    algorithms_comment character varying(255) NOT NULL,
    algorithms_grade character varying(255) NOT NULL,
    comments character varying(255) NOT NULL,
    db_andsqlcomment character varying(255) NOT NULL,
    db_andsql_grade character varying(255) NOT NULL,
    development_experience character varying(255) NOT NULL,
    final_decision character varying(255) NOT NULL,
    javaeecomment character varying(255) NOT NULL,
    javaeegrade character varying(255) NOT NULL,
    javasecomment character varying(255) NOT NULL,
    javasegrade character varying(255) NOT NULL,
    oop_comment character varying(255) NOT NULL,
    oop_grade character varying(255) NOT NULL,
    status character varying(255) NOT NULL,
    self_study_ability character varying(255) NOT NULL,
    speed_of_thinking character varying(255) NOT NULL,
    team_role character varying(255) NOT NULL,
    web_comment character varying(255) NOT NULL,
    web_grade character varying(255) NOT NULL,
    work_readiness character varying(255) NOT NULL
);


ALTER TABLE public.reports OWNER TO postgres;

--
-- TOC entry 2714 (class 2606 OID 16830)
-- Name: candidates candidates_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.candidates
    ADD CONSTRAINT candidates_pkey PRIMARY KEY (id);


--
-- TOC entry 2716 (class 2606 OID 16832)
-- Name: employees employees_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT employees_pkey PRIMARY KEY (id);


--
-- TOC entry 2720 (class 2606 OID 16834)
-- Name: hibernate_sequences hibernate_sequences_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hibernate_sequences
    ADD CONSTRAINT hibernate_sequences_pkey PRIMARY KEY (sequence_name);


--
-- TOC entry 2722 (class 2606 OID 16836)
-- Name: interviews interviews_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.interviews
    ADD CONSTRAINT interviews_pkey PRIMARY KEY (id);


--
-- TOC entry 2724 (class 2606 OID 16838)
-- Name: reports reports_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reports
    ADD CONSTRAINT reports_pkey PRIMARY KEY (id);


--
-- TOC entry 2718 (class 2606 OID 16840)
-- Name: employees uk_j9xgmd0ya5jmus09o0b8pqrpb; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT uk_j9xgmd0ya5jmus09o0b8pqrpb UNIQUE (email);


--
-- TOC entry 2728 (class 2606 OID 16841)
-- Name: employee_roles fk3uwwaxeiucvfixgd45etkjgmg; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee_roles
    ADD CONSTRAINT fk3uwwaxeiucvfixgd45etkjgmg FOREIGN KEY (employee_id) REFERENCES public.employees(id);


--
-- TOC entry 2729 (class 2606 OID 16846)
-- Name: interviews fk4yhqbcuvek69rr37nr6lwau77; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.interviews
    ADD CONSTRAINT fk4yhqbcuvek69rr37nr6lwau77 FOREIGN KEY (candidate_id) REFERENCES public.candidates(id);


--
-- TOC entry 2725 (class 2606 OID 16851)
-- Name: employee_reports fk54dk94obsunajxbbdp3ufn290; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee_reports
    ADD CONSTRAINT fk54dk94obsunajxbbdp3ufn290 FOREIGN KEY (interview_id) REFERENCES public.interviews(id);


--
-- TOC entry 2726 (class 2606 OID 16856)
-- Name: employee_reports fkabaiypr097hogw51gj5usw0ee; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee_reports
    ADD CONSTRAINT fkabaiypr097hogw51gj5usw0ee FOREIGN KEY (employee_id) REFERENCES public.employees(id);


--
-- TOC entry 2727 (class 2606 OID 16861)
-- Name: employee_reports fkb16sctalejcejb6hgf9avg78q; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee_reports
    ADD CONSTRAINT fkb16sctalejcejb6hgf9avg78q FOREIGN KEY (report_id) REFERENCES public.reports(id);


-- Completed on 2019-11-17 16:10:37

--
-- PostgreSQL database dump complete
--

