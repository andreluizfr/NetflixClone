-- filmes
-- 6 = romance
INSERT INTO media (media_id, title, is_animation, genres, director, release_year, description, age_rating, thumbnail_url, poster_url, trailer_url, created_at, updated_at)
VALUES (1, 'After - Depois da Promessa', FALSE, '[6]', 'Castille Landon', 2022, 'Enquanto Hardin confronta seu passado e Tessa sofre uma tragédia, os dois devem decidir se continuam seu relacionamento apaixonado, mas tóxico, ou fazem mudanças dramáticas em suas vidas.', 16, 'https://cinebuzz.uol.com.br/media/_versions/after_5_ja_foi_gravado_e_tem_titulo_oficial_revelado_saiba_qual_widelg.jpg', 'https://www.arrobanerd.com.br/wp-content/uploads/2022/08/after-depois-da-promessa-cri%CC%81tica.jpg', 'https://www.youtube.com/watch?v=us7zUFtoc9Q', '2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328');
INSERT INTO movie (media_id, movie_series_flag, sequence_number, actors_actresses)
VALUES (1, TRUE, 4, '["Josephine Langford", "Hero Fiennes-Tiffin"]');

-- comedy = 0, mystery = 4
INSERT INTO media (media_id, title, is_animation, genres, director, release_year, description, age_rating, thumbnail_url, poster_url, trailer_url, created_at, updated_at)
VALUES (2, 'Mistério em Paris', FALSE, '[0, 4]', 'Jeremy Garelick', 2023, 'Nick e Audrey Spitz abrem uma agência de investigação e finalmente conseguem um caso importante: um amigo bilionário é sequestrado no dia de seu casamento.', 14, 'https://p2.trrsf.com/image/fget/cf/774/0/images.terra.com/2023/03/31/1844513309-77cce32034f8e41609274fff44185dfd.jpg', 'https://www.kakuchopurei.com/wp-content/uploads/2023/03/MURDER-MYSTERY-2-_-Key-Art-1.jpg', 'https://www.youtube.com/watch?v=FDcsfr7bsE8', '2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328');
INSERT INTO movie (media_id, movie_series_flag, sequence_number, actors_actresses)
VALUES (2, FALSE, 1, '["Adam Sandler", "Allen Covert", "Tripp Vinson", "Jennifer Aniston", "James Vanderbilt", "James D. Stern"]');

-- action = 1, thriller = 2
INSERT INTO media (media_id, title, is_animation, genres, director, release_year, description, age_rating, thumbnail_url, poster_url, trailer_url, created_at, updated_at)
VALUES (3, 'Resgate 2', FALSE, '[1, 2]', 'Sam Hargrave', 2023, 'Depois de escapar da morte por um triz, o mercenário Tyler Rake encara mais uma missão perigosa: resgatar a família de um criminoso implacável.', 16, 'https://s2-techtudo.glbimg.com/PyUKS3XAYVrUpD9jGC5443dpz_k=/0x0:1045x551/924x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_08fbf48bc0524877943fe86e43087e7a/internal_photos/bs/2023/X/a/ogKNjNSmiC2H2o9duV1w/aaaaqwqo-fc4mwa5e-63h4jpgu8zfwvbaajgfok-odqaljok9nscre8bf20cpdsd-x8dizihmtxlyrxgmf-oh8ukdg0jwiyyjstd7qlq7tkpw-bmyqhmlahsg-7ayaoj3h5iult9psj4vhqbyg-703tj-sua.jpg', 'https://www.ofuxico.com.br/wp-content/uploads/2023/05/chris-hemsworth-poster-resgate-2-netflix.jpg', 'https://www.youtube.com/watch?v=7ZzP1vgk5nA', '2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328');
INSERT INTO movie (media_id, movie_series_flag, sequence_number, actors_actresses)
VALUES (3, TRUE, 2, '[]');

