package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.util.DBConn;

public class ScoreDAO {


	private Connection conn;
	
	// DB 연결 메소드
	public Connection connection() throws ClassNotFoundException, SQLException
	{
		conn = DBConn.getConnection();
		return conn;
	}
	// DB 연결 종료 메소드
	public void close() throws SQLException
	{
		DBConn.close();
	}
	// 데이터 입력 전용 메소드
	public int add(ScoreDTO dto) throws SQLException, ClassNotFoundException
	{
		int result = 0;
		String sql = "INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT)"
				+ " VALUES(MEMBERSEQ.NEXTVAL, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, dto.getName());
		pstmt.setInt(2, dto.getKor());
		pstmt.setInt(3, dto.getEng());
		pstmt.setInt(4, dto.getMat());
		
		result = pstmt.executeUpdate();
		
		if (result > 0)
			System.out.println("데이터 입력 성공~!!!");
		
		pstmt.close();
		DBConn.close();
		return result;
	}
	
	
	public int count() throws SQLException
	{
		int result = 0;
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_SCORE";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next())
		{
			result = rs.getInt("COUNT");
		}
		rs.close();
		pstmt.close();
		return result;
	}
	
	
	public ArrayList<ScoreDTO> lists() throws SQLException, ClassNotFoundException
	{
		
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
	    String sql = "SELECT SID, NAME, KOR, ENG, MAT"
	    		+ ", KOR+ENG+MAT AS TOT"
	    		+ ", (KOR+ENG+MAT)/3.0 AS AVG "
	    		+ ", RANK() OVER(ORDER BY KOR+ENG+MAT) AS RANK"
	    		+ " FROM TBL_SCORE";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	   
	    
	    ResultSet rs = pstmt.executeQuery();
	    
	    while (rs.next()) 
	    {
	    	ScoreDTO dto = new ScoreDTO();
	    	dto.setSid(rs.getString("SID"));
	    	dto.setName(rs.getString("NAME"));
	    	dto.setKor(rs.getInt("KOR"));
	    	dto.setEng(rs.getInt("ENG"));
	    	dto.setMat(rs.getInt("MAT"));
	    	dto.setTot(rs.getInt("TOT"));
	    	dto.setAvg(rs.getInt("AVG"));
	    	dto.setRank(rs.getInt("RANK"));
	    	result.add(dto);
	    }
	    
	    rs.close();
	    pstmt.close();
	    DBConn.close();
	    return result;
	    		
	}
	
	
	public ArrayList<ScoreDTO> lists(String name) throws SQLException, ClassNotFoundException
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
	
		
		String sql = "SELECT SID, NAME, KOR, ENG, MAT"
	    		+ ", KOR+ENG+MAT AS TOT"
	    		+ ", (KOR+ENG+MAT)/3.0 AS AVG "
	    		+ ", RANK() OVER(ORDER BY KOR+ENG+MAT) AS RANK"
	    		+ "FROM TBL_SCORE"
	    		+ "WHERE NAME = '?'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		
	   while (rs.next()) 
	    {
	    	ScoreDTO dto = new ScoreDTO();
	    	dto.setSid(rs.getString("SID"));
	    	dto.setName(rs.getString("NAME"));
	    	dto.setKor(rs.getInt("KOR"));
	    	dto.setEng(rs.getInt("ENG"));
	    	dto.setMat(rs.getInt("MAT"));
	    	dto.setTot(rs.getInt("TOT"));
	    	dto.setAvg(rs.getInt("AVG"));
	    	dto.setRank(rs.getInt("RANK"));
	    	result.add(dto);
	    }
	    
	    rs.close();
	    pstmt.close();
	    DBConn.close();
	    return result;
	}
	
	
	public ArrayList<ScoreDTO> lists(int sid) throws SQLException, ClassNotFoundException
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
	
		
		String sql = "SELECT SID, NAME, KOR, ENG, MAT"
	    		+ ", KOR+ENG+MAT AS TOT"
	    		+ ", (KOR+ENG+MAT)/3.0 AS AVG "
	    		+ ", RANK OVER(ORDER BY KOR+ENG+MAT) AS RANK"
	    		+ "FROM TBL_SCORE"
	    		+ "WHERE SID = '?'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, sid);
		ResultSet rs = pstmt.executeQuery();
		
	   while (rs.next()) 
	    {
	    	ScoreDTO dto = new ScoreDTO();
	    	dto.setSid(rs.getString("SID"));
	    	dto.setName(rs.getString("NAME"));
	    	dto.setKor(rs.getInt("KOR"));
	    	dto.setEng(rs.getInt("ENG"));
	    	dto.setMat(rs.getInt("MAT"));
	    	dto.setTot(rs.getInt("TOT"));
	    	dto.setAvg(rs.getInt("AVG"));
	    	dto.setRank(rs.getInt("RANK"));
	    	result.add(dto);
	    }
	    
	    rs.close();
	    pstmt.close();
	    DBConn.close();
	    return result;
	}
	
	
	// 데이터 수정 메소드
	public int modify(ScoreDTO dto) throws ClassNotFoundException, SQLException
	{
		int result = 0;
		String sql = "UPDATE TBL_SCORE"
				+ "SET NAME = '?',KOR = '?', ENG = '?', MAT = '?'"
				+ "WHERE SID = '?'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, dto.getName());
		pstmt.setInt(2, dto.getKor());
		pstmt.setInt(3, dto.getEng());
		pstmt.setInt(4, dto.getMat());
		
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}
	
	// 데이터 삭제 메소드
	
	public int remove(int sid) throws SQLException, ClassNotFoundException
	{
		int result = 0;
		
		String sql = "DELETE * FROM TBL_SCORE"
				+ "WHERE SID = '?'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, sid);
		
		return result;
	}
}
