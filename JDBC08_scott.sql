SELECT USER
FROM DUAL;
--==>> SCOTT


SELECT *
FROM TBL_MEMBER
ORDER BY SID;
--==>> 
/*
2	전혜림	010-2222-2222
3	장서현	010-3333-3333
4	이새롬	010-4444-4444
5	정주희	010-5555-5555
6	이희주	010-6666-6666
7	심혜진	010-7777-7777
*/



--○ CallableStatement 실습을 위한 프로시저 작성
-- 프로시저 명 : PRC_MEMVERINSERT
-- 프로시저 기능 : TBL_MEMBER 테이블에 데이터를 입력하는 프로시저

CREATE OR REPLACE PROCEDURE PRC_MEMBERINSERT
( VNAME IN TBL_MEMBER.NAME%TYPE
, VTEL  IN TBL_MEMBER.TEL%TYPE
)
IS
    VSID    TBL_MEMBER.SID%TYPE;
BEGIN
    -- 기존 SID 의 최대값 얻어오기
    SELECT NVL(MAX(SID), 0) INTO  VSID
    FROM TBL_MEMBER;
    
    -- 데이터 입력
    INSERT INTO TBL_MEMBER(SID, NAME, TEL)
    VALUES((VSID+1), VNAME, VTEL);
    
    -- 커밋
    COMMIT;
END;
--==>> Procedure PRC_MEMBERINSERT이(가) 컴파일되었습니다.


--○ 데이터 삽입
EXEC PRC_MEMBERINSERT('이상화', '010-1234-3434');


--○ 확인
SELECT *
FROM TBL_MEMBER
ORDER BY SID;



--○ CallableStatement 실습을 위한 프로시저 작성
--   프로시저 명 : PRC_MEMBERSELECT
--   프로시저 기능 : TBL_MEMBER 테이블의 데이터를 읽어오는 기능의 프로시저
--   ※ 『SYS_REFCURSOR』 자료형을 이용하여 커서 다루기


CREATE OR REPLACE PROCEDURE PRC_MEMBERSELECT
( VRESULT   OUT SYS_REFCURSOR   -- 커서 결과값을 VRESULT 안에 담아라??
    -- 외부 커서를 VRESULT라는 출력 매개변수안에 담아서..
)
IS

BEGIN
    OPEN VRESULT FOR
        SELECT SID, NAME, TEL
        FROM TBL_MEMBER
        ORDER BY SID;
        -- 이 형태에서는 CLOSE 하면 안됨..(CLOSE VRESULT)
        --  꺼내 쓸 수 없음..  
END;
--==>> Procedure PRC_MEMBERSELECT이(가) 컴파일되었습니다.


DROP PROCEDURE PRC_MEMBERSELCT;
