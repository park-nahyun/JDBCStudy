/*============================
  DBConn.java
  - try ~ catch 로 예외 처리
 ============================*/


package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn
{
	// 변수선언
	private static Connection dbConn;
	
	// 메소드 정의 → 연결
	public static Connection getConnection()
	{
		// 만약 연결이 null이라면 다음을 실행해
		if (dbConn == null)
		{
			try 
			{
				String url = "jdbc:oracle:thin:@211.238.142.156:1521:xe";
				String user = "scott";
				String pwd = "tiger";
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				dbConn = DriverManager.getConnection(url, user, pwd);
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
			
		}
		return dbConn;
	}
	
	// getConnection() 메소드의 오버로딩 → 연결
	public static Connection getConnection(String url, String user, String pwd)
	{
		
		if (dbConn == null)
		{
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				dbConn = DriverManager.getConnection(url, user, pwd);
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
		return dbConn;
	}
	
	// 메소드 정의 → 연결 종료
	public static void close()
	{
		if (dbConn != null)
		{
			try
			{
				if(!dbConn.isClosed())
					dbConn.close();
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
		dbConn = null;
		//-- 연결 객체 초기화
	}
}
