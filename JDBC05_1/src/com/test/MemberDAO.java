package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class MemberDAO
{
	// 데이터베이스 연결 변수 및 메소드
	private Connection conn;
	
	public Connection connection()
	{
		conn = DBConn.getConnection();
		return conn;
	}
	
	// 1. 데이터 입력 담당 메소드
	public int add(MemberDTO dto) throws SQLException
	{
		int result = 0;
		
		// 작업 객체
		Statement stmt = conn.createStatement();
		
		// 쿼리문
		String sql = String.format("INSERT INTO TBL_EMP(EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID"
												   + ", TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG)"
								+ " VALUES (EMPSEQ.NEXTVAL, '%s', '%s'"
										+ ", TO_DATE('%s', 'YYYY-MM-DD')"
										+ ", (SELECT CITY_ID FROM TBL_CITY WHERE CITY_LOC = '%s')"
										+ ", '%s', (SELECT BUSEO_ID FROM TBL_BUSEO WHERE BUSEO_NAME = '%s')"
										+ ", (SELECT JIKWI_ID FROM TBL_JIKWI WHERE JIKWI_NAME = '%s'), %d, %d)"
										, dto.getName(), dto.getSsn(), dto.getIbsadate(), dto.getCity()
										, dto.getTel(), dto.getBuseo(), dto.getJikwi()
										, dto.getBasicpay(),dto.getSudang());
				
				
		// 작업 객체에 쿼리문 전달
		result = stmt.executeUpdate(sql);
		stmt.close();
		return result;
	}
	
	// 안내용 지역 출력 메소드
	public ArrayList<MemberDTO> cityList() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		
		String sql = "SELECT CITY_LOC FROM TBL_CITY";
		
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			dto.setCity(rs.getString("CITY_LOC"));
			result.add(dto);
		}
		
		stmt.close();
		return result;
	}
	
	// 안내용 부서 출력 메소드
	public ArrayList<MemberDTO> buseoList() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		
		String sql = "SELECT BUSEO_NAME FROM TBL_BUSEO";
		
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			dto.setBuseo(rs.getString("BUSEO_NAME"));
			result.add(dto);
		}
		
		stmt.close();
		return result;
	}
	
	// 안내용 직위 출력 메소드
	public ArrayList<MemberDTO> jikwiList() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		
		String sql = "SELECT JIKWI_NAME FROM TBL_JIKWI";
		
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			dto.setJikwi(rs.getString("JIKWI_NAME"));
			result.add(dto);
		}
		
		stmt.close();
		return result;
	}
	
	
	// 최소 기본급 조회 메소드
	public ArrayList<MemberDTO> jikwiList(MemberDTO dto) throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		
		String sql = String.format("SELECT MIN_BASICPAY FROM TBL_JIKWI WHERE JIKWI_NAME = '%s'", dto.getJikwi());
		
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto2 = new MemberDTO();
			dto.setJikwi(rs.getString("JIKWI_NAME"));
			result.add(dto2);
		}
		
		stmt.close();
		return result;
	}
	
	
	// 2. 전체 데이터 출력 메소드
	// 2-1. 사번정렬
	public ArrayList<MemberDTO> lists1() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		
		String sql = "SELECT * FROM EMPVIEW ORDER BY EMP_ID";
		
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			dto.setJikwi(rs.getString("JIKWI_NAME"));
			result.add(dto);
		}
		return result;
	}
	// 2-2. 이름 정렬
	public ArrayList<MemberDTO> lists2()
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		return result;
	}
	// 2-3. 부서 정렬
	public ArrayList<MemberDTO> lists3()
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		return result;
	}
	// 2-4. 직위 정렬
	public ArrayList<MemberDTO> lists4()
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		return result;
	}
	// 2-5. 급여 내림차순 정렬
	public ArrayList<MemberDTO> lists5()
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		return result;
	}
	
	// 인원 수 확인 담당 메소드
	public int count()
	{
		int result = 0;
		
		return result;
	}
	
	
	// 3. 직원 검색 출력 담당 메소드
	// 3-1. 사번 검색
	public ArrayList<MemberDTO> lists1(int sid)
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		return result;
	}
	// 3-2. 이름 검색
	public ArrayList<MemberDTO> lists2(String name)
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		return result;
	}
	// 3-3. 직위 검색
	public ArrayList<MemberDTO> lists3(String jikwi)
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		return result;
	}
	

	
	// 4. 데이터 수정 담당 메소드
	public int modify(MemberDTO dto)
	{
		int result = 0;
		
		return result;
	}
	
	// 5. 데이터 삭제 담당 메소드
	public int remove(int sid)
	{
		int result = 0;
		
		return result;
	}
	
	// 데이터베이스 연결 종료 메소드
	public void close()
	{
		DBConn.close();
	}
}
