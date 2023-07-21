-- filmes
INSERT INTO movie (id, title, is_animation, genres, director, release_year, descriptions, age_rating, thumbnail_url, poster_url, trailer_url, is_movie_series, sequence_number, actors_actresses, episode_id)
VALUES (1, 'After - Depois da Promessa', FALSE, '{ROMANCE}', 'Castille Landon', 2022, 'Enquanto Hardin confronta seu passado e Tessa sofre uma tragédia, os dois devem decidir se continuam seu relacionamento apaixonado, mas tóxico, ou fazem mudanças dramáticas em suas vidas.', 16, 'https://www.claquete.com.br/fotos/filmes/poster/14124_medio.jpg', 'https://cdn.fstatic.com/media/movies/covers/2022/02/305140_background.jpg', 'https://www.youtube.com/watch?v=us7zUFtoc9Q', TRUE, 4, '{Josephine Langford, Hero Fiennes-Tiffin}', '00000000-0000-0000-0000-000000000000');
INSERT INTO movie (id, title, is_animation, genres, director, release_year, descriptions, age_rating, thumbnail_url, poster_url, trailer_url, is_movie_series, sequence_number, actors_actresses, episode_id)
VALUES (2, 'Mistério em Paris', FALSE, '{COMEDY, MYSTERY}', 'Jeremy Garelick', 2023, 'Nick e Audrey Spitz abrem uma agência de investigação e finalmente conseguem um caso importante: um amigo bilionário é sequestrado no dia de seu casamento.', 14, 'https://p2.trrsf.com/image/fget/cf/774/0/images.terra.com/2023/03/31/1844513309-77cce32034f8e41609274fff44185dfd.jpg', 'https://www.kakuchopurei.com/wp-content/uploads/2023/03/MURDER-MYSTERY-2-_-Key-Art-1.jpg', 'https://www.youtube.com/watch?v=FDcsfr7bsE8', FALSE, 1, '{Adam Sandler, Allen Covert, Tripp Vinson, Jennifer Aniston, James Vanderbilt, James D. Stern}', '00000000-0000-0000-0000-000000000001');
INSERT INTO movie (id, title, is_animation, genres, director, release_year, descriptions, age_rating, thumbnail_url, poster_url, trailer_url, is_movie_series, sequence_number, actors_actresses, episode_id)
VALUES (3, 'Resgate 2', FALSE, '{ACTION, THRILLER}', 'Sam Hargrave', 2023, 'Depois de escapar da morte por um triz, o mercenário Tyler Rake encara mais uma missão perigosa: resgatar a família de um criminoso implacável.', 16, 'https://s2-techtudo.glbimg.com/PyUKS3XAYVrUpD9jGC5443dpz_k=/0x0:1045x551/924x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_08fbf48bc0524877943fe86e43087e7a/internal_photos/bs/2023/X/a/ogKNjNSmiC2H2o9duV1w/aaaaqwqo-fc4mwa5e-63h4jpgu8zfwvbaajgfok-odqaljok9nscre8bf20cpdsd-x8dizihmtxlyrxgmf-oh8ukdg0jwiyyjstd7qlq7tkpw-bmyqhmlahsg-7ayaoj3h5iult9psj4vhqbyg-703tj-sua.jpg', 'https://www.ofuxico.com.br/wp-content/uploads/2023/05/chris-hemsworth-poster-resgate-2-netflix.jpg', 'https://www.youtube.com/watch?v=7ZzP1vgk5nA', TRUE, 2, '{}', '00000000-0000-0000-0000-000000000002');
-- episodios para os filmes
INSERT INTO episode (id, media_id, episode_url, duration, "order")
VALUES ('00000000-0000-0000-0000-000000000000', 1, 'https://www.youtube.com/watch?v=us7zUFtoc9Q', 100);
INSERT INTO episode (id, media_id, episode_url, duration, "order")
VALUES ('00000000-0000-0000-0000-000000000001', 2, 'https://www.youtube.com/watch?v=FDcsfr7bsE8', 100);
INSERT INTO episode (id, media_id, episode_url, duration, "order")
VALUES ('00000000-0000-0000-0000-000000000002', 3, 'https://www.youtube.com/watch?v=7ZzP1vgk5nA', 100);

