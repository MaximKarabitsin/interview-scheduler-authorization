--
-- PostgreSQL database dump
--

-- Dumped from database version 12.0
-- Dumped by pg_dump version 12.0

-- Started on 2019-12-01 15:07:55

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
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 2855 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 16597)
-- Name: hibernate_sequences; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.hibernate_sequences (
    sequence_name character varying(255) NOT NULL,
    sequence_next_hi_value bigint
);


ALTER TABLE public.hibernate_sequences OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16657)
-- Name: policies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.policies (
    id bigint NOT NULL,
    combine_algorithm character varying(255) NOT NULL,
    description character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    target character varying(255) NOT NULL
);


ALTER TABLE public.policies OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16665)
-- Name: policies_rules; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.policies_rules (
    policy_id bigint NOT NULL,
    rule_id bigint NOT NULL
);


ALTER TABLE public.policies_rules OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16670)
-- Name: policy_sets; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.policy_sets (
    id bigint NOT NULL,
    combine_algorithm character varying(255) NOT NULL,
    description character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    target character varying(255) NOT NULL
);


ALTER TABLE public.policy_sets OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 16678)
-- Name: policy_sets_policies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.policy_sets_policies (
    policy_set_id bigint NOT NULL,
    policy_id bigint NOT NULL
);


ALTER TABLE public.policy_sets_policies OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16628)
-- Name: rules; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rules (
    id bigint NOT NULL,
    condition character varying(255) NOT NULL,
    description character varying(255) NOT NULL,
    effect boolean NOT NULL,
    name character varying(255) NOT NULL,
    target character varying(255) NOT NULL
);


ALTER TABLE public.rules OWNER TO postgres;

--
-- TOC entry 2709 (class 2606 OID 16601)
-- Name: hibernate_sequences hibernate_sequences_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.hibernate_sequences
    ADD CONSTRAINT hibernate_sequences_pkey PRIMARY KEY (sequence_name);


--
-- TOC entry 2713 (class 2606 OID 16664)
-- Name: policies policies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.policies
    ADD CONSTRAINT policies_pkey PRIMARY KEY (id);


--
-- TOC entry 2715 (class 2606 OID 16669)
-- Name: policies_rules policies_rules_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.policies_rules
    ADD CONSTRAINT policies_rules_pkey PRIMARY KEY (policy_id, rule_id);


--
-- TOC entry 2717 (class 2606 OID 16677)
-- Name: policy_sets policy_sets_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.policy_sets
    ADD CONSTRAINT policy_sets_pkey PRIMARY KEY (id);


--
-- TOC entry 2719 (class 2606 OID 16682)
-- Name: policy_sets_policies policy_sets_policies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.policy_sets_policies
    ADD CONSTRAINT policy_sets_policies_pkey PRIMARY KEY (policy_set_id, policy_id);


--
-- TOC entry 2711 (class 2606 OID 16635)
-- Name: rules rules_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rules
    ADD CONSTRAINT rules_pkey PRIMARY KEY (id);


--
-- TOC entry 2721 (class 2606 OID 16718)
-- Name: policies_rules policies_rules_policies_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.policies_rules
    ADD CONSTRAINT policies_rules_policies_id_fk FOREIGN KEY (policy_id) REFERENCES public.policies(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2720 (class 2606 OID 16713)
-- Name: policies_rules policies_rules_rules_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.policies_rules
    ADD CONSTRAINT policies_rules_rules_id_fk FOREIGN KEY (rule_id) REFERENCES public.rules(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2722 (class 2606 OID 16703)
-- Name: policy_sets_policies policy_sets_policies_policies_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.policy_sets_policies
    ADD CONSTRAINT policy_sets_policies_policies_id_fk FOREIGN KEY (policy_id) REFERENCES public.policies(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2723 (class 2606 OID 16708)
-- Name: policy_sets_policies policy_sets_policies_policy_sets_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.policy_sets_policies
    ADD CONSTRAINT policy_sets_policies_policy_sets_id_fk FOREIGN KEY (policy_set_id) REFERENCES public.policy_sets(id) ON UPDATE CASCADE ON DELETE CASCADE;


-- Completed on 2019-12-01 15:07:56

--
-- PostgreSQL database dump complete
--

