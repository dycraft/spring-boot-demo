DROP TABLE IF EXISTS user;

CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(127) NOT NULL,
    password VARCHAR(127) NOT NULL,
    created_at TIMESTAMP(3) NOT NULL DEFAULT current_timestamp(3),
    updated_at TIMESTAMP(3) NOT NULL DEFAULT current_timestamp(3) ON UPDATE current_timestamp(3)
);

INSERT INTO user (email, password) VALUES
('a@b.cd', 'test');


DROP TABLE IF EXISTS note;

CREATE TABLE note (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(127) NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP(3) NOT NULL DEFAULT current_timestamp(3),
    updated_at TIMESTAMP(3) NOT NULL DEFAULT current_timestamp(3) ON UPDATE current_timestamp(3)
);

INSERT INTO note (title, content) VALUES
('title', 'test');