-- tracks para os filmes
INSERT INTO episode_track (id, media_id, title, duration, n_order, season, processing_status, created_at, updated_at)
VALUES ('00000000-0000-0000-0000-000000000000', 1, 'title', 100, 1, 1, 0, '2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328');
INSERT INTO episode_track (id, media_id, title, duration, n_order, season, processing_status, created_at, updated_at)
VALUES ('00000000-0000-0000-0000-000000000001', 2, 'title', 100, 1, 1, 0, '2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328');
INSERT INTO episode_track (id, media_id, title, duration, n_order, season, processing_status, created_at, updated_at)
VALUES ('00000000-0000-0000-0000-000000000002', 3, 'title', 100, 1, 1, 0, '2023-08-02 13:14:21.045328','2023-08-02 13:14:21.045328');







-- animes
-- action = 1, fantasy = 5
INSERT INTO media (media_id, title, is_animation, genres, director, release_year, description, age_rating, thumbnail_url, poster_url, trailer_url, created_at, updated_at)
VALUES (4, 'Demon Slayer', TRUE, '[1, 5]', 'Koyoharu Gotouge', 2019, 'A história conta sobre um jovem que ganha a vida vendendo carvão até descobrir que sua família foi massacrada por um demônio, restando apenas sua irmã, Nezuko, que foi infectada e está se tornando um monstro. O protagonista, então, decide virar um caçador de demônios e buscar vingança.', 16, 'https://animerant.com.br/wp-content/uploads/2023/02/demon-slayer-ordem-3.jpg', 'https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgEP35guHXTOFmNq4Xpug0dNOlU5_lrSDSqXU5YisCpj5iSoDKgYpyo_jJ9b0Gej6Q_uFTSGbXGs8KlQZCcllq5atDR3-7_ycWq8CvSdt7-UDAhb6rIa8CI3IfS3jfSBLhJMvzrKaE-iSYb5FP4vcKCi3m3cfAr5ZzTezTtNFJF8Tru2B1HxRCLrnQ3LQ/s2233/demon-slayer-hinokami-chronicles-capa.jpg', 'https://www.youtube.com/watch?v=VQGCKyvzIM4','2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328');
INSERT INTO anime (media_id, number_of_seasons, season_number, studio, voice_actors_actresses)
VALUES (4, 2, 1, 'Ufotable', '["Takahiro Sakurai", "Natsuki Hanae", "Akari Kitō", "Kana Hanazawa"]');

