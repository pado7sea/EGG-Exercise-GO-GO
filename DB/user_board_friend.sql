CREATE DATABASE IF NOT EXISTS `egg`;

USE `egg`;

CREATE TABLE IF NOT EXISTS `users` (
  `id` varchar(40) NOT NULL,
  `password` varchar(200) NOT NULL,
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


INSERT INTO users(id, password, name, birthday)
VALUES
("user1", "password1", "John Doe", "1990-05-15"),
("user2", "password2", "Jane Smith", "1985-08-22"),
("user3", "password3", "Bob Johnson", "1995-03-10"),
("user4", "password4", "Alice Brown", "1982-11-28");

CREATE TABLE IF NOT EXISTS `board` (
	id INT AUTO_INCREMENT,
    writer VARCHAR(20) NOT NULL,
    title VARCHAR(50) NOT NULL,
    content TEXT,
    viewCnt INT DEFAULT 0,
    regDate TIMESTAMP DEFAULT now(),
	img VARCHAR(1000),
    orgImg VARCHAR (1000),
    PRIMARY KEY(id)
);

-- 데이터 추가 예시
INSERT INTO `board` (writer, title, content, viewCnt, regDate) VALUES
('JohnDoe', '첫 번째 글', '이 글은 첫 번째 글입니다. 환영합니다!', 10, '2023-01-01 12:00:00'),
('AliceSmith', '두 번째 글', '두 번째 글 내용입니다. 어떠세요?', 15, '2023-02-05 15:30:00'),
('BobJohnson', '테스트 글', '테스트 글입니다. 데이터베이스 작동 확인 중...', 5, '2023-03-10 09:45:00'),
('아오', '이미지 테스트', '이미지 테스트임....', 0, '2023-01-01 12:00:00');
-- 추가된 데이터 확인
SELECT * FROM board;

-- 추가된 데이터 확인
SELECT * FROM users;

-- 샘플 친구 추가
INSERT INTO friends (user_id, friend_id)
VALUES ("user1", "user2");

-- 'friends' 테이블 확인
SELECT * FROM friends;

commit;



