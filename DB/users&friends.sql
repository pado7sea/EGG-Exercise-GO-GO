CREATE DATABASE IF NOT EXISTS `egg`;

USE `egg`;

CREATE TABLE IF NOT EXISTS `users` (
  `id` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `name` varchar(40) NOT NULL,
  `birthday` varchar(40) NOT NULL,
  `egg_count` int,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4;

-- 'friends' 테이블 생성
CREATE TABLE IF NOT EXISTS `friends` (
  `user_id` varchar(40) NOT NULL,
  `friend_id` varchar(40) NOT NULL,
  PRIMARY KEY (`user_id`, `friend_id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  FOREIGN KEY (`friend_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4;

commit;

INSERT INTO users(id, password, name, birthday)
VALUES
("user1", "password1", "John Doe", "1990-05-15"),
("user2", "password2", "Jane Smith", "1985-08-22"),
("user3", "password3", "Bob Johnson", "1995-03-10"),
("user4", "password4", "Alice Brown", "1982-11-28");

-- 추가된 데이터 확인
SELECT * FROM users;

-- 샘플 친구 추가
INSERT INTO friends (user_id, friend_id)
VALUES ("user1", "user2");

-- 'friends' 테이블 확인
SELECT * FROM friends;