--retirar depois de teste
INSERT INTO media (media_id, title, is_animation, genres, director, release_year, description, age_rating, thumbnail_url, poster_url, trailer_url, created_at, updated_at)
VALUES (5, 'Demon Slayer', TRUE, '[1, 5]', 'Koyoharu Gotouge', 2019, 'A história conta sobre um jovem que ganha a vida vendendo carvão até descobrir que sua família foi massacrada por um demônio, restando apenas sua irmã, Nezuko, que foi infectada e está se tornando um monstro. O protagonista, então, decide virar um caçador de demônios e buscar vingança.', 16, 'https://animerant.com.br/wp-content/uploads/2023/02/demon-slayer-ordem-3.jpg', 'https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgEP35guHXTOFmNq4Xpug0dNOlU5_lrSDSqXU5YisCpj5iSoDKgYpyo_jJ9b0Gej6Q_uFTSGbXGs8KlQZCcllq5atDR3-7_ycWq8CvSdt7-UDAhb6rIa8CI3IfS3jfSBLhJMvzrKaE-iSYb5FP4vcKCi3m3cfAr5ZzTezTtNFJF8Tru2B1HxRCLrnQ3LQ/s2233/demon-slayer-hinokami-chronicles-capa.jpg', 'https://www.youtube.com/watch?v=VQGCKyvzIM4','2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328');
INSERT INTO anime (media_id, number_of_seasons, season_number, studio, voice_actors_actresses)
VALUES (5, 2, 1, 'Ufotable', '["Takahiro Sakurai", "Natsuki Hanae", "Akari Kitō", "Kana Hanazawa"]');
INSERT INTO media (media_id, title, is_animation, genres, director, release_year, description, age_rating, thumbnail_url, poster_url, trailer_url, created_at, updated_at)
VALUES (6, 'Demon Slayer', TRUE, '[1, 5]', 'Koyoharu Gotouge', 2019, 'A história conta sobre um jovem que ganha a vida vendendo carvão até descobrir que sua família foi massacrada por um demônio, restando apenas sua irmã, Nezuko, que foi infectada e está se tornando um monstro. O protagonista, então, decide virar um caçador de demônios e buscar vingança.', 16, 'https://animerant.com.br/wp-content/uploads/2023/02/demon-slayer-ordem-3.jpg', 'https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgEP35guHXTOFmNq4Xpug0dNOlU5_lrSDSqXU5YisCpj5iSoDKgYpyo_jJ9b0Gej6Q_uFTSGbXGs8KlQZCcllq5atDR3-7_ycWq8CvSdt7-UDAhb6rIa8CI3IfS3jfSBLhJMvzrKaE-iSYb5FP4vcKCi3m3cfAr5ZzTezTtNFJF8Tru2B1HxRCLrnQ3LQ/s2233/demon-slayer-hinokami-chronicles-capa.jpg', 'https://www.youtube.com/watch?v=VQGCKyvzIM4','2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328');
INSERT INTO anime (media_id, number_of_seasons, season_number, studio, voice_actors_actresses)
VALUES (6, 2, 1, 'Ufotable', '["Takahiro Sakurai", "Natsuki Hanae", "Akari Kitō", "Kana Hanazawa"]');
INSERT INTO media (media_id, title, is_animation, genres, director, release_year, description, age_rating, thumbnail_url, poster_url, trailer_url, created_at, updated_at)
VALUES (7, 'Demon Slayer', TRUE, '[1, 5]', 'Koyoharu Gotouge', 2019, 'A história conta sobre um jovem que ganha a vida vendendo carvão até descobrir que sua família foi massacrada por um demônio, restando apenas sua irmã, Nezuko, que foi infectada e está se tornando um monstro. O protagonista, então, decide virar um caçador de demônios e buscar vingança.', 16, 'https://animerant.com.br/wp-content/uploads/2023/02/demon-slayer-ordem-3.jpg', 'https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgEP35guHXTOFmNq4Xpug0dNOlU5_lrSDSqXU5YisCpj5iSoDKgYpyo_jJ9b0Gej6Q_uFTSGbXGs8KlQZCcllq5atDR3-7_ycWq8CvSdt7-UDAhb6rIa8CI3IfS3jfSBLhJMvzrKaE-iSYb5FP4vcKCi3m3cfAr5ZzTezTtNFJF8Tru2B1HxRCLrnQ3LQ/s2233/demon-slayer-hinokami-chronicles-capa.jpg', 'https://www.youtube.com/watch?v=VQGCKyvzIM4','2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328');
INSERT INTO anime (media_id, number_of_seasons, season_number, studio, voice_actors_actresses)
VALUES (7, 2, 1, 'Ufotable', '["Takahiro Sakurai", "Natsuki Hanae", "Akari Kitō", "Kana Hanazawa"]');
INSERT INTO media (media_id, title, is_animation, genres, director, release_year, description, age_rating, thumbnail_url, poster_url, trailer_url, created_at, updated_at)
VALUES (8, 'Demon Slayer', TRUE, '[1, 5]', 'Koyoharu Gotouge', 2019, 'A história conta sobre um jovem que ganha a vida vendendo carvão até descobrir que sua família foi massacrada por um demônio, restando apenas sua irmã, Nezuko, que foi infectada e está se tornando um monstro. O protagonista, então, decide virar um caçador de demônios e buscar vingança.', 16, 'https://animerant.com.br/wp-content/uploads/2023/02/demon-slayer-ordem-3.jpg', 'https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgEP35guHXTOFmNq4Xpug0dNOlU5_lrSDSqXU5YisCpj5iSoDKgYpyo_jJ9b0Gej6Q_uFTSGbXGs8KlQZCcllq5atDR3-7_ycWq8CvSdt7-UDAhb6rIa8CI3IfS3jfSBLhJMvzrKaE-iSYb5FP4vcKCi3m3cfAr5ZzTezTtNFJF8Tru2B1HxRCLrnQ3LQ/s2233/demon-slayer-hinokami-chronicles-capa.jpg', 'https://www.youtube.com/watch?v=VQGCKyvzIM4','2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328');
INSERT INTO anime (media_id, number_of_seasons, season_number, studio, voice_actors_actresses)
VALUES (8, 2, 1, 'Ufotable', '["Takahiro Sakurai", "Natsuki Hanae", "Akari Kitō", "Kana Hanazawa"]');
INSERT INTO media (media_id, title, is_animation, genres, director, release_year, description, age_rating, thumbnail_url, poster_url, trailer_url, created_at, updated_at)
VALUES (9, 'Demon Slayer', TRUE, '[1, 5]', 'Koyoharu Gotouge', 2019, 'A história conta sobre um jovem que ganha a vida vendendo carvão até descobrir que sua família foi massacrada por um demônio, restando apenas sua irmã, Nezuko, que foi infectada e está se tornando um monstro. O protagonista, então, decide virar um caçador de demônios e buscar vingança.', 16, 'https://animerant.com.br/wp-content/uploads/2023/02/demon-slayer-ordem-3.jpg', 'https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgEP35guHXTOFmNq4Xpug0dNOlU5_lrSDSqXU5YisCpj5iSoDKgYpyo_jJ9b0Gej6Q_uFTSGbXGs8KlQZCcllq5atDR3-7_ycWq8CvSdt7-UDAhb6rIa8CI3IfS3jfSBLhJMvzrKaE-iSYb5FP4vcKCi3m3cfAr5ZzTezTtNFJF8Tru2B1HxRCLrnQ3LQ/s2233/demon-slayer-hinokami-chronicles-capa.jpg', 'https://www.youtube.com/watch?v=VQGCKyvzIM4','2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328');
INSERT INTO anime (media_id, number_of_seasons, season_number, studio, voice_actors_actresses)
VALUES (9, 2, 1, 'Ufotable', '["Takahiro Sakurai", "Natsuki Hanae", "Akari Kitō", "Kana Hanazawa"]');
INSERT INTO media (media_id, title, is_animation, genres, director, release_year, description, age_rating, thumbnail_url, poster_url, trailer_url, created_at, updated_at)
VALUES (10, 'Demon Slayer', TRUE, '[1, 5]', 'Koyoharu Gotouge', 2019, 'A história conta sobre um jovem que ganha a vida vendendo carvão até descobrir que sua família foi massacrada por um demônio, restando apenas sua irmã, Nezuko, que foi infectada e está se tornando um monstro. O protagonista, então, decide virar um caçador de demônios e buscar vingança.', 16, 'https://animerant.com.br/wp-content/uploads/2023/02/demon-slayer-ordem-3.jpg', 'https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgEP35guHXTOFmNq4Xpug0dNOlU5_lrSDSqXU5YisCpj5iSoDKgYpyo_jJ9b0Gej6Q_uFTSGbXGs8KlQZCcllq5atDR3-7_ycWq8CvSdt7-UDAhb6rIa8CI3IfS3jfSBLhJMvzrKaE-iSYb5FP4vcKCi3m3cfAr5ZzTezTtNFJF8Tru2B1HxRCLrnQ3LQ/s2233/demon-slayer-hinokami-chronicles-capa.jpg', 'https://www.youtube.com/watch?v=VQGCKyvzIM4','2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328');
INSERT INTO anime (media_id, number_of_seasons, season_number, studio, voice_actors_actresses)
VALUES (10, 2, 1, 'Ufotable', '["Takahiro Sakurai", "Natsuki Hanae", "Akari Kitō", "Kana Hanazawa"]');
INSERT INTO media (media_id, title, is_animation, genres, director, release_year, description, age_rating, thumbnail_url, poster_url, trailer_url, created_at, updated_at)
VALUES (11, 'Demon Slayer', TRUE, '[1, 5]', 'Koyoharu Gotouge', 2019, 'A história conta sobre um jovem que ganha a vida vendendo carvão até descobrir que sua família foi massacrada por um demônio, restando apenas sua irmã, Nezuko, que foi infectada e está se tornando um monstro. O protagonista, então, decide virar um caçador de demônios e buscar vingança.', 16, 'https://animerant.com.br/wp-content/uploads/2023/02/demon-slayer-ordem-3.jpg', 'https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgEP35guHXTOFmNq4Xpug0dNOlU5_lrSDSqXU5YisCpj5iSoDKgYpyo_jJ9b0Gej6Q_uFTSGbXGs8KlQZCcllq5atDR3-7_ycWq8CvSdt7-UDAhb6rIa8CI3IfS3jfSBLhJMvzrKaE-iSYb5FP4vcKCi3m3cfAr5ZzTezTtNFJF8Tru2B1HxRCLrnQ3LQ/s2233/demon-slayer-hinokami-chronicles-capa.jpg', 'https://www.youtube.com/watch?v=VQGCKyvzIM4','2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328');
INSERT INTO anime (media_id, number_of_seasons, season_number, studio, voice_actors_actresses)
VALUES (11, 2, 1, 'Ufotable', '["Takahiro Sakurai", "Natsuki Hanae", "Akari Kitō", "Kana Hanazawa"]');
INSERT INTO media (media_id, title, is_animation, genres, director, release_year, description, age_rating, thumbnail_url, poster_url, trailer_url, created_at, updated_at)
VALUES (12, 'Demon Slayer', TRUE, '[1, 5]', 'Koyoharu Gotouge', 2019, 'A história conta sobre um jovem que ganha a vida vendendo carvão até descobrir que sua família foi massacrada por um demônio, restando apenas sua irmã, Nezuko, que foi infectada e está se tornando um monstro. O protagonista, então, decide virar um caçador de demônios e buscar vingança.', 16, 'https://animerant.com.br/wp-content/uploads/2023/02/demon-slayer-ordem-3.jpg', 'https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgEP35guHXTOFmNq4Xpug0dNOlU5_lrSDSqXU5YisCpj5iSoDKgYpyo_jJ9b0Gej6Q_uFTSGbXGs8KlQZCcllq5atDR3-7_ycWq8CvSdt7-UDAhb6rIa8CI3IfS3jfSBLhJMvzrKaE-iSYb5FP4vcKCi3m3cfAr5ZzTezTtNFJF8Tru2B1HxRCLrnQ3LQ/s2233/demon-slayer-hinokami-chronicles-capa.jpg', 'https://www.youtube.com/watch?v=VQGCKyvzIM4','2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328');
INSERT INTO anime (media_id, number_of_seasons, season_number, studio, voice_actors_actresses)
VALUES (12, 2, 1, 'Ufotable', '["Takahiro Sakurai", "Natsuki Hanae", "Akari Kitō", "Kana Hanazawa"]');

