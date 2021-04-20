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

public class MemberDAO_trash
{
	// 주요 변수 선언 → DB 연결 객체
	private Connection conn;

	// 생성자 정의
	public MemberDAO(Connection conn) throws ClassNotFoundException, SQLException
	{
		conn = DBConn.getConnection();
	}
	
	// 메소드 정의 → 데이터를 입력하는 기능 → insert
	public int add(MemberDTO dto) throws SQLException	// excuteUpdate 써서 int로 반환하니까..
	{

		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = String.format("INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(MEMBERSEQ.NEXTVAL, '%s', '%s')"
									 , dto.getName(), dto.getTel());
		
		result = stmt.executeUpdate(sql);
		return result;
	}

	// 메소드 정의 → 전체 인원수 확인 기능 → select (count)
	public int count() throws SQLException
	{ 
		int result = 0;
		Statement stmt = conn.createStatement();
		
		String sql = "SELECT COUNT(*) AS COUNT  FROM TBL_MEMBER";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next())
		{
			result = rs.getInt("COUNT");
		}
		rs.close();
		stmt.close();
		return result;
	}
	
	
	// 메소드 정의 → 전체 리스트 조회 기능 → select
	public ArrayList<MemberDTO> lists() throws SQLException  // MemberDTO..즉 레코드'들'을 반환 하니까..
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();

		String sql = String.format("SELECT SID, NAME, TEL FROM TBL_MEMBER");
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next())
		{
			MemberDTO dto = new MemberDTO();
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setTel(rs.getString("TEL"));
			
			result.add(dto);
		}
		rs.close();
		stmt.close();
		
		return result;
	}
	
	public void close() throws SQLException 
	{
		DBConn.close();
	}
}
