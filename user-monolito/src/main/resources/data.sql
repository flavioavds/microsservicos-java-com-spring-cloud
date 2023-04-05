INSERT INTO tb_user (name, email, password) VALUES ('Flavio Augusto', 'flavio@gmail.com', '$2a$10$y41F8jCg9BwUeW2JFO/HgeItjorpYSP9iXG/Q66WaqIXKcjwG14vi');
INSERT INTO tb_user (name, email, password) VALUES ('Rogerio Venancio', 'rogerio@gmail.com', '$2a$10$y41F8jCg9BwUeW2JFO/HgeItjorpYSP9iXG/Q66WaqIXKcjwG14vi');

INSERT INTO tb_role (name) VALUES ('ROLE_USER');
INSERT INTO tb_role (name) VALUES ('ROLE_MODERATOR');
INSERT INTO tb_role (name) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO tb_user_roles (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_roles (user_id, role_id) VALUES (2, 3);