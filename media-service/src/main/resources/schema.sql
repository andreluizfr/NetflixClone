----------------------------- MEDIA ------------------------------------

CREATE TABLE IF NOT EXISTS public.media (
    media_id bigint NOT NULL DEFAULT nextval('media_media_id_seq'::regclass),
    title character varying(255) NOT NULL,
    is_animation boolean NOT NULL,
    genres jsonb NOT NULL,
    director character varying(255) NOT NULL,
    release_year integer NOT NULL,
    descriptions text COLLATE NOT NULL,
    age_rating integer NOT NULL,
    thumbnail_url text COLLATE NOT NULL,
    poster_url text COLLATE NOT NULL,
    trailer_url text COLLATE NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    CONSTRAINT media_pkey PRIMARY KEY (media_id),
    CONSTRAINT media_title_is_animation_release_year_unique UNIQUE (title, is_animation, release_year)
);
ALTER TABLE IF EXISTS public.media
    OWNER to postgres;
CREATE INDEX IF NOT EXISTS ix_media_created_at
    ON public.media USING btree
    (created_at ASC NULLS LAST)
    TABLESPACE pg_default;

----------------------------- MOVIE ------------------------------------

CREATE TABLE IF NOT EXISTS public.movie
(
    is_movie_series boolean NOT NULL,
    sequence_number integer NOT NULL,
    media_id bigint NOT NULL,
    episode_id uuid,
    actors_actresses jsonb NOT NULL,
    CONSTRAINT movie_pkey PRIMARY KEY (media_id),
    CONSTRAINT movie_episode_id_key UNIQUE (episode_id),
    CONSTRAINT fkjfwp4ur33cfl5bqbkpu2wwdud FOREIGN KEY (episode_id)
        REFERENCES public.episode (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkr3irhd1rplc68prp3rdcx5mg1 FOREIGN KEY (media_id)
        REFERENCES public.media (media_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.movie
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS public.anime
(
    number_of_seasons integer NOT NULL,
    season_number integer NOT NULL,
    media_id bigint NOT NULL,
    studio character varying(255) COLLATE pg_catalog."default" NOT NULL,
    voice_actors_actresses jsonb NOT NULL,
    CONSTRAINT anime_pkey PRIMARY KEY (media_id),
    CONSTRAINT fk9mv6wmv3sy8cu23g5jqgyrusp FOREIGN KEY (media_id)
        REFERENCES public.media (media_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.anime
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS public.tv_show
(
    number_of_seasons integer NOT NULL,
    season_number integer NOT NULL,
    media_id bigint NOT NULL,
    actors_actresses jsonb NOT NULL,
    CONSTRAINT tv_show_pkey PRIMARY KEY (media_id),
    CONSTRAINT fk3kfr1dsiolbgvwfhndd9p9j2m FOREIGN KEY (media_id)
        REFERENCES public.media (media_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tv_show
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.preview_media
(
    created_at timestamp(6) without time zone NOT NULL,
    id bigint NOT NULL DEFAULT nextval('preview_media_id_seq'::regclass),
    media_id bigint,
    updated_at timestamp(6) without time zone NOT NULL,
    CONSTRAINT preview_media_pkey PRIMARY KEY (id),
    CONSTRAINT preview_media_media_id_key UNIQUE (media_id),
    CONSTRAINT fkqrjbxv8jae7mi3klj6i2fihjo FOREIGN KEY (media_id)
        REFERENCES public.media (media_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.preview_media
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS public.preview_media
(
    created_at timestamp(6) without time zone NOT NULL,
    id bigint NOT NULL DEFAULT nextval('preview_media_id_seq'::regclass),
    media_id bigint,
    updated_at timestamp(6) without time zone NOT NULL,
    CONSTRAINT preview_media_pkey PRIMARY KEY (id),
    CONSTRAINT preview_media_media_id_key UNIQUE (media_id),
    CONSTRAINT fkqrjbxv8jae7mi3klj6i2fihjo FOREIGN KEY (media_id)
        REFERENCES public.media (media_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.preview_media
    OWNER to postgres;



CREATE TABLE IF NOT EXISTS public.media_list_and_media
(
    media_id bigint NOT NULL,
    media_list_id uuid NOT NULL,
    CONSTRAINT media_list_and_media_pkey PRIMARY KEY (media_id, media_list_id),
    CONSTRAINT fk36lvnuu1kkywcwpypsco32cbn FOREIGN KEY (media_list_id)
        REFERENCES public.media_list (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkpq7lu0ksopvy9d00e0kspmf4k FOREIGN KEY (media_id)
        REFERENCES public.media (media_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.media_list_and_media
    OWNER to postgres;


----------------------------- EPISODE ------------------------------------

CREATE TABLE IF NOT EXISTS public.episode (
    id uuid NOT NULL,
    tv_show_id bigint,
    anime_id bigint,
    title character varying(255),
    thumbnail_url text NOT NULL,
    episode_url text NOT NULL,
    duration integer NOT NULL,
    ordem integer,
    created_at timestamp(6) without time zone NOT NULL,
    updated_at timestamp(6) without time zone NOT NULL,
    CONSTRAINT episode_pkey PRIMARY KEY (id),
    CONSTRAINT episode_tv_show_id_fk FOREIGN KEY (tv_show_id)
        REFERENCES public.tv_show (media_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT episode_anime_id_fk FOREIGN KEY (anime_id)
        REFERENCES public.anime (media_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT episode_episode_url_unique UNIQUE (episode_url)
)
ALTER TABLE IF EXISTS public.episode
    OWNER to postgres;
CREATE INDEX IF NOT EXISTS ix_episode_created_at
    ON public.episode USING btree
    (created_at ASC NULLS LAST)
    TABLESPACE pg_default;