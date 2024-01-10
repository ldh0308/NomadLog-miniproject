CREATE TABLE bbs (
    bbs_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    contents TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modify_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    view_cnt INT DEFAULT 0,
    like_cnt INT DEFAULT 0,
    tag_id INT,
    is_deleted BOOLEAN DEFAULT 0,
    member_code INT
);

INSERT INTO bbs (title, contents, view_cnt, like_cnt, tag_id, member_code)
VALUES
  ('유럽 여행 후기♡', '첫 번째 게시물의 내용입니다.', 50, 10, 1, 101),
  ('강원도 양양 먹방 여행', '두 번째 게시물의 내용입니다.', 30, 5, 2, 102),
  ('서울 근교 드라이브 여행', '세 번째 게시물의 내용입니다.', 20, 15, 1, 103),
  ('부산 겨울 바다', '네 번째 게시물의 내용입니다.', 15, 7, 4, 107);


ALTER TABLE bbs ADD CONSTRAINT 
	continent_id FOREIGN KEY(continent_id)
	REFERENCES category(continent_id) ON DELETE CASCADE ON UPDATE CASCADE;
	/* */
ALTER TABLE countries ADD CONSTRAINT 
	continent_Id FOREIGN KEY(continent_Id)
	REFERENCES continents(continent_Id) ON DELETE CASCADE ON UPDATE CASCADE;
	

SELECT ct.continent_name, c.country_name FROM countries c 
	JOIN continents ct ON c.continent_id = ct.continent_id;
	
SELECT b.bbs_id, b.title, b.contents, b.created_at, b.modify_at, b.view_cnt, b.like_cnt, b.member_code,
		ct.continent_name, c.country_name
	FROM bbs b
	JOIN countries c ON b.country_id = c.country_id
	JOIN continents ct ON c.continent_id = ct.continent_id
	
SELECT continent_name FROM continents ct 
		JOIN countries c ON ct.continent_id = c.continent_id
		WHERE country_id = 2
		
SELECT country_name FROM countries WHERE country_id = 2

INSERT INTO bbs(title, contents) VALUES ("감자","구구구");
SELECT LAST_INSERT_ID()

SELECT * FROM bbs;

SELECT contents FROM bbs WHERE bbs_id = 1;

SELECT country_id FROM countries
	WHERE country_name = '중국';
	
ALTER TABLE hashtag ADD CONSTRAINT 
	bbs_id FOREIGN KEY(bbs_id)
	REFERENCES bbs(bbs_id) ON DELETE CASCADE ON UPDATE CASCADE;
	
INSERT INTO hashtag(contnet)
	VALUES "감자";
	
SELECT LAST_INSERT_ID() AS bbsId

		INSERT INTO hashtag((SELECT LAST_INSERT_ID() AS bbsId), content)
		VALUES (1,"사과");

SELECT * FROM hashtag WHERE bbs_id = 50;

SELECT *
		FROM hashtag WHERE bbs_id = 50;
