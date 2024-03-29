DROP TABLE IF EXISTS public.anime;
DROP TABLE IF EXISTS public.tv_show;
DROP TABLE IF EXISTS public.episode;
DROP TABLE IF EXISTS public.movie;
DROP TABLE IF EXISTS public.preview_media;
DROP TABLE IF EXISTS public.media_list_and_media;
DROP TABLE IF EXISTS public.media_list;
DROP TABLE IF EXISTS public.media;

DROP SEQUENCE IF EXISTS media_media_id_seq;
DROP SEQUENCE IF EXISTS preview_media_id_seq;

DROP INDEX IF EXISTS ix_media_created_at;
DROP INDEX IF EXISTS ix_episode_created_at;

----------------------------- MEDIA ------------------------------------

CREATE SEQUENCE media_media_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

CREATE TABLE public.media (
    media_id bigint NOT NULL DEFAULT nextval('media_media_id_seq'),
    title character varying(255) NOT NULL,
    is_animation boolean NOT NULL,
    genres jsonb NOT NULL,
    director character varying(255) NOT NULL,
    release_year integer NOT NULL,
    description text NOT NULL,
    age_rating integer NOT NULL,
    thumbnail_url text NOT NULL,
    poster_url text NOT NULL,
    trailer_url text NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,

    CONSTRAINT media_pkey PRIMARY KEY (media_id)
);
ALTER TABLE IF EXISTS public.media
    OWNER to postgres;
CREATE INDEX ix_media_created_at
    ON public.media USING btree
    (created_at ASC NULLS LAST)
    TABLESPACE pg_default;

----------------------------- MOVIE ------------------------------------

CREATE TABLE public.movie (
    media_id bigint NOT NULL,
    movie_series_flag boolean NOT NULL,
    sequence_number integer NOT NULL,
    actors_actresses jsonb NOT NULL,

    CONSTRAINT movie_pkey PRIMARY KEY (media_id),
    CONSTRAINT movie_media_id_fk FOREIGN KEY (media_id)
        REFERENCES public.media (media_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
ALTER TABLE IF EXISTS public.movie
    OWNER to postgres;

----------------------------- ANIME ------------------------------------

CREATE TABLE public.anime (
    media_id bigint NOT NULL,
    number_of_seasons integer NOT NULL,
    season_number integer NOT NULL,
    studio character varying(255) NOT NULL,
    voice_actors_actresses jsonb NOT NULL,

    CONSTRAINT anime_pkey PRIMARY KEY (media_id),
    CONSTRAINT anime_media_id_fk FOREIGN KEY (media_id)
        REFERENCES public.media (media_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
ALTER TABLE IF EXISTS public.anime
    OWNER to postgres;

----------------------------- TV SHOW ------------------------------------

CREATE TABLE public.tv_show (
    media_id bigint NOT NULL,
    number_of_seasons integer NOT NULL,
    season_number integer NOT NULL,
    actors_actresses jsonb NOT NULL,

    CONSTRAINT tv_show_pkey PRIMARY KEY (media_id),
    CONSTRAINT tv_show_media_if_kf FOREIGN KEY (media_id)
        REFERENCES public.media (media_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
ALTER TABLE IF EXISTS public.tv_show
    OWNER to postgres;

----------------------------- EPISODE ------------------------------------
CREATE TABLE public.episode (
    id uuid NOT NULL,
    media_id bigint,
    title character varying(255),
    thumbnail_url text NOT NULL,
    episode_url text NOT NULL,
    duration integer NOT NULL,
    order_ integer,
    created_at timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,

    CONSTRAINT episode_pkey PRIMARY KEY (id),
    CONSTRAINT episode_media_id_fk FOREIGN KEY (media_id)
        REFERENCES public.media (media_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT episode_episode_url_unique UNIQUE (episode_url)
);
ALTER TABLE IF EXISTS public.episode
    OWNER to postgres;
CREATE INDEX ix_episode_created_at
    ON public.episode USING btree
    (created_at ASC NULLS LAST)
    TABLESPACE pg_default;

----------------------------- PREVIEW MEDIA ------------------------------------
CREATE SEQUENCE preview_media_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

CREATE TABLE public.preview_media (   
    id bigint NOT NULL DEFAULT nextval('preview_media_id_seq'),
    media_id bigint,
    created_at timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,

    CONSTRAINT preview_media_pkey PRIMARY KEY (id),
    CONSTRAINT preview_media_media_id_fk FOREIGN KEY (media_id)
        REFERENCES public.media (media_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
ALTER TABLE IF EXISTS public.preview_media
    OWNER to postgres;

----------------------------- MEDIA LIST ------------------------------------

CREATE TABLE public.media_list (
    id uuid NOT NULL,
    title character varying(255) NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,

    CONSTRAINT media_list_pkey PRIMARY KEY (id)
);
ALTER TABLE IF EXISTS public.media_list
    OWNER to postgres;

----------------------------- MEDIA LIST / MEDIA (relation table) ------------------------------------

CREATE TABLE public.media_list_and_media (
    media_list_id uuid NOT NULL,
    media_id bigint NOT NULL,

    CONSTRAINT media_list_and_media_pkey PRIMARY KEY (media_id, media_list_id),
    CONSTRAINT media_list_and_media__media_list_id_fk FOREIGN KEY (media_list_id)
        REFERENCES public.media_list (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT media_list_and_media__media_id_fk FOREIGN KEY (media_id)
        REFERENCES public.media (media_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
ALTER TABLE IF EXISTS public.media_list_and_media
    OWNER to postgres;