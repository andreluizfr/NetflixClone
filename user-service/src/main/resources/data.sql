-- criando usuários e informações
-- basic = 0, admin = 1
INSERT INTO account (id, active_flag, current_plan, day_plan_expire_date, payment_history, limit_of_profiles, created_at, updated_at)
VALUES ('48c3684d-3093-4e99-8ac6-8dbc6a902cce', TRUE, 1, 22, '[]', 3, '2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328')
ON DUPLICATE KEY UPDATE id = id;
INSERT INTO user_info (id, email, password, birth_date, role, account_id, created_at, updated_at) --teste123
VALUES ('561718d0-1c7c-4251-97c0-946f1048243b', 'teste@teste.com', '$2a$12$Ju2OKxxKmeE2A36X885fR.DUuQutAQmvLOcZgo6Xh7fGCJwyDN8LW', '2004-10-19', 0, '48c3684d-3093-4e99-8ac6-8dbc6a902cce', '2004-10-19T10:24:01-03:00','2004-10-19T10:24:01-03:00')
ON DUPLICATE KEY UPDATE id = id;
--perfil da conta
INSERT INTO profile (id, account_id, owner_name, icon_cod, preferences, created_at, updated_at)
VALUES ('acb9947a-1735-4bd5-a2e8-0e8adedae018', '48c3684d-3093-4e99-8ac6-8dbc6a902cce', 'andre perfil teste', 1, '{}',  '2023-08-03 15:00:21.045328', '2023-08-03 15:00:21.045328')
ON DUPLICATE KEY UPDATE id = id;
--conta admin
INSERT INTO account (id, active_flag, current_plan, day_plan_expire_date, payment_history, limit_of_profiles, created_at, updated_at)
VALUES ('dbca60d0-2f7d-497d-8964-66fe2f62a179', TRUE, 2, 22, '[]', 5, '2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328')
ON DUPLICATE KEY UPDATE id = id;
INSERT INTO user_info (id, email, password, birth_date, role, account_id, created_at, updated_at) --admin123
VALUES ('385a5f10-694e-430c-853f-5fcdc13b5f17', 'andre@admin.com', '$2a$12$ZyygM5Xn77wOwoooLyaSA.b1O9DttZSLHNp6VGsxB2NbGhXHozlLi', '1999-07-22', 1, 'dbca60d0-2f7d-497d-8964-66fe2f62a179', '2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328')
ON DUPLICATE KEY UPDATE id = id;
--perfil da conta
INSERT INTO profile (id, account_id, owner_name, icon_cod, preferences, created_at, updated_at)
VALUES ('acb9948a-1735-4bd5-a2e8-0e8adedae018', 'dbca60d0-2f7d-497d-8964-66fe2f62a179', 'andre adm', 3, '{}',  '2023-08-03 15:00:21.045328', '2023-08-03 15:00:21.045328')
ON DUPLICATE KEY UPDATE id = id;
--adicionando anime visto ao perfil
INSERT INTO profile_and_seen_medias (profile_id, media_id)
VALUES ('acb9948a-1735-4bd5-a2e8-0e8adedae018', 4)
ON DUPLICATE KEY UPDATE id = id;