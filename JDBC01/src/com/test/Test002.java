/*=============================================
   Test002.java
   - main() 메소드를 포함하는 테스트 클래스
   - 데이터베이스 연결
   - 데이터 입력
=============================================*/

package com.test;

import java.sql.Connection;
import java.sql.Statement;

import com.util.DBConn;

public class Test002
{

	public static void main(String[] args)
	{
		Connection conn = DBConn.getConnection();     
		
		if (conn == null)
		{
			System.out.println("데이터베이스 연결 실패~!!!");
			System.exit(0);
		}
		
		// System.out.println("데이터베이스 연결 성공~!!!");
		
		try
		{
			// 작업 객체 준비		// 수레.. 끈에 달려있거나 레일 위에 있는..
			Statement stmt = conn.createStatement();	// 와이어에 수레를 매다는 작업
			
			//  데이터 입력 쿼리 실행 과정
			//  한 번 실행하면 다시 실행하지 못하는 상황이다.
			//  TBL_MEMBER 테이블에는 기본키 제약조건이 설정되어 있으므로
			//  동일한 키 값이 중복될 수 없기 때문이다. 
			
			// 쿼리문 준비 
			String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(2, '김서현', '010-2222-2222')";
			//-- 주의. 쿼리문 끝에 『;』 붙이지 않는다.
			//-- 주의. 자바에서 실행한 DML 구문은 내부적으로 자동 commit 된다.
			//-- 주의. 오라클에서 트랜잭션 처리가 끝나지 않으면 
			//         데이터 액션 처리가 이루어지지 않는다. -- 즉 오라클에서 커밋 안하고 자바와서 수행하면 에러난다.
			
			int result = stmt.executeUpdate(sql); // 적용된 커서 개수를 반환(정수)
			
			if (result > 0)
			{
				System.out.println("데이터 입력 성공~!!!");
			}
			else
			{
				System.out.println("데이터 입력 실패ㅠ_ㅠ");
			}
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		DBConn.close();
	}

}
