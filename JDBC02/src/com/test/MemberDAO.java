/*=====================
   MemberDTO.java
 ======================*/

// Database 에 Access 하는 기능
// → DBConn 활용

// 데이터를 입력하는 기능 → insert

// 인원 수를 확인하는 기능
// → 대상 테이블(TBL_MEMBER)의 레코드 카운팅 기능 → select

// 전체 리스트 조회하는 기능
// → 대상 테이블(TBL_MEMBER)의 데이터를 조회하는 기능 → select


package com.test;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class MemberDAO
{
	// 주요 변수 선언 → DB 연결 객체
	private Connection conn;

	// 생성자 정의
	public MemberDAO() throws ClassNotFoundException, SQLException 
	{
		conn = DBConn.getConnection();
	}

	// 메소드 정의 → 데이터를 입력하는 기능 → select(insert)
	public int add(MemberDTO dto) throws SQLException 	// excuteUpdate 써서 int로 반환하니까..
	{
		// 반환할 결과값을 담아낼 변수(적용된 행의 개수)
		int result = 0;
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		/*
		// ※ Statement 의 종류
		 * - Statement										일회용품
		 * : 하나의 쿼리를 사용하고 나면 더이상 사용할 수 없다. But 이때문에 보안 취약..		 *
		 * - PreparedStatement	Statement 객체의 자식		락앤락
		 * : 하나의 PreparedStatement 로 쿼리를 여러 번 처리할 수 있다.
		 * - CallableStatement	PreparedStatement 객체의 자식
		 * : 데이터베이스 내의 스토어드 프로시저나 함수 등을 호출할 수 있다.
		
		※ Statement 의 의미
		  자바에서 사용되는 3가지 종류의 작업 객체들은
		  데이터베이스로 쿼리를 담아서 보내는 그릇 정도로 생각하자.
		  즉, 작업 객체에 쿼리를 실어 데이터베이스로 보내버리면
		  그 내용이 데이터베이스에서 처리되는 것이다.
		  이 때, 한 번 사용하고 버리는 그릇은 Statement 이며,
		  재사용이 가능한 그릇은 PreparedStatement 이다.
		 */
		
		// 쿼리문 준비
		String sql = String.format("INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(MEMBERSEQ.NEXTVAL, '%s', '%s')"
								 , dto.getName(), dto.getTel());
		
		// 작업 객체를 활용하여 쿼리문 전달(실행) → 적용된 행의 개수 반환
		result = stmt.executeUpdate(sql);
		
		// 리소스 반납 ~일회용이니까ㅎ
		stmt.close();
		
		// 최종 결과값 반환
		return result;
	}

	
	// 메소드 정의 → 전체 인원수 확인 기능 → select (count)
	public int count() throws SQLException 
	{ 
		// 결과값으로 반환하게 된 변수 선언 및 초기화
		int result = 0;
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비 → select
		String sql = "SELECT COUNT(*) AS COUNT  FROM TBL_MEMBER";
		
		// 쿼리문 실행
		ResultSet rs = stmt.executeQuery(sql);

		// ResultSet 처리 → 반복문 구성 → 결과값 수신
		while (rs.next())		// if (rs.next())
		{
			result = rs.getInt("COUNT");		// while이라도 한 번만 수행.. 여러 레코드가 아니니까
		}										// rs.getInt(1) 컬럼 인덱스는 1부터다!!!!
		
		// 리소스 반납
		rs.close();
		stmt.close();
		
		// 최종 결과값 반환
		
		return result;

	}
	
	
	// 메소드 정의 → 전체 리스트 조회 기능 → select
	public ArrayList<MemberDTO> lists() throws SQLException  // MemberDTO..즉 레코드'들'을 반환 하니까..
	{
		// 결과값으로 반환할 변수 선언 및 초기화
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비 → select
		String sql = "SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID";
		
		// 쿼리문 실행 → select → executeQuery() → ResultSet 반환
		ResultSet rs = stmt.executeQuery(sql);
		
		// ResultSet 처리 → 반복문 활용 → MemberDTO 인스턴스 생성 → 속성 구성 → ArrayList 적재
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setTel(rs.getString("TEL"));
			
			result.add(dto);
		}
		
		// 리소스 반납
		rs.close();
		stmt.close();
		
		// 최종 결과값 반환
		return result;
	}
	

	

}

/*
 	public int count() 
	{ 
		int result = 0;
		
		return result;

	}
	이렇게 return의 구조를 미리 만들어 놓을 것!
 * */
