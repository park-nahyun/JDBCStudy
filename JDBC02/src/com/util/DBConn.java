/*=========================================
   DBConn.java
   - 예외 처리 방법을 try ~ catch → throws
 ==========================================*/
// 무조건 외우자


package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.lang.ClassNotFoundException;

public class DBConn
{
	// 변수 선언
	private static Connection dbConn;
	
	
	// 메소드 정의 → 연결.. 연결상태가 뭘 들고있다면 그거 그냥 실행
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		if (dbConn == null)
		{
			String url = "jdbc:oracle:thin:@211.238.142.156:1521:xe";
			
			String user = "scott";
			String pwd = "tiger";
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			dbConn = DriverManager.getConnection(url, user, pwd);
			
		}
		return dbConn;
	}
	
	// getConnection() 메소드의 오버로딩 → 연결
	public static Connection getConnection(String url, String user, String pwd) throws ClassNotFoundException, SQLException
	{
		if (dbConn == null)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			dbConn = DriverManager.getConnection(url, user, pwd);
		}
		return dbConn;
	}
	
	// 메소드 정의 → 연결 종료
	public static void close() throws SQLException
	{
		if (dbConn != null)
		{
			if (!dbConn.isClosed())
				dbConn.close();
		}
		
		// 연결 객체 초기화
		dbConn = null;
	}
	
}
