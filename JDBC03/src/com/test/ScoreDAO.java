package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class ScoreDAO
{
	// 주요 변수 선언 → DB 연결 객체
	public Connection conn;
	
	// 생성자 정의
	public ScoreDAO()
	{
		conn = DBConn.getConnection();
	}
	
	// 메소드 정의 → 데이터를 입력하는 기능 → select(insert)
	public int add(ScoreDTO dto) throws SQLException
	{
		int result = 0;
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = String.format("INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT) VALUES(SCORESEQ.NEXTVAL, '%s', %d, %d, %d)"
								  , dto.getName(), dto.getKor(), dto.getEng(), dto.getMat());
		
		// 작업 객체를 활용하여 쿼리문 전달(실행) → 적용된 행의 개수 반환						  
		result = stmt.executeUpdate(sql);
		
		stmt.close();
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
		String sql = "SELECT COUNT(*) AS COUNT  FROM TBL_SCORE";
		
		// 쿼리문 실행
		ResultSet rs = stmt.executeQuery(sql);

		// ResultSet 처리 → 반복문 구성 → 결과값 수신
		while (rs.next())	
		{
			result = rs.getInt("COUNT");		
		}										
		
		// 리소스 반납
		rs.close();
		stmt.close();
		
		// 최종 결과값 반환
		
		return result;

	}
	
	// 메소드 정의 → 전체 리스트 조회 기능 → select
	public ArrayList<ScoreDTO> lists() throws SQLException
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		Statement stmt = conn.createStatement();
		
		// 쿼리문
		String sql = "SELECT SID, NAME, KOR, ENG, MAT FROM TBL_SCORE";
				
		// 쿼리문 실행
		ResultSet rs = stmt.executeQuery(sql);
		
		// ResultSet 처리 → 반복문 활용 → ScoreDTO인스턴스 생성 → 속성 구성 → ArrayList 적재
		while (rs.next())
		{
			ScoreDTO dto = new ScoreDTO();
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			
			result.add(dto);
		}
				
		// 리소스 반납
		rs.close();
		stmt.close();
		
		// 최종 결과값 반환
		return result;
	}
}
