INSERT INTO mood (id, name)
VALUES ('1', '베지테리언');
INSERT INTO mood (id, name)
VALUES ('2', '베지테리언2');
INSERT INTO mood (id, name)
VALUES ('3', '무드1');
INSERT INTO mood (id, name)
VALUES ('4', '무드2');
INSERT INTO mood (id, name)
VALUES ('5', '무드3');
INSERT INTO mood (id, name)
VALUES ('6', '무드4');

INSERT INTO image (id, url)
VALUES ('1', 'https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png');

# 멤버
INSERT INTO member (id, email, nickname, password, mood_id, profile_image_id)
VALUES ('1', 'albert@albert.com', '알버트', 'albert123!', '1', '1');
INSERT INTO member (id, email, nickname, password, mood_id, profile_image_id)
VALUES ('2', 'sully@sully.com', '설리', 'sully123!', '1', '1');
INSERT INTO member (id, email, nickname, password, mood_id, profile_image_id)
VALUES ('3', 'bono@bono.com', '보노', 'bono123!', '1', '1');
INSERT INTO member (id, email, nickname, password, mood_id, profile_image_id)
VALUES ('4', 'ati@ati.com', '아티', 'ati123!', '1', '1');
INSERT INTO member (id, email, nickname, password, mood_id, profile_image_id)
VALUES ('5', 'puban@puban.com', '푸반', 'puban123!', '1', '1');

