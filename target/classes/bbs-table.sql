DROP TABLE IF EXISTS bbs;
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

select * from bbs;

COMMIT;