----------------------------- ACCOUNT ------------------------------------
CREATE TABLE IF NOT EXISTS public.account (
    id uuid NOT NULL,
    active_flag boolean NOT NULL,
    current_plan smallint,
    plan_expire_day smallint,
    payment_history jsonb NOT NULL,
    limit_of_profiles smallint,
    created_at timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    
    CONSTRAINT account_pkey PRIMARY KEY (id),
    CONSTRAINT account_current_plan_check CHECK (current_plan >= 0 AND current_plan <= 2)
);
ALTER TABLE IF EXISTS public.account
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.account_aud (
    rev integer NOT NULL,
    revtype smallint,
    id uuid NOT NULL,
    active_flag boolean NOT NULL,
    current_plan smallint,
    plan_expire_day smallint,
    payment_history jsonb NOT NULL,
    limit_of_profiles smallint
);

----------------------------- USER INFO ------------------------------------
CREATE TABLE IF NOT EXISTS public.user_info (
    id uuid NOT NULL,
    enabled_flag boolean NOT NULL,
    deleted_flag boolean NOT NULL,
    email character varying(255)  NOT NULL,
    password character varying(255)  NOT NULL,
    birth_date character varying(255)  NOT NULL,
    role_id smallint NOT NULL,
    account_id uuid,
    created_at timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    
    CONSTRAINT user_info_pkey PRIMARY KEY (id),
    CONSTRAINT user_info_email_key UNIQUE (email),
    CONSTRAINT user_info_role_check CHECK (role_id >= 1 AND role_id <= 3),
    CONSTRAINT user_info_account_id_key UNIQUE (account_id),
    CONSTRAINT user_info_account_id_fk FOREIGN KEY (account_id)
        REFERENCES public.account (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
ALTER TABLE IF EXISTS public.user_info
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.user_info_aud (
    rev integer NOT NULL,
    revtype smallint,
    id uuid NOT NULL,
    enabled_flag boolean NOT NULL,
    deleted_flag boolean NOT NULL,
    email character varying(255)  NOT NULL,
    password character varying(255)  NOT NULL,
    birth_date character varying(255)  NOT NULL,
    role_id smallint NOT NULL,
    account_id uuid
);

----------------------------- PROFILE ------------------------------------
CREATE TABLE IF NOT EXISTS public.profile (
    id uuid NOT NULL,
    account_id uuid,
    owner_name character varying(255) NOT NULL,
    icon_cod integer NOT NULL,
    seen_medias_ids jsonb NOT NULL,
    preferences jsonb NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,

    CONSTRAINT profile_pkey PRIMARY KEY (id),
    CONSTRAINT profile_account_id_fk FOREIGN KEY (account_id)
        REFERENCES public.account (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
ALTER TABLE IF EXISTS public.profile
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.profile_aud (
    rev integer NOT NULL,
    revtype smallint,
    id uuid NOT NULL,
    account_id uuid,
    owner_name character varying(255) NOT NULL,
    icon_cod integer NOT NULL,
    seen_medias_ids jsonb NOT NULL,
    preferences jsonb NOT NULL
);

----------------------------- PERMISSION ------------------------------------
CREATE TABLE IF NOT EXISTS public.permission (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    description character varying(255) NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,

    CONSTRAINT permission_pkey PRIMARY KEY (id),
    CONSTRAINT permission_name_unique UNIQUE (name),
    CONSTRAINT permission_description_unique UNIQUE (description)
);
ALTER TABLE IF EXISTS public.permission
    OWNER to postgres;
CREATE INDEX IF NOT EXISTS ix_permission_created_at
    ON public.permission USING btree
    (created_at ASC NULLS LAST)
    TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS public.permission_aud (
    rev integer NOT NULL,
    revtype smallint,
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    description character varying(255) NOT NULL
);

----------------------------- ROLE ------------------------------------
CREATE TABLE IF NOT EXISTS public.role (
    id smallint NOT NULL,
    name character varying(255) NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,

    CONSTRAINT role_pkey PRIMARY KEY (id),
    CONSTRAINT role_id_check CHECK (id >= 1 AND id <= 3),
    CONSTRAINT role_name_unique UNIQUE (name)
);
ALTER TABLE IF EXISTS public.role
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.role_aud (
    rev integer NOT NULL,
    revtype smallint,
    id smallint NOT NULL,
    name character varying(255) NOT NULL
);

----------------------------- ROLE / PERMISSION (many to many relation table) ------------------------------------
CREATE TABLE IF NOT EXISTS public.role_and_permission (
    role_id smallint NOT NULL,
    permission_id bigint NOT NULL,

    CONSTRAINT role_and_permission_pkey PRIMARY KEY (role_id, permission_id),
    CONSTRAINT role_and_permission__role_id_fk FOREIGN KEY (role_id)
        REFERENCES public.role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT role_and_permission__permission_id_fk FOREIGN KEY (permission_id)
        REFERENCES public.permission (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
ALTER TABLE IF EXISTS public.role_and_permissio
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.role_aud (
    rev integer NOT NULL,
    revtype smallint,
    role_id smallint NOT NULL,
    permission_id bigint NOT NULL
);
    
----------------------------- USER ACTIVITY ------------------------------------
CREATE SEQUENCE IF NOT EXISTS user_activity_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.user_activity (
    id bigint NOT NULL DEFAULT nextval('user_activity_id_seq'),
    user_id uuid NOT NULL,
    profile_id uuid,
    ip character varying(255) NOT NULL,
    permission_name character varying(255) NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,

    CONSTRAINT user_activity_pkey PRIMARY KEY (id),
    CONSTRAINT user_activity_user_id_fk FOREIGN KEY (user_id)
        REFERENCES public.user_info (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_activity_profile_id_fk FOREIGN KEY (profile_id)
        REFERENCES public.profile (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_activity_user_profile_permission_name_created_at_unique UNIQUE (user_id, profile_id, permission_name, created_at)
);
ALTER TABLE IF EXISTS public.user_activity
    OWNER to postgres;
CREATE INDEX IF NOT EXISTS ix_user_activity_created_at
    ON public.user_activity USING btree
    (created_at ASC NULLS LAST)
    TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS public.user_activity_aud (
    rev integer NOT NULL,
    revtype smallint,
    id bigint NOT NULL,
    user_id uuid NOT NULL,
    profile_id uuid,
    ip character varying(255) NOT NULL,
    permission_name character varying(255) NOT NULL
);

----------------------------- REV INFO ------------------------------------
CREATE TABLE IF NOT EXISTS public.revinfo (
    rev INTEGER NOT NULL,
    revtstmp bigint,
    CONSTRAINT revinfo_pkey PRIMARY KEY (rev)
)