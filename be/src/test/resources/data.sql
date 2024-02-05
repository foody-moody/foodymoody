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

INSERT INTO store_status(code, type)
VALUES (1, 'OPEN'),
       (2, 'TEMPORARILY_CLOSED'),
       (3, 'CLOSED'),
       (4, 'ETC');

INSERT INTO image (id, url, deleted)
VALUES ('1', 'https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png1', 0),
       ('2', 'https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png2', 0),
       ('3', 'https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png3', 0),
       ('4', 'https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png4', 0),
       ('member-profile-default', 'https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png1', 0);

INSERT INTO store (id, name, road_address, address, phone, x, y, status)
VALUES ('1', '영업중 식당', '도로명주소1', '지번주소1', '전화번호1', 0, 0, 1),
       ('2', '폐업한 식당', '도로명주소2', '지번주소2', '전화번호2', 1, 1, 3),
       ('3', '송파구', '도로명주소2', '지번주소2', '전화번호2', 1, 1, 1),
       ('4', '송파구식당', '도로명주소2', '지번주소2', '전화번호2', 1, 1, 1),
       ('5', '식당송파구', '도로명주소2', '지번주소2', '전화번호2', 1, 1, 1),
       ('6', '식당 송파구 식당', '도로명주소2', '지번주소2', '전화번호2', 1, 1, 1),
       ('7', '식당송파구식당', '도로명주소2', '지번주소2', '전화번호2', 1, 1, 1),
       ('8', '송파구송파구', '서울특별시 송파구 어딘가12로 1-1', '지번주소2', '전화번호2', 1, 1, 1),
       ('9', '식당3', '도로명주소2', '송파구 문정동 1-1', '전화번호2', 1, 1, 1),
       ('10', '식당4', '도로명주소, (송파구)', '지번주소2', '전화번호2', 1, 1, 1),
       ('11', '식당5', '도로명주소, (송파구)', '지번주소2', '전화번호2', 1, 1, 1),
       ('12', '식당6', '도로명주소, (송파구)', '지번주소2', '전화번호2', 1, 1, 1),
       ('13', '식당7', '도로명주소, (송파구)', '지번주소2', '전화번호2', 1, 1, 1),
       ('14', '식당8', '도로명주소, (송파구)', '지번주소2', '전화번호2', 1, 1, 1),
       ('15', '식당9', '도로명주소, (송파구)', '지번주소2', '전화번호2', 1, 1, 1),
       ('16', '식당10', '도로명주소, (송파구)', '지번주소2', '전화번호2', 1, 1, 1),
       ('17', '식당11', '도로명주소, (송파구)', '지번주소2', '전화번호2', 1, 1, 1),
       ('18', '식당12', '도로명주소, (송파구)', '지번주소2', '전화번호2', 1, 1, 1);

