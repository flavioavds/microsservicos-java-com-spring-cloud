CREATE TABLE tb_user(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL,
password VARCHAR(255));

CREATE TABLE tb_role(
id INTEGER AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255));

CREATE TABLE tb_user_roles(
user_id BIGINT NOT NULL,
role_id INTEGER NOT NULL,
FOREIGN KEY (user_id) REFERENCES tb_user(id),
FOREIGN KEY (role_id) REFERENCES tb_role(id));