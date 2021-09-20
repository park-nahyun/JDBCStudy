SELECT USER
FROM DUAL;
--==>> SCOTT

TRUNCATE TABLE TBL_SCORE;
--==>> Table TBL_SCORE��(��) �߷Ƚ��ϴ�.


DROP SEQUENCE SCORESEQ;
--==>> Sequence SCORESEQ��(��) �����Ǿ����ϴ�.


--�� ������ ����
CREATE SEQUENCE SCORESEQ
NOCACHE;
--==>> Sequence SCORESEQ��(��) �����Ǿ����ϴ�.


--�� ������ �غ�

--1. ������ �Է� ������ ����
INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT)
VALUES(SCORESEQ.NEXTVAL, 'ȫ�浿', 90, 80, 70);
--> �� �� ����
INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT) VALUES(SCORESEQ.NEXTVAL, 'ȫ�浿', 90, 80, 70)
;
--==>> 1 �� ��(��) ���ԵǾ����ϴ�.


--2. ����Ʈ ��� ����
SELECT SID, NAME, KOR, ENG, MAT
     , KOR+ENG+MAT AS TOT
     , (KOR+ENG+MAT)/3.0 AS AVG
     , RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK
FROM TBL_SCORE
ORDER BY SID ASC;


SELECT SID, NAME, KOR, ENG, MAT , KOR+ENG+MAT AS TOT, (KOR+ENG+MAT)/3.0 AS AVG, RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK FROM TBL_SCORE ORDER BY SID ASC
;
--==>> 1	ȫ�浿	90	80	70	240	80	1



-- 3. �ο� �� ��ȸ ������ ����
SELECT COUNT(*) AS COUNT
FROM TBL_SCORE;
--> �� �� ����
SELECT COUNT(*) AS COUNT FROM TBL_SCORE
;
--==>>


--4. �̸� �˻� ������ ����
SELECT *
FROM(
    SELECT SID, NAME, KOR, ENG, MAT
         , KOR+ENG+MAT AS TOT
         , (KOR+ENG+MAT)/3.0 AS AVG
         , RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK
    FROM TBL_SCORE
    )
WHERE NAME = 'ȫ�浿';
--> �������� �Ⱦ��� �׳� WHERE �ϸ� WHERE ���� �ɸ� ����� ������ ���ͼ� ���� 1��
--> ���� ���� ��� ��ü ���� ���� ������ �״��� �ű⼭ ȫ�浿�� ��� ã�� ��
--> ���� ����
SELECT * FROM(SELECT SID, NAME, KOR, ENG, MAT, KOR+ENG+MAT AS TOT, (KOR+ENG+MAT)/3.0 AS AVG, RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK FROM TBL_SCORE) WHERE NAME = 'ȫ�浿'
;
--==>> 1	ȫ�浿	90	80	70	240	80	1


--5. ��ȣ �˻� ������ ����
SELECT *
FROM(
    SELECT SID, NAME, KOR, ENG, MAT
         , KOR+ENG+MAT AS TOT
         , (KOR+ENG+MAT)/3.0 AS AVG
         , RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK
    FROM TBL_SCORE
    )
WHERE SID = 1;
--=> �� �� ����
SELECT * FROM(SELECT SID, NAME, KOR, ENG, MAT, KOR+ENG+MAT AS TOT, (KOR+ENG+MAT)/3.0 AS AVG, RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK FROM TBL_SCORE) WHERE SID = 1
;


--6. ������ ���� ������ ����
UPDATE TBL_SCORE
SET NAME='��浿', KOR=10, ENG=10, MAT=10
WHERE SID = 1;
--> �� �� ����
UPDATE TBL_SCORE SET NAME='��浿', KOR=10, ENG=10, MAT=10 WHERE SID = 1
;
--==>> 1 �� ��(��) ������Ʈ�Ǿ����ϴ�.

COMMIT;
--==>> Ŀ�� �Ϸ�.


SELECT *
FROM TBL_SCORE;


-- 7. ������ ���� ������ ����
DELETE
FROM TBL_SCORE
WHERE SID = 1;
--> �� �� ����
DELETE FROM TBL_SCORE WHERE SID = 1
;
--==>> 1 �� ��(��) �����Ǿ����ϴ�.

COMMIT;
--==>> Ŀ�� �Ϸ�.
