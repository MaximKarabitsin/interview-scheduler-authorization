ALTER TABLE public.candidates 
ADD COLUMN IF NOT EXISTS office_id bigint NOT NULL;


ALTER TABLE public.employees 
ADD COLUMN IF NOT EXISTS office_id bigint NOT NULL,
ADD COLUMN IF NOT EXISTS interviewer boolean NOT NULL;


CREATE TABLE IF NOT EXISTS public.offices
(
    id bigint NOT NULL,
    city character varying(255) NOT NULL
);

ALTER TABLE public.offices OWNER to postgres;


CREATE TABLE IF NOT EXISTS public.rules
(
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
	description character varying(255) NOT NULL,
    target character varying(255) NOT NULL,
    condition character varying(255) NOT NULL,
    effect boolean NOT NULL
);

ALTER TABLE public.rules OWNER to postgres;


CREATE TABLE IF NOT EXISTS public.policy
(
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    description character varying(255) NOT NULL,
    target character varying(255) NOT NULL,
    rule_combine_algorithm character varying(255) NOT NULL
);

ALTER TABLE public.policy OWNER to postgres;


CREATE TABLE IF NOT EXISTS public.policy_rules
(
    policy_id bigint NOT NULL,
    rule_id bigint NOT NULL
);

ALTER TABLE public.policy_rules OWNER to postgres;


ALTER TABLE ONLY public.candidates DROP CONSTRAINT IF EXISTS candidates_fk0;
ALTER TABLE ONLY public.policy_rules DROP CONSTRAINT IF EXISTS policy_rules_fk0;
ALTER TABLE ONLY public.policy_rules DROP CONSTRAINT IF EXISTS policy_rules_fk1;


ALTER TABLE ONLY public.offices DROP CONSTRAINT IF EXISTS offices_pkey;
ALTER TABLE ONLY public.rules DROP CONSTRAINT IF EXISTS rules_pkey;
ALTER TABLE ONLY public.policy DROP CONSTRAINT IF EXISTS policy_pkey;


ALTER TABLE ONLY public.offices
    ADD CONSTRAINT offices_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.rules
    ADD CONSTRAINT rules_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.policy
    ADD CONSTRAINT policy_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.candidates
    ADD CONSTRAINT candidates_fk0 FOREIGN KEY (office_id)
	REFERENCES public.offices(id);


ALTER TABLE ONLY public.policy_rules
    ADD CONSTRAINT policy_rules_fk0 FOREIGN KEY (policy_id)
	REFERENCES public.policy(id),
	ADD CONSTRAINT policy_rules_fk1 FOREIGN KEY (rule_id)
	REFERENCES public.rules(id);