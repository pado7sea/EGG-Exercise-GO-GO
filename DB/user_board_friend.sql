DROP DATABASE `egg`;
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
("11", "4fc82b26aecb47d2868c4efbe3581732a3e7cbcc6c2efb32062c08170a05eeb8", "일일", "1111-11-11"),
("22", "785f3ec7eb32f30b90cd0fcf3657d388b5ff4297f2f9716ff66e9b69c05ddd09", "짱구", "2000-12-22"),
("33", "c6f3ac57944a531490cd39902d0f777715fd005efac9a30622d5f5205e7f6894", "마카오", "1990-11-11");

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
INSERT INTO `board` (writer, title, content, viewCnt, regDate, img, orgImg) VALUES
('일일', '운동완', '오늘은 헬스를 했어요', 0, '2023-11-23 14:19:28', '1700716768926_KakaoTalk_Moim_4CkZHNUele2uqPIX26gdCG6z3O3aRH.jpg', 'KakaoTalk_Moim_4CkZHNUele2uqPIX26gdCG6z3O3aRH.jpg'),
('짱구', '축구한판부리부리', '장미반 친구들과 부리부리부립뤼부리부리뷔ㅜ', 2, '2023-11-23 14:21:34', '1700716880405_KakaoTalk_20180627_025945944.jpg.png', 'KakaoTalk_20180627_025945944.jpg.png' ),
('짱구', '흰둥이랑 산책', '부리부리부리ㅜ비ㅜ리불', 0, '2023-11-23 14:23:15', '1700716995038_제목-없음-17.gif', '제목-없음-17.gif' ), -- 이미지가 없을 경우 imagePath를 null로 설정
('마카오', '아라베스크', '발레', 0, '2023-11-23 14:25:03', '1700717103123_20200906_105938.gif', '20200906_105938.gif'); -- imagePath를 적절히 수정

-- 추가된 데이터 확인
SELECT * FROM board;

-- 추가된 데이터 확인
SELECT * FROM users;

-- 샘플 친구 추가
INSERT INTO friends (user_id, friend_id)
VALUES ("11", "11");

-- 'friends' 테이블 확인
SELECT * FROM friends;

commit;