-- episodios para anime
INSERT INTO episode (id, media_id, title, thumbnail_url, episode_url, duration, order_, created_at, updated_at)
VALUES ('11111111-1111-1111-1111-111111111111', 4, 'Crueldade', 'https://occ-0-3936-3851.1.nflxso.net/dnm/api/v6/9pS1daC2n6UGc3dUogvWIPMR_OU/AAAABbIG3xyrM5-FAxBYRTHz0HckVeMGheZteiWRjFXN82hawuGEkvTGogYVmTeiIHS7bYEfSzP-YRMABt-BEwSfLSH7zBJWy2KnKSWHZrkpgV01QC4l-PAQX4Rk.jpg?r=0ec', 'https://www.youtube.com/watch?v=VQGCKyvzIM4', 24, 1, '2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328');
INSERT INTO episode (id, media_id, title, thumbnail_url, episode_url, duration, order_, created_at, updated_at)
VALUES ('22222222-2222-2222-2222-222222222222', 4, 'Sakonji Urokodaki, o treinador', 'https://occ-0-3936-3851.1.nflxso.net/dnm/api/v6/9pS1daC2n6UGc3dUogvWIPMR_OU/AAAABSi7WTW1BKzTg_-XuOdfdxxbyDsQYNWl4a8F-tIy05zcXDMNF-z7vHBRhEamJkM8c7BHpPxcA8DzWnTOboe08SuT6J9aVdgV2TdIejHHsmWtBvVjDG_s0wNU.jpg?r=584', 'https://www.youtube.com/watch?v=VQGCKyvzIM', 24, 2, '2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328');
INSERT INTO episode (id, media_id, title, thumbnail_url, episode_url, duration, order_, created_at, updated_at)
VALUES ('33333333-3333-3333-3333-333333333333', 4, 'Sabito e Makomo', 'https://occ-0-3936-3851.1.nflxso.net/dnm/api/v6/9pS1daC2n6UGc3dUogvWIPMR_OU/AAAABWzDzcspEWJlZSQltan5qtG3BGlMv54cV7gBiPzSAjikL1O-LWIy-5lp_IMR3R4iYSwTZWtragcztGd-ARMdOodZWPOgjmpmyqRBBATobQy2CELcRfIXN6up.jpg?r=295', 'https://www.youtube.com/watch?v=VQGCKyvzI', 24, 3, '2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328');
INSERT INTO episode (id, media_id, title, thumbnail_url, episode_url, duration, order_, created_at, updated_at)
VALUES ('44444444-4444-4444-4444-444444444444', 4, 'Seleção final', 'https://occ-0-3936-3851.1.nflxso.net/dnm/api/v6/9pS1daC2n6UGc3dUogvWIPMR_OU/AAAABdGByJ3U6edMdUtCGfshJGM390ZrDGsYEmBcE4o_b1iLOZFS5D863_rSV27oM_A9GdYeU_gSbW07R_Y_uIyc6672IwkKhnUFelhLWd7rplt1NuhtfQZvpO4Y.jpg?r=5b6', 'https://www.youtube.com/watch?v=VQGCKyvz', 24, 4, '2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328');
INSERT INTO episode (id, media_id, title, thumbnail_url, episode_url, duration, order_, created_at, updated_at)
VALUES ('55555555-5555-5555-5555-555555555555', 4, 'Meu próprio aço', 'https://occ-0-3936-3851.1.nflxso.net/dnm/api/v6/9pS1daC2n6UGc3dUogvWIPMR_OU/AAAABTuCttRHtc_SATCV5Ttb9fRKEGi_G9-MAvbHv-trwk65uwbTebp4dPr_vlznB1WmlLRSXesIULexNZMnW3ewrRkNXgTq1MINIJcUQWNSr8FgZRkOICusxWay.jpg?r=6d0', 'https://www.youtube.com/watch?v=VQGCKyv', 24, 5, '2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328');
INSERT INTO episode (id, media_id, title, thumbnail_url, episode_url, duration, order_, created_at, updated_at)
VALUES ('66666666-6666-6666-6666-666666666666', 4, 'O espadachim que acompanhou um demônio', 'https://occ-0-3936-3851.1.nflxso.net/dnm/api/v6/9pS1daC2n6UGc3dUogvWIPMR_OU/AAAABUjr3zo2fECvIR8VtUaye_ZvHRgLdZ1_YFh4ch1izr12iM7-7HFvM0BZryhxQwxXHe3wHLc03exlwmMaUFnSEsZy7m6GjRbyWeNR5-qzixO9834uPLvEP2Y2.jpg?r=333', 'https://www.youtube.com/watch?v=VQGCKy', 24, 6, '2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328');
INSERT INTO episode (id, media_id, title, thumbnail_url, episode_url, duration, order_, created_at, updated_at)
VALUES ('77777777-7777-7777-7777-777777777777', 4, 'Muzan Kibutsuji', 'https://occ-0-3936-3851.1.nflxso.net/dnm/api/v6/9pS1daC2n6UGc3dUogvWIPMR_OU/AAAABbFKMspQ03NmBCbvflwaxzTYOGTd7ontmfY1sS5of1HSf16c1V-U-qr4hrJBZSpGOtudbsbdKbfaLXjR21O0JFwtTy2h3orE0mHMS4MSnN_konc29FB6DL8w.jpg?r=9d7', 'https://www.youtube.com/watch?v=VQGCK', 24, 7, '2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328');
INSERT INTO episode (id, media_id, title, thumbnail_url, episode_url, duration, order_, created_at, updated_at)
VALUES ('88888888-8888-8888-8888-888888888888', 4, 'O aroma do sangue encantado', 'https://occ-0-3936-3851.1.nflxso.net/dnm/api/v6/9pS1daC2n6UGc3dUogvWIPMR_OU/AAAABdU6p-T5SvHXOWeXtnr7SHf-RbB7OxttXoQXCHwwtodWwrTtlHHfUhRu14B_pM-6J0-jw0oHRqi4QKpeYBJz-GkjEVOjPnjuQHkfS6cBTdt7ywKrSpQwdAXd.jpg?r=df0', 'https://www.youtube.com/watch?v=VQGC', 24, 8, '2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328');









