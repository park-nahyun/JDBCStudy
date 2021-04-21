/*==============================
 	ScoreDTO.java
 ==============================*/


package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class ScoreDAO
{
	private Connection conn;
	
	// 데이터베이스 연결 담당 메소드
	public Connection connection() throws ClassNotFoundException, SQLException
	{
		conn = DBConn.getConnection();
		return conn;
	}
	
	// 데이터 입력 담당 메소드
	public int add(ScoreDTO dto) throws SQLException
	{
		int result = 0;
		
		// 작업 객체
		Statement stmt = conn.createStatement();
		
		// 쿼리문
		String sql = String.format("INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT)"
								+ " VALUES(SCORESEQ.NEXTVAL, '%s', %d, %d, %d)"
								  , dto.getName(), dto.getKor(), dto.getEng(), dto.getMat());
		// 작업 객체에 쿼리문 전달
		result = stmt.executeUpdate(sql);
		stmt.close();
		
		return result;
		// ctrl + shift + f 개행 맞춰줌!!
	}
	
	// 전체 리스트 출력 담당 메소드
	public ArrayList<ScoreDTO> lists() throws SQLException
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		Statement stmt = conn.createStatement();
		
		String sql = String.format("SELECT SID, NAME, KOR, ENG, MAT"
								 + ", KOR+ENG+MAT AS TOT, (KOR+ENG+MAT)/3.0 AS AVG"
								 + ", RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK"
								 				   + " FROM TBL_SCORE "
								 				   + " ORDER BY SID ASC");
		
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			ScoreDTO dto = new ScoreDTO();
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			dto.setTot(rs.getInt("TOT"));
			dto.setAvg(rs.getDouble("AVG"));
			dto.setRank(rs.getInt("RANK"));
			
			result.add(dto);
		}
		stmt.close();
		return result;
	}
	
	
	// 이름 검색 담당 메소드
	public ArrayList<ScoreDTO> lists(String name) throws SQLException	// 오버로딩
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		Statement stmt = conn.createStatement();
		
		String sql = String.format("SELECT *"
								 + " FROM(SELECT SID, NAME, KOR, ENG, MAT"
								 + ", KOR+ENG+MAT AS TOT"
								 + ", (KOR+ENG+MAT)/3.0 AS AVG"
								 + ", RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK"
								 + " FROM TBL_SCORE)"
								 + " WHERE NAME = '%s'", name);
		
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{		
			ScoreDTO dto = new ScoreDTO();
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			dto.setTot(rs.getInt("TOT"));
			dto.setAvg(rs.getDouble("AVG"));
			dto.setRank(rs.getInt("RANK"));
		
			result.add(dto);
		}
		
		stmt.close();
		return result;
	}
	
	
	// 번호 검색 담당 메소드
	public ArrayList<ScoreDTO> lists(int sid) throws SQLException 
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		Statement stmt = conn.createStatement();
		
		String sql = String.format("SELECT *"
								 + " FROM(SELECT SID, NAME, KOR, ENG, MAT"
								 + ", KOR+ENG+MAT AS TOT"
								 + ", (KOR+ENG+MAT)/3.0 AS AVG"
								 + ", RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK"
								 + " FROM TBL_SCORE)"
								 + " WHERE SID = %d", sid);
		
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{		
			ScoreDTO dto = new ScoreDTO();
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			dto.setTot(rs.getInt("TOT"));
			dto.setAvg(rs.getDouble("AVG"));
			dto.setRank(rs.getInt("RANK"));
		
			result.add(dto);
		}
		
		stmt.close();
		return result;
		// 꼭 어레이 안써도 되지만 그냥 같이 오버로딩..
	}
	
	
	// 인원 수 확인 담당 메소드
	public int count() throws SQLException
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = String.format("SELECT COUNT(*) AS COUNT FROM TBL_SCORE");
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
			result = rs.getInt("COUNT");
		
		stmt.close();
		return result;
	}
	
	
	// 매개변수로 dto를 받아오니까 ScoreDTO = new ~안 해도 dto.get 가능..
	// 데이터 수정 담당 메소드
	public int modify(ScoreDTO dto) throws SQLException	// 번호가 아니라 dto. 번호는 수정할 대상을 찾을 때 하는 것. 그건 번호 담당 메소드가 한다..
	{								// 수정하기 전에 찾는 메소드가 아니라 수정하는 메소드!
		// 수정 = 어떤 데이터를 어떻게 바꿀 것인가..
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = String.format("UPDATE TBL_SCORE "
								 + "SET NAME='%s', KOR=%d, ENG=%d, MAT=%d"
								 + " WHERE SID = %s"
								 , dto.getName(), dto.getKor(), dto.getEng(), dto.getMat(), dto.getSid());
		result = stmt.executeUpdate(sql);
		stmt.close();
		
		return result;
	}
	
	
	// 데이터 삭제 담당 메소드
	public int remove(int sid) throws SQLException		// 삭제할 때는 누구다~하는 것만 있으면 됨. 찾기만 하면.. // void, String 다됨..ㅇ
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		String sql = String.format("DELETE FROM TBL_SCORE WHERE SID = %d", sid);
		result = stmt.executeUpdate(sql);
		stmt.close();
		
		return result;
	}
	
	
	// 데이터베이스 연결 종료 담당 메소드
	public void close() throws SQLException
	{
		DBConn.close();
	}
}
