INSERT INTO mood (id, name)
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

INSERT INTO member (id, email, nickname, password, mood_id, profile_image_id)
VALUES ('1', 'ati@ati.com', '아티', 'ati123!', '1', '1'),
       ('2', 'puban@puban.com', '푸반', 'puban123!', '1', '2');

