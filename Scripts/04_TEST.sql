SELECT NAME, USERID, PWD, EMAIL, PHONE, ADMIN, JOINDATE
  FROM MEMBER;

INSERT INTO MEMBER(NAME, USERID, PWD, EMAIL, PHONE, ADMIN) VALUES('박규영', 'parkgy', '1234', 'pgy@gmail.com', '010-1111-2222', 0);

SELECT NAME, USERID, PWD, EMAIL, PHONE, ADMIN, JOINDATE FROM MEMBER WHERE USERID = 'parkgy';

UPDATE MEMBER SET NAME = '문채원', PWD = '5678', EMAIL = 'mcw@gmail.com', PHONE = '010-3333-5555', ADMIN = 1, JOINDATE = '2020-08-20' WHERE USERID = 'parkgy';

-- userCheck
SELECT PWD FROM MEMBER WHERE USERID = 'somi';

DELETE FROM MEMBER WHERE USERID = 'parkgy';

SELECT * FROM MEMBER;
