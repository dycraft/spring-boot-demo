DROP TABLE IF EXISTS user;

CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(127) NOT NULL,
    password VARCHAR(127) NOT NULL,
    created_at TIMESTAMP(3) NOT NULL DEFAULT current_timestamp(3),
    updated_at TIMESTAMP(3) NOT NULL DEFAULT current_timestamp(3) ON UPDATE current_timestamp(3),
    constraint idx_username unique (username)
);

INSERT INTO user (username, password) VALUES
('test', '$2a$10$S2jfr8xzO1xZqWyYxBMRQubnGc3MGZ1nWAxQgCtO5kkYTASVbKX9.');


DROP TABLE IF EXISTS note;

CREATE TABLE note (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(127) NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP(3) NOT NULL DEFAULT current_timestamp(3),
    updated_at TIMESTAMP(3) NOT NULL DEFAULT current_timestamp(3) ON UPDATE current_timestamp(3)
);

INSERT INTO note (user_id, title, content) VALUES
(1, 'title', 'content');