# 댓글
insert into comment (id, content, created_at, member_id, feed_id, deleted, updated_at)
values ("1", "정말 맛있는 음식이었어요!", "2023-11-07 11:11:11", 1, 1, "1", "2023-11-07 11:11:11"),
       ("2", "여기 다시 올 거예요!", "2023-11-07 11:12:11", 2, 1, "1", "2023-11-07 11:12:11"),
       ("3", "친절한 서비스가 인상적이었어요.", "2023-11-07 11:13:11", 3, 1, "1", "2023-11-07 11:13:11"),
       ("4", "분위기가 아늑하고 좋았습니다.", "2023-11-07 11:14:11", 4, 1, "1", "2023-11-07 11:14:11"),
       ("5", "음식이 너무 맛있어서 놀랐어요!", "2023-11-07 11:15:11", 5, 1, "1", "2023-11-07 11:15:11"),
       ("6", "가격 대비 훌륭해요!", "2023-11-07 11:16:11", 1, 1, "1", "2023-11-07 11:16:11"),
       ("7", "재료가 신선해서 좋았어요.", "2023-11-07 11:17:11", 2, 1, "1", "2023-11-07 11:17:11"),
       ("8", "아이와 함께 방문하기 좋은 곳이에요.", "2023-11-07 11:18:11", 3, 1, "1", "2023-11-07 11:18:11"),
       ("9", "다양한 메뉴가 매력적이었어요.", "2023-11-07 11:19:11", 4, 1, "1", "2023-11-07 11:19:11"),
       ("10", "디저트가 정말 일품이었습니다!", "2023-11-07 11:20:11", 5, 1, "1", "2023-11-07 11:20:11"),
       ("11", "정말 맛있는 음식이었어요!", "2023-11-07 11:11:11", 1, 1, "1", "2023-11-07 11:11:11"),
       ("12", "여기 다시 올 거예요!", "2023-11-07 11:12:11", 2, 1, "1", "2023-11-07 11:12:11"),
       ("13", "친절한 서비스가 인상적이었어요.", "2023-11-07 11:13:11", 3, 1, "1", "2023-11-07 11:13:11"),
       ("14", "분위기가 아늑하고 좋았습니다.", "2023-11-07 11:14:11", 4, 1, "1", "2023-11-07 11:14:11"),
       ("15", "음식이 너무 맛있어서 놀랐어요!", "2023-11-07 11:15:11", 5, 1, "1", "2023-11-07 11:15:11"),
       ("16", "가격 대비 훌륭해요!", "2023-11-07 11:16:11", 1, 1, "1", "2023-11-07 11:16:11"),
       ("17", "재료가 신선해서 좋았어요.", "2023-11-07 11:17:11", 2, 1, "1", "2023-11-07 11:17:11"),
       ("18", "아이와 함께 방문하기 좋은 곳이에요.", "2023-11-07 11:18:11", 3, 1, "1", "2023-11-07 11:18:11"),
       ("19", "다양한 메뉴가 매력적이었어요.", "2023-11-07 11:19:11", 4, 1, "1", "2023-11-07 11:19:11"),
       ("20", "디저트가 정말 일품이었습니다!", "2023-11-07 11:20:11", 5, 1, "1", "2023-11-07 11:20:11"),
       ("21", "정말 맛있는 음식이었어요!", "2023-11-07 11:11:11", 1, 1, "1", "2023-11-07 11:11:11"),
       ("22", "여기 다시 올 거예요!", "2023-11-07 11:12:11", 2, 1, "1", "2023-11-07 11:12:11"),
       ("23", "친절한 서비스가 인상적이었어요.", "2023-11-07 11:13:11", 3, 1, "1", "2023-11-07 11:13:11"),
       ("24", "분위기가 아늑하고 좋았습니다.", "2023-11-07 11:14:11", 4, 1, "1", "2023-11-07 11:14:11"),
       ("25", "음식이 너무 맛있어서 놀랐어요!", "2023-11-07 11:15:11", 5, 1, "1", "2023-11-07 11:15:11"),
       ("26", "가격 대비 훌륭해요!", "2023-11-07 11:16:11", 1, 1, "1", "2023-11-07 11:16:11"),
       ("27", "재료가 신선해서 좋았어요.", "2023-11-07 11:17:11", 2, 1, "1", "2023-11-07 11:17:11"),
       ("28", "아이와 함께 방문하기 좋은 곳이에요.", "2023-11-07 11:18:11", 3, 1, "1", "2023-11-07 11:18:11"),
       ("29", "다양한 메뉴가 매력적이었어요.", "2023-11-07 11:19:11", 4, 1, "1", "2023-11-07 11:19:11"),
       ("30", "디저트가 정말 일품이었습니다!", "2023-11-07 11:20:11", 5, 1, "1", "2023-11-07 11:20:11"),
       ("31", "정말 맛있는 음식이었어요!", "2023-11-07 11:11:11", 1, 1, "1", "2023-11-07 11:11:11"),
       ("32", "여기 다시 올 거예요!", "2023-11-07 11:12:11", 2, 1, "1", "2023-11-07 11:12:11"),
       ("33", "친절한 서비스가 인상적이었어요.", "2023-11-07 11:13:11", 3, 1, "1", "2023-11-07 11:13:11"),
       ("34", "분위기가 아늑하고 좋았습니다.", "2023-11-07 11:14:11", 4, 1, "1", "2023-11-07 11:14:11"),
       ("35", "음식이 너무 맛있어서 놀랐어요!", "2023-11-07 11:15:11", 5, 1, "1", "2023-11-07 11:15:11"),
       ("36", "가격 대비 훌륭해요!", "2023-11-07 11:16:11", 1, 1, "1", "2023-11-07 11:16:11"),
       ("37", "재료가 신선해서 좋았어요.", "2023-11-07 11:17:11", 2, 1, "1", "2023-11-07 11:17:11"),
       ("38", "아이와 함께 방문하기 좋은 곳이에요.", "2023-11-07 11:18:11", 3, 1, "1", "2023-11-07 11:18:11"),
       ("39", "다양한 메뉴가 매력적이었어요.", "2023-11-07 11:19:11", 4, 1, "1", "2023-11-07 11:19:11"),
       ("40", "디저트가 정말 일품이었습니다!", "2023-11-07 11:20:11", 5, 1, "1", "2023-11-07 11:20:11"),
       ("41", "정말 맛있는 음식이었어요!", "2023-11-07 11:11:11", 1, 1, "1", "2023-11-07 11:11:11"),
       ("42", "여기 다시 올 거예요!", "2023-11-07 11:12:11", 2, 1, "1", "2023-11-07 11:12:11"),
       ("43", "친절한 서비스가 인상적이었어요.", "2023-11-07 11:13:11", 3, 1, "1", "2023-11-07 11:13:11"),
       ("44", "분위기가 아늑하고 좋았습니다.", "2023-11-07 11:14:11", 4, 1, "1", "2023-11-07 11:14:11"),
       ("45", "음식이 너무 맛있어서 놀랐어요!", "2023-11-07 11:15:11", 5, 1, "1", "2023-11-07 11:15:11"),
       ("46", "가격 대비 훌륭해요!", "2023-11-07 11:16:11", 1, 1, "1", "2023-11-07 11:16:11"),
       ("47", "재료가 신선해서 좋았어요.", "2023-11-07 11:17:11", 2, 1, "1", "2023-11-07 11:17:11"),
       ("48", "아이와 함께 방문하기 좋은 곳이에요.", "2023-11-07 11:18:11", 3, 1, "1", "2023-11-07 11:18:11"),
       ("49", "다양한 메뉴가 매력적이었어요.", "2023-11-07 11:19:11", 4, 1, "1", "2023-11-07 11:19:11"),
       ("50", "디저트가 정말 일품이었습니다!", "2023-11-07 11:20:11", 5, 1, "1", "2023-11-07 11:20:11"),
       ("51", "정말 맛있는 음식이었어요!", "2023-11-07 11:11:11", 1, 1, "1", "2023-11-07 11:11:11"),
       ("52", "여기 다시 올 거예요!", "2023-11-07 11:12:11", 2, 1, "1", "2023-11-07 11:12:11"),
       ("53", "친절한 서비스가 인상적이었어요.", "2023-11-07 11:13:11", 3, 1, "1", "2023-11-07 11:13:11"),
       ("54", "분위기가 아늑하고 좋았습니다.", "2023-11-07 11:14:11", 4, 1, "1", "2023-11-07 11:14:11"),
       ("55", "음식이 너무 맛있어서 놀랐어요!", "2023-11-07 11:15:11", 5, 1, "1", "2023-11-07 11:15:11"),
       ("56", "가격 대비 훌륭해요!", "2023-11-07 11:16:11", 1, 1, "1", "2023-11-07 11:16:11"),
       ("57", "재료가 신선해서 좋았어요.", "2023-11-07 11:17:11", 2, 1, "1", "2023-11-07 11:17:11"),
       ("58", "아이와 함께 방문하기 좋은 곳이에요.", "2023-11-07 11:18:11", 3, 1, "1", "2023-11-07 11:18:11"),
       ("59", "다양한 메뉴가 매력적이었어요.", "2023-11-07 11:19:11", 4, 1, "1", "2023-11-07 11:19:11"),
       ("60", "디저트가 정말 일품이었습니다!", "2023-11-07 11:20:11", 5, 1, "1", "2023-11-07 11:20:11"),
       ("61", "정말 맛있는 음식이었어요!", "2023-11-07 11:11:11", 1, 1, "1", "2023-11-07 11:11:11"),
       ("62", "매콤한 음식이 끝내줬어요!", "2023-11-07 12:12:11", 2, 1, "1", "2023-11-07 12:12:11"),
       ("63", "해산물이 신선해서 계속 생각나네요.", "2023-11-07 12:13:11", 3, 1, "1", "2023-11-07 12:13:11"),
       ("64", "이렇게 부드러운 스테이크는 처음이에요!", "2023-11-07 12:14:11", 4, 1, "1", "2023-11-07 12:14:11"),
       ("65", "디자인이 멋진 카페였어요.", "2023-11-07 12:15:11", 5, 1, "1", "2023-11-07 12:15:11"),
       ("66", "전통 음식의 진수를 보여주는 곳!", "2023-11-07 12:16:11", 1, 1, "1", "2023-11-07 12:16:11"),
       ("67", "감자튀김이 너무 바삭바삭해요!", "2023-11-07 12:17:11", 2, 1, "1", "2023-11-07 12:17:11"),
       ("68", "와인 선택이 훌륭했어요!", "2023-11-07 12:18:11", 3, 1, "1", "2023-11-07 12:18:11"),
       ("69", "음식이 너무 늦게 나와서 아쉬웠어요.", "2023-11-07 12:19:11", 4, 1, "1", "2023-11-07 12:19:11"),
       ("70", "이런 맛집을 이제야 발견하다니!", "2023-11-07 12:20:11", 5, 1, "1", "2023-11-07 12:20:11");

