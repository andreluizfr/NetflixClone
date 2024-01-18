-- criando usuários e informações
-- basic = 0, admin = 1
--conta comum
INSERT INTO account (id, active_flag, current_plan, plan_expire_day, payment_history, limit_of_profiles, created_at, updated_at)
VALUES ('48c3684d-3093-4e99-8ac6-8dbc6a902cce', TRUE, 1, 22, '[]', 3, '2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328');
--usuario comum
INSERT INTO user_info (id, enabled_flag, deleted_flag, email, password, birth_date, role_id, account_id, created_at, updated_at) --teste123
VALUES ('561718d0-1c7c-4251-97c0-946f1048243b', TRUE, FALSE, 'teste@teste.com', '$2a$12$Ju2OKxxKmeE2A36X885fR.DUuQutAQmvLOcZgo6Xh7fGCJwyDN8LW', '2003-10-19', 3, '48c3684d-3093-4e99-8ac6-8dbc6a902cce', '2023-08-02 13:14:21.045328','2023-08-02 13:14:21.045328');
--perfil comum
INSERT INTO profile (id, account_id, owner_name, icon_cod, seen_medias_ids, preferences, created_at, updated_at)
VALUES ('acb9947a-1735-4bd5-a2e8-0e8adedae018', '48c3684d-3093-4e99-8ac6-8dbc6a902cce', 'andre perfil teste', 1, '[]', '{}', '2023-08-03 15:00:21.045328', '2023-08-03 15:00:21.045328');

--conta admin
INSERT INTO account (id, active_flag, current_plan, plan_expire_day, payment_history, limit_of_profiles, created_at, updated_at)
VALUES ('dbca60d0-2f7d-497d-8964-66fe2f62a179', TRUE, 2, 22, '[]', 5, '2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328');
--usuario admin
INSERT INTO user_info (id,enabled_flag, deleted_flag, email, password, birth_date, role_id, account_id, created_at, updated_at) --admin123
VALUES ('385a5f10-694e-430c-853f-5fcdc13b5f17', TRUE, FALSE, 'andre@admin.com', '$2a$12$ZyygM5Xn77wOwoooLyaSA.b1O9DttZSLHNp6VGsxB2NbGhXHozlLi', '1999-07-22', 1, 'dbca60d0-2f7d-497d-8964-66fe2f62a179', '2023-08-02 13:14:21.045328', '2023-08-02 13:14:21.045328');
--perfil admin
INSERT INTO profile (id, account_id, owner_name, icon_cod, seen_medias_ids, preferences, created_at, updated_at)
VALUES ('acb9948a-1735-4bd5-a2e8-0e8adedae018', 'dbca60d0-2f7d-497d-8964-66fe2f62a179', 'andre adm', 3, '[]', '{}', '2023-08-03 15:00:21.045328', '2023-08-03 15:00:21.045328');




INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (1, 'CREATE_USER', 'Create user', NOW(), NOW());
INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (2, 'VIEW_USER', 'View user', NOW(), NOW());
INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (3, 'EDIT_USER', 'Edit user', NOW(), NOW());
INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (4, 'DELETE_USER', 'Delete user', NOW(), NOW());

INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (5, 'VIEW_ACCOUNT', 'View account', NOW(), NOW());
INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (6, 'EDIT_ACCOUNT', 'Edit account', NOW(), NOW());

INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (7, 'CREATE_PROFILE', 'Create profile', NOW(), NOW());
INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (8, 'VIEW_PROFILE', 'View profile', NOW(), NOW());
INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (9, 'EDIT_PROFILE', 'Edit profile', NOW(), NOW());
INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (10, 'DELETE_PROFILE', 'Delete profile', NOW(), NOW());

INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (11, 'CREATE_MEDIA', 'Create media', NOW(), NOW());
INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (12, 'VIEW_MEDIA', 'View media', NOW(), NOW());
INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (13, 'EDIT_MEDIA', 'Edit media', NOW(), NOW());
INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (14, 'DELETE_MEDIA', 'Delete media', NOW(), NOW());

INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (15, 'CREATE_PREVIEW_MEDIA', 'Create preview media', NOW(), NOW());
INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (16, 'VIEW_PREVIEW_MEDIA', 'View preview media', NOW(), NOW());
INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (17, 'EDIT_PREVIEW_MEDIA', 'Edit preview media', NOW(), NOW());
INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (18, 'DELETE_PREVIEW_MEDIA', 'Delete preview media', NOW(), NOW());

INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (19, 'CREATE_MEDIA_LIST', 'Create media list', NOW(), NOW());
INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (20, 'VIEW_MEDIA_LIST', 'View media list', NOW(), NOW());
INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (21, 'EDIT_MEDIA_LIST', 'Edit media list', NOW(), NOW());
INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (22, 'DELETE_MEDIA_LIST', 'Delete media list', NOW(), NOW());

INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (23, 'VIEW_OWN_USER', 'View own user', NOW(), NOW());
INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (24, 'EDIT_OWN_USER', 'Edit own user', NOW(), NOW());
INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (25, 'DELETE_OWN_USER', 'Delete own user', NOW(), NOW());
INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (26, 'VIEW_OWN_ACCOUNT', 'View own account', NOW(), NOW());
INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (27, 'EDIT_OWN_ACCOUNT', 'Edit own account', NOW(), NOW());
INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (28, 'VIEW_OWN_PROFILE', 'View own profile', NOW(), NOW());
INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (29, 'EDIT_OWN_PROFILE', 'Edit own profile', NOW(), NOW());
INSERT INTO permission (id, name, description, created_at, updated_at)
VALUES (30, 'DELETE_OWN_PROFILE', 'Delete own profile', NOW(), NOW());



INSERT INTO role (id, name, created_at, updated_at)
VALUES (1, 'ADMIN', NOW(), NOW());
INSERT INTO role (id, name, created_at, updated_at)
VALUES (2, 'STAFF', NOW(), NOW());
INSERT INTO role (id, name, created_at, updated_at)
VALUES (3, 'USER', NOW(), NOW());




INSERT INTO role_and_permission (role_id, permission_id)
SELECT 1, id
FROM permission;

INSERT INTO role_and_permission (role_id, permission_id)
SELECT 2, x
FROM UNNEST(ARRAY[2,5,8,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30]) x;

INSERT INTO role_and_permission (role_id, permission_id)
SELECT 3, x
FROM UNNEST(ARRAY[12,16,20,23,24,25,26,27,28,29,30]) x;