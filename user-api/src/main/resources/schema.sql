DROP TABLE IF EXISTS public.user_activity;
DROP TABLE IF EXISTS public.user_info;
DROP TABLE IF EXISTS public.profile;
DROP TABLE IF EXISTS public.account;
DROP TABLE IF EXISTS public.role_and_permission;
DROP TABLE IF EXISTS public.permission;
DROP TABLE IF EXISTS public.role;

DROP INDEX IF EXISTS ix_permission_created_at;
DROP INDEX IF EXISTS ix_user_activity_created_at;


----------------------------- ACCOUNT ------------------------------------

CREATE TABLE public.account (
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

----------------------------- USER INFO ------------------------------------

CREATE TABLE public.user_info (
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

----------------------------- PROFILE ------------------------------------

CREATE TABLE public.profile (
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

----------------------------- PERMISSION ------------------------------------

CREATE TABLE public.permission (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    description character varying(255) NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,

    CONSTRAINT permission_pkey PRIMARY KEY (id),
    CONSTRAINT permission_name_description_unique UNIQUE (name, description)
);
ALTER TABLE IF EXISTS public.permission
    OWNER to postgres;
CREATE INDEX ix_permission_created_at
    ON public.permission USING btree
    (created_at ASC NULLS LAST)
    TABLESPACE pg_default;

----------------------------- ROLE ------------------------------------

CREATE TABLE public.role (
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

----------------------------- ROLE / PERMISSION (many to many relation table) ------------------------------------

CREATE TABLE public.role_and_permission (
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
    
----------------------------- USER ACTIVITY ------------------------------------

CREATE TABLE public.user_activity (
    id bigint NOT NULL,
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
CREATE INDEX ix_user_activity_created_at
    ON public.user_activity USING btree
    (created_at ASC NULLS LAST)
    TABLESPACE pg_default;





------------------------------------------ STORED PROCEDURES ---------------------------------------------------------

--CREATE OR REPLACE FUNCTION public.create_partition_or_insert ()
--RETURNS trigger
--LANGUAGE plpgsql
--VOLATILE 
--STRICT
--SECURITY DEFINER
--COST 100
--AS $$
--    DECLARE
--        partition_date TEXT;
--        current_partition TEXT;
--        index_def TEXT;
--    BEGIN
--        partition_date := to_char(NEW.created_at,'YYYY_MM');
--        current_partition := TG_TABLE_NAME || '_' || partition_date;
--        IF NOT EXISTS(SELECT relname FROM pg_class WHERE relname=current_partition) THEN
--            RAISE NOTICE 'A partition has been created %',current_partition;
--            EXECUTE 'CREATE TABLE IF NOT EXISTS ' || current_partition ||  ' (check (to_char(created_at::timestamp::date,''YYYY_MM'') = ''' || to_char(NEW.created_at::timestamp::date,'YYYY_MM') || ''')) INHERITS (' || TG_TABLE_NAME || ');'
--                    ' GRANT ALL PRIVILEGES ON ' || current_partition || ' TO postgres;';
--
--            FOR index_def IN
--                SELECT REPLACE(indexdef, TG_TABLE_NAME, current_partition) FROM pg_indexes WHERE tablename = TG_TABLE_NAME
--            LOOP
--                EXECUTE index_def || ';';
--            END LOOP;
--        END IF;
--        EXECUTE 'INSERT INTO ' || current_partition || ' SELECT(' || TG_TABLE_NAME || ' ' || quote_literal(NEW) || ').* ON CONFLICT DO NOTHING RETURNING id;';
--        RETURN NULL;
--        EXCEPTION
--        WHEN duplicate_table THEN
--            IF EXISTS(SELECT relname FROM pg_class WHERE relname=current_partition) THEN
--                EXECUTE 'INSERT INTO ' || current_partition || ' SELECT(' || TG_TABLE_NAME || ' ' || quote_literal(NEW) || ').* ON CONFLICT DO NOTHING RETURNING id;';
--            END IF;
--        RETURN NULL;
--    END;
--$$;

CREATE OR REPLACE FUNCTION public.create_partition_or_insert ()
RETURNS trigger
LANGUAGE plpgsql
VOLATILE 
STRICT
SECURITY DEFINER
COST 100
AS '
    DECLARE
        partition_date TEXT;
        current_partition TEXT;
        index_def TEXT;
    BEGIN
        partition_date := to_char(NEW.created_at,''YYYY_MM'');
        current_partition := TG_TABLE_NAME || ''_'' || partition_date;
        IF NOT EXISTS(SELECT relname FROM pg_class WHERE relname=current_partition) THEN
            RAISE NOTICE ''A partition has been created %'',current_partition;
            EXECUTE ''CREATE TABLE IF NOT EXISTS '' || current_partition ||  '' (check (to_char(created_at::timestamp::date,''''YYYY_MM'''') = '''' || to_char(NEW.created_at::timestamp::date,''''YYYY_MM'''') || '''')) INHERITS ('' || TG_TABLE_NAME || '');''
                    '' GRANT ALL PRIVILEGES ON '' || current_partition || '' TO postgres;'';

            FOR index_def IN
                SELECT REPLACE(indexdef, TG_TABLE_NAME, current_partition) FROM pg_indexes WHERE tablename = TG_TABLE_NAME
            LOOP
                EXECUTE index_def || '';'';
            END LOOP;
        END IF;
        EXECUTE ''INSERT INTO '' || current_partition || '' SELECT('' || TG_TABLE_NAME || '' '' || quote_literal(NEW) || '').* ON CONFLICT DO NOTHING RETURNING id;'';
        RETURN NULL;
        EXCEPTION
        WHEN duplicate_table THEN
            IF EXISTS(SELECT relname FROM pg_class WHERE relname=current_partition) THEN
                EXECUTE ''INSERT INTO '' || current_partition || '' SELECT('' || TG_TABLE_NAME || '' '' || quote_literal(NEW) || '').* ON CONFLICT DO NOTHING RETURNING id;'';
            END IF;
        RETURN NULL;
    END;
';

CREATE OR REPLACE TRIGGER user_activity_insert
BEFORE INSERT ON public.user_activity
FOR EACH ROW EXECUTE PROCEDURE create_partition_or_insert();

--CREATE OR REPLACE FUNCTION public.user_activity_remove_partitions()
--RETURNS void
--LANGUAGE plpgsql
--COST 100
--VOLATILE NOT LEAKPROOF
--AS $BODY$
--    DECLARE 
--    tabela_pai text := 'user_activity';
--    particao text;
--    particao_vazia boolean;
--    BEGIN
--        FOR particao IN (SELECT tab_filha.relname
--                        FROM pg_inherits AS assoc_heranca
--                        INNER JOIN pg_class AS tab_pai ON tab_pai.oid = assoc_heranca.inhparent
--                        INNER JOIN pg_class AS tab_filha ON tab_filha.oid = assoc_heranca.inhrelid
--                        WHERE tab_pai.relname = tabela_pai
--                        ORDER BY tab_filha.relname ASC)
--        LOOP 
--            particao_vazia := NULL;
--            EXECUTE format('SELECT NOT EXISTS(SELECT 1 FROM %I LIMIT 1);', particao) 
--            INTO particao_vazia;
--            IF (particao_vazia IS true) THEN
--                EXECUTE format('DROP TABLE IF EXISTS %I;', particao);
--                RAISE NOTICE 'Partição removida: %', particao;
--            END IF;
--        END LOOP;
--    END;
--$BODY$;
--GRANT EXECUTE ON FUNCTION public.user_activity_remove_partitions() TO postgres;

CREATE OR REPLACE FUNCTION public.user_activity_remove_partitions()
RETURNS void
LANGUAGE plpgsql
COST 100
VOLATILE NOT LEAKPROOF
AS '
    DECLARE 
    tabela_pai text := ''user_activity'';
    particao text;
    particao_vazia boolean;
    BEGIN
        FOR particao IN (SELECT tab_filha.relname
                        FROM pg_inherits AS assoc_heranca
                        INNER JOIN pg_class AS tab_pai ON tab_pai.oid = assoc_heranca.inhparent
                        INNER JOIN pg_class AS tab_filha ON tab_filha.oid = assoc_heranca.inhrelid
                        WHERE tab_pai.relname = tabela_pai
                        ORDER BY tab_filha.relname ASC)
        LOOP 
            particao_vazia := NULL;
            EXECUTE format(''SELECT NOT EXISTS(SELECT 1 FROM %I LIMIT 1);'', particao) 
            INTO particao_vazia;
            IF (particao_vazia IS true) THEN
                EXECUTE format(''DROP TABLE IF EXISTS %I;'', particao);
                RAISE NOTICE ''Partição removida: %'', particao;
            END IF;
        END LOOP;
    END;
';
GRANT EXECUTE ON FUNCTION public.user_activity_remove_partitions() TO postgres;