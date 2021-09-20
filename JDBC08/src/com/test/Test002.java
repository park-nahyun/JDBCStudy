/*===================================================
   Test001.java
   - CallableStatement 를 활용한 SQL 구문 전송 실습 2
=====================================================*/


package com.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import com.util.DBConn;

import oracle.jdbc.internal.OracleTypes;

public class Test002 {
	public static void main(String[] args) {
		try {
			Connection conn = DBConn.getConnection();
			
			if (conn != null)
			{
				System.out.println("데이터베이스 연결 성공~!!!");
				
				try {
					String sql = "{call PRC_MEMBERSELECT(?)}";
					CallableStatement cstmt = conn.prepareCall(sql);
					
					//check~!!!
					// 프로시저 내부에서 SYS_REFCURSOR 를 사용하고 있기 때문에
					// OracleTypes.CURSOR 를 사용하기 위한 등록 과정이 필요한 상황.
					// 1. Project Exploler 상에서 해당 프로젝트 마우스 우클릭
					//	>Build Path > Configure Build Path → Java Build Path 대화창 호출
					// 2. Java Build Path 대화창에서 Libraries 탭 선택
				// // 3. Add External JARs... 버튼 클릭
	               //    > 『ojdbc6.jar』 또는 『ojdbc14.jar』 파일 추가 등록
	               //    (외부 jar 파일 연결)
				   // 4. 『import oracle.jdbc.OracleTypes』 구문 추가
					
					// check~!!!
					cstmt.registerOutParameter(1, OracleTypes.CURSOR);
					cstmt.execute();
					ResultSet rs = (ResultSet)cstmt.getObject(1);
					
					while (rs.next()) 
					{
						String sid = rs.getString("SID");
						String name = rs.getString("NAME");
						String tel = rs.getString("TEL");
						
						String str = String.format("%3s %7s %10s", sid, name, tel);
						
						System.out.println(str);
					
					}
					rs.close();
					cstmt.close();
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			} // end if (dbConn != null)
			
			System.out.println();
			System.out.println("데이터베이스 연결 닫힘~!");
			System.out.println("프로그램 종료");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}

/*
 * 데이터베이스 연결 성공~!!!
  2     전혜림 010-2222-2222
  3     장서현 010-3333-3333
  4     이새롬 010-4444-4444
  5     정주희 010-5555-5555
  6     이희주 010-6666-6666
  7     심혜진 010-7777-7777
  8     이상화 010-1234-3434
  9     안정미 010-9797-6565
 10     이하림 010-9898-4646

데이터베이스 연결 닫힘~!
프로그램 종료

 */
