INSERT INTO mood (id, name)
VALUES ('1', '베지테리언');
INSERT INTO mood (id, name)
VALUES ('2', '베지테리언2');
INSERT INTO mood (id, name) VALUES ('3', '무드1');
INSERT INTO mood (id, name) VALUES ('4', '무드2');
INSERT INTO mood (id, name) VALUES ('5', '무드3');
INSERT INTO mood (id, name) VALUES ('6', '무드4');

INSERT INTO image (id, url)
VALUES ('1', 'https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png');

INSERT INTO member (id, email, nickname, password, mood_id)
VALUES ('1', 'ati@ati.com', '아티', 'ati123!', '1');
INSERT INTO member (id, email, nickname, password, mood_id)
VALUES ('2', 'puban@puban.com', '푸반', 'puban123!', '1');
