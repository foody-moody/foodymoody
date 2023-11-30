INSERT INTO store_mood (id, name)
VALUES ('1', '가족과 함께'),
       ('2', '혼밥'),
       ('3', '감성'),
       ('4', '데이트'),
       ('5', '루프탑'),
       ('6', '특별한 테마');

INSERT INTO taste_mood (id, name)
VALUES ('1', '베지테리언'),
       ('2', '베지테리언2'),
       ('3', '무드1'),
       ('4', '무드2'),
       ('5', '무드3'),
       ('6', '무드4');

INSERT INTO image (id, url)
VALUES ('1', 'https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png1'),
       ('2', 'https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png2'),
       ('3', 'https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png3'),
       ('4', 'https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png4');

INSERT INTO member (id, email, nickname, password, taste_mood_id, profile_image_id)
VALUES ('1', 'ati@ati.com', '아티', 'ati123!', '1', '1'),
       ('2', 'puban@puban.com', '푸반', 'puban123!', '1', '2');

INSERT INTO feed (id, member_id, comment_count, created_at, is_liked, like_count, location, review, updated_at)
VALUES ('1c', '2', 0, '2023-11-07T11:11:11', 1, 10, '역삼동', '맛있어요!', '2023-11-08T14:11:11'),
       ('2c', '2', 14, '2023-09-03T09:09:09', 1, 30, '강남구', '훌륭합니다!', '2023-09-04T08:09:09'),
       ('3c', '2', 15, '2023-09-02T08:08:08', 0, 0, '성동구', '다시는 안 가고 싶어요', '2023-09-03T07:08:08');

INSERT INTO image_menu (id, image_id, menu_id, display_order)
VALUES ('1a', '2', '1a', 0),
       ('2a', '3', '3a', 1),
       ('3a', '1', '2a', 2),
       ('4a', '3', '1a', 1),
       ('5a', '1', '5a', 0),
       ('6a', '4', '4a', 1),
       ('7a', '2', '1a', 2),
       ('8a', '3', '3a', 0);

INSERT INTO menu (id, name, rating)
VALUES ('1a', '2', 3),
       ('2a', '3', 4),
       ('3a', '1', 5),
       ('4a', '3', 3),
       ('5a', '2', 4);

INSERT INTO feed_image_menus_list (feed_id, image_menus_list_id)
values ('1c', '1a'),
       ('1c', '2a'),
       ('1c', '3a'),
       ('2c', '4a'),
       ('2c', '5a'),
       ('3c', '6a'),
       ('3c', '7a'),
       ('3c', '8a');

INSERT INTO feed_store_mood_ids (feed_id, store_mood_ids)
values ('1c', '1'),
       ('2c', '2'),
       ('3c', '3');