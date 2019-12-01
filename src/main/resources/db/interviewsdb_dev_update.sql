CREATE SCHEMA IF NOT EXISTS public;

ALTER SCHEMA public OWNER TO postgres;

CREATE TABLE IF NOT EXISTS public.hibernate_sequences (
    sequence_name character varying(255) NOT NULL,
    sequence_next_hi_value bigint
);

ALTER TABLE public.hibernate_sequences OWNER TO postgres;


CREATE TABLE IF NOT EXISTS public.policies (
    id bigint NOT NULL,
    combine_algorithm character varying(255) NOT NULL,
    description character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    target character varying(255) NOT NULL
);

ALTER TABLE public.policies OWNER TO postgres;


CREATE TABLE IF NOT EXISTS public.policies_rules (
    policy_id bigint NOT NULL,
    rule_id bigint NOT NULL
);

ALTER TABLE public.policies_rules OWNER TO postgres;


CREATE TABLE IF NOT EXISTS public.policy_sets (
    id bigint NOT NULL,
    combine_algorithm character varying(255) NOT NULL,
    description character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    target character varying(255) NOT NULL
);

ALTER TABLE public.policy_sets OWNER TO postgres;


CREATE TABLE IF NOT EXISTS public.policy_sets_policies (
    policy_set_id bigint NOT NULL,
    policy_id bigint NOT NULL
);

ALTER TABLE public.policy_sets_policies OWNER TO postgres;


CREATE TABLE IF NOT EXISTS public.rules (
    id bigint NOT NULL,
    condition character varying(255) NOT NULL,
    description character varying(255) NOT NULL,
    effect boolean NOT NULL,
    name character varying(255) NOT NULL,
    target character varying(255) NOT NULL
);

ALTER TABLE public.rules OWNER TO postgres;


ALTER TABLE ONLY public.policy_sets_policies DROP CONSTRAINT IF EXISTS policy_sets_policies_policy_sets_id_fk;
ALTER TABLE ONLY public.policy_sets_policies DROP CONSTRAINT IF EXISTS policy_sets_policies_policies_id_fk;
ALTER TABLE ONLY public.policies_rules DROP CONSTRAINT IF EXISTS policies_rules_rules_id_fk;
ALTER TABLE ONLY public.policies_rules DROP CONSTRAINT IF EXISTS policies_rules_policies_id_fk;


ALTER TABLE ONLY public.rules DROP CONSTRAINT IF EXISTS rules_pkey;
ALTER TABLE ONLY public.policy_sets_policies DROP CONSTRAINT IF EXISTS policy_sets_policies_pkey;
ALTER TABLE ONLY public.policy_sets DROP CONSTRAINT IF EXISTS policy_sets_pkey;
ALTER TABLE ONLY public.policies_rules DROP CONSTRAINT IF EXISTS policies_rules_pkey;
ALTER TABLE ONLY public.policies DROP CONSTRAINT IF EXISTS policies_pkey;
ALTER TABLE ONLY public.hibernate_sequences DROP CONSTRAINT IF EXISTS hibernate_sequences_pkey;



ALTER TABLE ONLY public.hibernate_sequences
    ADD CONSTRAINT hibernate_sequences_pkey PRIMARY KEY (sequence_name);

ALTER TABLE ONLY public.policies
    ADD CONSTRAINT policies_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.policies_rules
    ADD CONSTRAINT policies_rules_pkey PRIMARY KEY (policy_id, rule_id);

ALTER TABLE ONLY public.policy_sets
    ADD CONSTRAINT policy_sets_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.policy_sets_policies
    ADD CONSTRAINT policy_sets_policies_pkey PRIMARY KEY (policy_set_id, policy_id);

ALTER TABLE ONLY public.rules
    ADD CONSTRAINT rules_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.policies_rules
    ADD CONSTRAINT policies_rules_policies_id_fk FOREIGN KEY (policy_id) REFERENCES public.policies(id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY public.policies_rules
    ADD CONSTRAINT policies_rules_rules_id_fk FOREIGN KEY (rule_id) REFERENCES public.rules(id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY public.policy_sets_policies
    ADD CONSTRAINT policy_sets_policies_policies_id_fk FOREIGN KEY (policy_id) REFERENCES public.policies(id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY public.policy_sets_policies
    ADD CONSTRAINT policy_sets_policies_policy_sets_id_fk FOREIGN KEY (policy_set_id) REFERENCES public.policy_sets(id) ON UPDATE CASCADE ON DELETE CASCADE;
