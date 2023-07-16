CREATE TABLE credentials
(
    user_id varchar(50) PRIMARY KEY NOT NULL,
    pw varchar(68)             NOT NULL,
    active  tinyint                 NOT NULL
);


CREATE TABLE roles
(
    user_id  varchar(50) NOT NULL,
    role varchar(50) NOT NULL,

    unique key authorities_idx_1 (user_id, role),
    CONSTRAINT authorities_ibfk_1 FOREIGN KEY (user_id) REFERENCES credentials (user_id)
);

-- NOTE: The passwords are encrypted using BCrypt
-- A generation tool is avail at: https://www.bcryptcalculator.com/
-- Default passwords here are: test


INSERT INTO `credentials`
VALUES
    ('peter','{bcrypt}$2a$10$H0TItclQvsLneTPU/PJpvui8cWcWGL3rfLwnp6JLmBc/fNc6VI9sy',1),
    ('ivan','{bcrypt}$2a$10$H0TItclQvsLneTPU/PJpvui8cWcWGL3rfLwnp6JLmBc/fNc6VI9sy',1),
    ('emil','{bcrypt}$2a$10$H0TItclQvsLneTPU/PJpvui8cWcWGL3rfLwnp6JLmBc/fNc6VI9sy',1);


INSERT INTO `roles`
VALUES
    ('peter','ROLE_EMPLOYEE'),
    ('ivan','ROLE_EMPLOYEE'),
    ('ivan','ROLE_MANAGER'),
    ('emil','ROLE_EMPLOYEE'),
    ('emil','ROLE_MANAGER'),
    ('emil','ROLE_ADMIN');