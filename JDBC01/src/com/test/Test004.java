/*=============================================
   Test004.java
   - 테이블 내의 데이터 읽어오기
=============================================*/

package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.util.DBConn;

public class Test004
{
	public static void main(String[] args)
	{
		Connection conn = DBConn.getConnection();
		
		if (conn != null)
		{
			System.out.println("데이터베이스 연결 성공~!!!");
			
			try
			{
				// 작업 객체 생성(쿼리문 담을 만한~)
				Statement stmt = conn.createStatement();	// sql 예외 던지고 statement 자료형을 반환한다. 아이콘이 A면 추상..
				
				// 쿼리문 준비
				// String sql = "SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY 1";
				String sql = "SELECT SID, NAME, TEL"	// 공백 신경 쓰자~ 공백은 웬만하면 앞에다 붙여줘야 보기 편함.
						   + " FROM TBL_MEMBER"
						   + " ORDER BY 1";
				// ※ 쿼리문 구성 간 공백 처리 check~!!!
				
	
				// 작업객체는 execute쿼리/excute업데이트만 일단 제대로 알아두자.
				// 오라클에서 뭔가가 바뀌는 거면 업데이트.. 변화하는게 없다면 execute
				// executeQuery() 메소드를 사용하면
				// 질의 결과를 ResultSet 객체로 가져올 수 있다.
				// 하지만, ResultSet 객체가 질의에 대한 결과물 모두를
				// 한꺼번에 갖고 있는 구조가 아니다.
				// 단지, 데이터베이스로부터 획득한 질의 결과물에대한
				// 관리가 가능한 상태가 되는 것이다.
				// 이 때문에 데이터베이스와의 연결을 끊게 되면
				// ResultSet 객체는 더이상 질의 결과를 관리할 수 없게 된다.
				ResultSet rs = stmt.executeQuery(sql);
				
				// ResultSet 에 대한 처리 → 반복문 구성
				// 가져올 값이 있는지 확인하는 메소드 『next()』
				while (rs.next())	
				{
					// 레코드에서 결과값을 가져오는 메소드 『getter』
					String sid = rs.getString("SID");		// 위의 셀렉트문에서 별칭을 썼으면 여기서도 별칭써야함
					String name = rs.getString("NAME");		// rs.getString(1); 인덱스로 해도됨.. 첫번째 컬럼~!
					String tel = rs.getString("TEL");
					
					String str = String.format("%3s %8s %12s", sid, name, tel);
					System.out.println(str);
				}
				rs.close();
				stmt.close();
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
			
		}// end if
		
		DBConn.close();
		
		System.out.println("데이터베이스 연결 닫힘~!!!");
		System.out.println("프로그램 종료됨~!!!");
		
	}

}


// 실행 결과
/*
 * 데이터베이스 연결 성공~!!!
  1      김가영 010-1111-1111
  2      김서현 010-2222-2222
  3      박정준 010-3333-3333
  4      이유림 010-4444-4444
데이터베이스 연결 닫힘~!!!
프로그램 종료됨~!!!
 * */