--Criando listas
INSERT INTO media_list (id, title, created_at, updated_at)
VALUES('5ced3b53-8d57-447d-aa69-fd0a55ab85d8', 'teste 1', '2023-08-02 13:14:22.045328','2023-08-02 13:14:22.045328');
INSERT INTO media_list (id, title, created_at, updated_at)
VALUES('0316dfc4-912d-4cda-9018-419bb3045cd1', 'teste 2', '2023-08-02 13:14:23.045328', '2023-08-02 13:14:23.045328');
INSERT INTO media_list (id, title, created_at, updated_at)
VALUES('ac3cdec1-3994-4ee6-8b15-0b3b72793695', 'teste 3', '2023-08-02 13:14:24.045328', '2023-08-02 13:14:24.045328');
INSERT INTO media_list (id, title, created_at, updated_at)
VALUES('6d3bc137-ce46-43ac-9460-287d096192df', 'teste 4', '2023-08-02 13:14:25.045328', '2023-08-02 13:14:25.045328');
INSERT INTO media_list (id, title, created_at, updated_at)
VALUES('53c24404-5136-4568-84a8-1a0f543f177b', 'teste 5', '2023-08-02 13:14:26.045328', '2023-08-02 13:14:26.045328');

-- lista 1
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('5ced3b53-8d57-447d-aa69-fd0a55ab85d8', 1);
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('5ced3b53-8d57-447d-aa69-fd0a55ab85d8', 2);
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('5ced3b53-8d57-447d-aa69-fd0a55ab85d8', 3);
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('5ced3b53-8d57-447d-aa69-fd0a55ab85d8', 4);
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('5ced3b53-8d57-447d-aa69-fd0a55ab85d8', 5);
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('5ced3b53-8d57-447d-aa69-fd0a55ab85d8', 6);
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('5ced3b53-8d57-447d-aa69-fd0a55ab85d8', 7);
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('5ced3b53-8d57-447d-aa69-fd0a55ab85d8', 8);