# 피드
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('1', '2', 0, '2023-11-07T11:11:11', 1, 10, '역삼동', '맛있어요!', '2023-11-08T14:11:11');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('2', '2', 5, '2023-11-06T10:10:10', 0, 0, '강남구', '좋아요!', '2023-11-07T13:10:10');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('3', '2', 3, '2023-11-05T09:09:09', 1, 20, '서초구', '아주 맛있습니다!', '2023-11-06T12:09:09');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('4', '2', 8, '2023-11-04T08:08:08', 0, 0, '신사동', '평범해요', '2023-11-05T11:08:08');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('5', '2', 2, '2023-11-03T07:07:07', 1, 30, '홍대입구', '정말 최고!', '2023-11-04T10:07:07');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('6', '2', 6, '2023-11-02T06:06:06', 0, 0, '이태원', '별로예요', '2023-11-03T09:06:06');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('7', '2', 1, '2023-11-01T05:05:05', 1, 18, '명동', '추천합니다!', '2023-11-02T08:05:05');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('8', '2', 4, '2023-10-31T04:04:04', 0, 0, '논현동', '괜찮아요', '2023-11-01T07:04:04');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('9', '2', 9, '2023-10-30T03:03:03', 1, 8, '잠실', '그저 그래요', '2023-10-31T06:03:03');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('10', '2', 7, '2023-10-29T02:02:02', 0, 0, '청담동', '다시 오고 싶어요!', '2023-10-30T05:02:02');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('11', '2', 10, '2023-10-28T01:01:01', 1, 9, '건대입구', '최악이었어요', '2023-10-29T04:01:01');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('12', '2', 4, '2023-10-27T15:15:15', 1, 7, '송파구', '정말 괜찮았어요!', '2023-10-28T08:15:15');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('13', '2', 6, '2023-10-26T14:14:14', 0, 0, '중구', '다시는 안 가고 싶어요', '2023-10-27T09:14:14');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('14', '2', 2, '2023-10-25T13:13:13', 1, 16, '영등포구', '평이하네요', '2023-10-26T10:13:13');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('15', '2', 8, '2023-10-24T12:12:12', 0, 0, '마포구', '최고예요!', '2023-10-25T11:12:12');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('16', '2', 5, '2023-10-23T11:11:11', 1, 14, '관악구', '볼만해요', '2023-10-24T12:11:11');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('17', '2', 3, '2023-10-22T10:10:10', 0, 0, '용산구', '기대 이하였어요', '2023-10-23T13:10:10');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('18', '2', 1, '2023-10-21T09:09:09', 1, 0, '동대문구', '추천드려요!', '2023-10-22T14:09:09');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('19', '2', 7, '2023-10-20T08:08:08', 0, 0, '성동구', '그럭저럭이에요', '2023-10-21T15:08:08');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('20', '2', 9, '2023-10-19T07:07:07', 1, 22, '서대문구', '별로였어요', '2023-10-20T16:07:07');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('21', '2', 0, '2023-10-18T06:06:06', 0, 0, '강서구', '최고의 맛!', '2023-10-19T17:06:06');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('22', '2', 11, '2023-10-17T05:05:05', 1, 17, '노원구', '가볼만 합니다', '2023-10-18T18:05:05');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('23', '2', 10, '2023-10-16T04:04:04', 0, 0, '강동구', '평균 이상이에요', '2023-10-17T19:04:04');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('24', '2', 12, '2023-10-15T03:03:03', 1, 6, '구로구', '다시 방문하고 싶네요', '2023-10-16T20:03:03');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('25', '2', 13, '2023-10-14T02:02:02', 0, 0, '금천구', '별로 추천하고 싶지 않아요', '2023-10-15T21:02:02');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('26', '2', 14, '2023-10-13T01:01:01', 1, 9, '양천구', '매우 만족스러워요', '2023-10-14T22:01:01');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('27', '2', 15, '2023-10-12T00:00:00', 0, 0, '강북구', '기대했던 것보다 덜해요', '2023-10-13T23:00:00');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('28', '2', 16, '2023-10-11T23:23:23', 1, 5, '도봉구', '정말 최악이었어요', '2023-10-12T22:23:23');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('29', '2', 17, '2023-10-10T22:22:22', 0, 0, '성북구', '훌륭합니다!', '2023-10-11T21:22:22');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('30', '2', 18, '2023-10-09T21:21:21', 1, 24, '종로구', '보통이에요', '2023-10-10T20:21:21');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('31', '2', 19, '2023-10-08T20:20:20', 0, 0, '중랑구', '맛있습니다!', '2023-10-09T19:20:20');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('32', '2', 7, '2023-10-07T19:19:19', 1, 15, '은평구', '맛있어요!', '2023-10-08T18:19:19');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('33', '2', 2, '2023-10-06T18:18:18', 0, 0, '동작구', '별로였어요', '2023-10-07T17:18:18');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('34', '2', 10, '2023-10-05T17:17:17', 1, 25, '광진구', '괜찮았어요', '2023-10-06T16:17:17');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('35', '2', 4, '2023-10-04T16:16:16', 0, 0, '서초구', '추천합니다!', '2023-10-05T15:16:16');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('36', '2', 6, '2023-10-03T15:15:15', 1, 20, '강서구', '다시 방문할게요', '2023-10-04T14:15:15');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('37', '2', 8, '2023-10-02T14:14:14', 0, 0, '송파구', '별로 추천하고 싶지 않아요', '2023-10-03T13:14:14');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('38', '2', 3, '2023-10-01T13:13:13', 1, 18, '노원구', '아주 좋았어요!', '2023-10-02T12:13:13');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('39', '2', 9, '2023-09-30T12:12:12', 0, 0, '양천구', '평범해요', '2023-10-01T11:12:12');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('40', '2', 1, '2023-09-29T11:11:11', 1, 22, '강남구', '좋았습니다!', '2023-09-30T10:11:11');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('41', '2', 5, '2023-09-28T10:10:10', 0, 0, '마포구', '기대 이하였어요', '2023-09-29T09:10:10');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('42', '2', 11, '2023-09-27T09:09:09', 1, 16, '관악구', '다시 오고 싶어요!', '2023-09-28T08:09:09');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('43', '2', 0, '2023-09-26T08:08:08', 0, 0, '중랑구', '훌륭합니다!', '2023-09-27T07:08:08');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('44', '2', 12, '2023-09-25T07:07:07', 1, 11, '강북구', '그저 그래요', '2023-09-26T06:07:07');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('45', '2', 13, '2023-09-24T06:06:06', 0, 0, '성북구', '매우 만족스러워요', '2023-09-25T05:06:06');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('46', '2', 14, '2023-09-23T05:05:05', 1, 21, '구로구', '정말 최고!', '2023-09-24T04:05:05');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('47', '2', 15, '2023-09-22T04:04:04', 0, 0, '금천구', '가볼만 합니다', '2023-09-23T03:04:04');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('48', '2', 16, '2023-09-21T03:03:03', 1, 13, '성동구', '평균 이상이에요', '2023-09-22T02:03:03');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('49', '2', 17, '2023-09-20T02:02:02', 0, 0, '도봉구', '최악이었어요', '2023-09-21T01:02:02');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('50', '2', 18, '2023-09-19T01:01:01', 1, 23, '서대문구', '보통이에요', '2023-09-20T00:01:01');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('51', '2', 19, '2023-09-18T00:00:00', 0, 0, '용산구', '추천드려요!', '2023-09-19T23:00:00');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('52', '2', 10, '2023-09-17T23:23:23', 1, 22, '동대문구', '매우 좋았어요!', '2023-09-18T22:23:23');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('53', '2', 8, '2023-09-16T22:22:22', 0, 0, '종로구', '보통이에요', '2023-09-17T21:22:22');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('54', '2', 6, '2023-09-15T21:21:21', 1, 14, '강동구', '평균 이상이에요', '2023-09-16T20:21:21');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('55', '2', 4, '2023-09-14T20:20:20', 0, 0, '노원구', '다시 오고 싶어요!', '2023-09-15T19:20:20');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('56', '2', 2, '2023-09-13T19:19:19', 1, 6, '양천구', '좋았습니다!', '2023-09-14T18:19:19');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('57', '2', 9, '2023-09-12T18:18:18', 0, 0, '서초구', '별로였어요', '2023-09-13T17:18:18');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('58', '2', 7, '2023-09-11T17:17:17', 1, 13, '광진구', '추천합니다!', '2023-09-12T16:17:17');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('59', '2', 5, '2023-09-10T16:16:16', 0, 0, '동작구', '평범해요', '2023-09-11T15:16:16');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('60', '2', 3, '2023-09-09T15:15:15', 1, 11, '은평구', '맛있어요!', '2023-09-10T14:15:15');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('61', '2', 1, '2023-09-08T14:14:14', 0, 0, '강서구', '기대 이하였어요', '2023-09-09T13:14:14');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('62', '2', 0, '2023-09-07T13:13:13', 1, 20, '송파구', '아주 좋았어요!', '2023-09-08T12:13:13');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('63', '2', 11, '2023-09-06T12:12:12', 0, 0, '마포구', '볼만해요', '2023-09-07T11:12:12');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('64', '2', 12, '2023-09-05T11:11:11', 1, 10, '관악구', '최고예요!', '2023-09-06T10:11:11');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('65', '2', 13, '2023-09-04T10:10:10', 0, 0, '용산구', '그럭저럭이에요', '2023-09-05T09:10:10');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('66', '2', 14, '2023-09-03T09:09:09', 1, 30, '강남구', '훌륭합니다!', '2023-09-04T08:09:09');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('67', '2', 15, '2023-09-02T08:08:08', 0, 0, '성동구', '다시는 안 가고 싶어요', '2023-09-03T07:08:08');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('68', '2', 16, '2023-09-01T07:07:07', 1, 17, '성북구', '최악이었어요', '2023-09-02T06:07:07');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('69', '2', 17, '2023-08-31T06:06:06', 0, 0, '구로구', '추천드려요!', '2023-09-01T05:06:06');
INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('70', '2', 18, '2023-08-30T05:05:05', 1, 21, '금천구', '별로 추천하고 싶지 않아요', '2023-08-31T04:05:05');