-- anime
INSERT INTO anime (id, title, is_animation, genres, director, release_year, descriptions, age_rating, thumbnail_url, poster_url, trailer_url, number_of_seasons, season_number, studio, voice_actors_actresses, episodes)
VALUES (4, 'Demon Slayer', TRUE, '{ACTION, THRILLER}', 'Koyoharu Gotouge', 2019, 'A história conta sobre um jovem que ganha a vida vendendo carvão até descobrir que sua família foi massacrada por um demônio, restando apenas sua irmã, Nezuko, que foi infectada e está se tornando um monstro. O protagonista, então, decide virar um caçador de demônios e buscar vingança.', 16, 'https://...', 'https://...', 'https://...', 2, 1, 'Ufotable', '{Takahiro Sakurai, Natsuki Hanae, Akari Kitō, Kana Hanazawa}', '{11111111-1111-1111-1111-111111111111, 22222222-2222-2222-2222-222222222222, 33333333-3333-3333-3333-333333333333, 44444444-4444-4444-4444-444444444444, 55555555-5555-5555-5555-555555555555, 66666666-6666-6666-6666-666666666666, 77777777-7777-7777-7777-777777777777, 88888888-8888-8888-8888-888888888888}');
-- episodios para anime
INSERT INTO episode (id, media_id, title, thumbnail_url, episode_url, duration, "order")
VALUES ('11111111-1111-1111-1111-111111111111', 4, 'Crueldade', 'https://occ-0-3936-3851.1.nflxso.net/dnm/api/v6/9pS1daC2n6UGc3dUogvWIPMR_OU/AAAABbIG3xyrM5-FAxBYRTHz0HckVeMGheZteiWRjFXN82hawuGEkvTGogYVmTeiIHS7bYEfSzP-YRMABt-BEwSfLSH7zBJWy2KnKSWHZrkpgV01QC4l-PAQX4Rk.jpg?r=0ec', 'https://www.youtube.com/watch?v=VQGCKyvzIM4', 24, 1);
INSERT INTO episode (id, media_id, title, thumbnail_url, episode_url, duration, "order")
VALUES ('22222222-2222-2222-2222-222222222222', 4, 'Sakonji Urokodaki, o treinador', 'https://occ-0-3936-3851.1.nflxso.net/dnm/api/v6/9pS1daC2n6UGc3dUogvWIPMR_OU/AAAABSi7WTW1BKzTg_-XuOdfdxxbyDsQYNWl4a8F-tIy05zcXDMNF-z7vHBRhEamJkM8c7BHpPxcA8DzWnTOboe08SuT6J9aVdgV2TdIejHHsmWtBvVjDG_s0wNU.jpg?r=584', 'https://www.youtube.com/watch?v=VQGCKyvzIM4', 24, 2);
INSERT INTO episode (id, media_id, title, thumbnail_url, episode_url, duration, "order")
VALUES ('33333333-3333-3333-3333-333333333333', 4, 'Sabito e Makomo', 'https://occ-0-3936-3851.1.nflxso.net/dnm/api/v6/9pS1daC2n6UGc3dUogvWIPMR_OU/AAAABWzDzcspEWJlZSQltan5qtG3BGlMv54cV7gBiPzSAjikL1O-LWIy-5lp_IMR3R4iYSwTZWtragcztGd-ARMdOodZWPOgjmpmyqRBBATobQy2CELcRfIXN6up.jpg?r=295', 'https://www.youtube.com/watch?v=VQGCKyvzIM4', 24, 3);
INSERT INTO episode (id, media_id, title, thumbnail_url, episode_url, duration, "order")
VALUES ('44444444-4444-4444-4444-444444444444', 4, 'Seleção final', 'https://occ-0-3936-3851.1.nflxso.net/dnm/api/v6/9pS1daC2n6UGc3dUogvWIPMR_OU/AAAABdGByJ3U6edMdUtCGfshJGM390ZrDGsYEmBcE4o_b1iLOZFS5D863_rSV27oM_A9GdYeU_gSbW07R_Y_uIyc6672IwkKhnUFelhLWd7rplt1NuhtfQZvpO4Y.jpg?r=5b6', 'https://www.youtube.com/watch?v=VQGCKyvzIM4', 24, 4);
INSERT INTO episode (id, media_id, title, thumbnail_url, episode_url, duration, "order")
VALUES ('55555555-5555-5555-5555-555555555555', 4, 'Meu próprio aço', 'https://occ-0-3936-3851.1.nflxso.net/dnm/api/v6/9pS1daC2n6UGc3dUogvWIPMR_OU/AAAABTuCttRHtc_SATCV5Ttb9fRKEGi_G9-MAvbHv-trwk65uwbTebp4dPr_vlznB1WmlLRSXesIULexNZMnW3ewrRkNXgTq1MINIJcUQWNSr8FgZRkOICusxWay.jpg?r=6d0', 'https://www.youtube.com/watch?v=VQGCKyvzIM4', 24, 5);
INSERT INTO episode (id, media_id, title, thumbnail_url, episode_url, duration, "order")
VALUES ('66666666-6666-6666-6666-666666666666', 4, 'O espadachim que acompanhou um demônio', 'https://occ-0-3936-3851.1.nflxso.net/dnm/api/v6/9pS1daC2n6UGc3dUogvWIPMR_OU/AAAABUjr3zo2fECvIR8VtUaye_ZvHRgLdZ1_YFh4ch1izr12iM7-7HFvM0BZryhxQwxXHe3wHLc03exlwmMaUFnSEsZy7m6GjRbyWeNR5-qzixO9834uPLvEP2Y2.jpg?r=333', 'https://www.youtube.com/watch?v=VQGCKyvzIM4', 24, 6);
INSERT INTO episode (id, media_id, title, thumbnail_url, episode_url, duration, "order")
VALUES ('77777777-7777-7777-7777-777777777777', 4, 'Muzan Kibutsuji', 'https://occ-0-3936-3851.1.nflxso.net/dnm/api/v6/9pS1daC2n6UGc3dUogvWIPMR_OU/AAAABbFKMspQ03NmBCbvflwaxzTYOGTd7ontmfY1sS5of1HSf16c1V-U-qr4hrJBZSpGOtudbsbdKbfaLXjR21O0JFwtTy2h3orE0mHMS4MSnN_konc29FB6DL8w.jpg?r=9d7', 'https://www.youtube.com/watch?v=VQGCKyvzIM4', 24, 7);
INSERT INTO episode (id, media_id, title, thumbnail_url, episode_url, duration, "order")
VALUES ('88888888-8888-8888-8888-888888888888', 4, 'O aroma do sangue encantado', 'https://occ-0-3936-3851.1.nflxso.net/dnm/api/v6/9pS1daC2n6UGc3dUogvWIPMR_OU/AAAABdU6p-T5SvHXOWeXtnr7SHf-RbB7OxttXoQXCHwwtodWwrTtlHHfUhRu14B_pM-6J0-jw0oHRqi4QKpeYBJz-GkjEVOjPnjuQHkfS6cBTdt7ywKrSpQwdAXd.jpg?r=df0', 'https://www.youtube.com/watch?v=VQGCKyvzIM4', 24, 8);