-- lista 2
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('0316dfc4-912d-4cda-9018-419bb3045cd1', 1);
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('0316dfc4-912d-4cda-9018-419bb3045cd1', 2);
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('0316dfc4-912d-4cda-9018-419bb3045cd1', 3);
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('0316dfc4-912d-4cda-9018-419bb3045cd1', 4);
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('0316dfc4-912d-4cda-9018-419bb3045cd1', 5);
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('0316dfc4-912d-4cda-9018-419bb3045cd1', 6);
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('0316dfc4-912d-4cda-9018-419bb3045cd1', 7);
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('0316dfc4-912d-4cda-9018-419bb3045cd1', 8);
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('0316dfc4-912d-4cda-9018-419bb3045cd1', 9);
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('0316dfc4-912d-4cda-9018-419bb3045cd1', 10);
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('0316dfc4-912d-4cda-9018-419bb3045cd1', 11);
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('0316dfc4-912d-4cda-9018-419bb3045cd1', 12);

-- lista 3
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('ac3cdec1-3994-4ee6-8b15-0b3b72793695', 1);
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('ac3cdec1-3994-4ee6-8b15-0b3b72793695', 2);
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('ac3cdec1-3994-4ee6-8b15-0b3b72793695', 3);
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('ac3cdec1-3994-4ee6-8b15-0b3b72793695', 4);

-- lista 4 
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('6d3bc137-ce46-43ac-9460-287d096192df', 1);
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('6d3bc137-ce46-43ac-9460-287d096192df', 2);
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('6d3bc137-ce46-43ac-9460-287d096192df', 3);
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('6d3bc137-ce46-43ac-9460-287d096192df', 4);

-- lista 5
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('53c24404-5136-4568-84a8-1a0f543f177b', 1);
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('53c24404-5136-4568-84a8-1a0f543f177b', 2);
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('53c24404-5136-4568-84a8-1a0f543f177b', 3);
INSERT INTO media_list_and_media (media_list_id, media_id)
VALUES('53c24404-5136-4568-84a8-1a0f543f177b', 4);









-- preview media history
INSERT INTO preview_media (id, media_id, created_at, updated_at)
VALUES (1, 3, '2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328');

INSERT INTO preview_media (id, media_id, created_at, updated_at)
VALUES (2, 4, '2023-10-08 13:14:21.045328', '2023-10-08 13:14:21.045328');