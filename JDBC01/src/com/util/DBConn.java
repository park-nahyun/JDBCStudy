/*==========================
 DBConn.java
 ===========================*/

/*
※ 싱글톤(Singleton) 디자인 패턴을 이용한 Database 연결 객체 생성 전용 클래스
   → DB 연결 과정이 가장 부하가 크기 때문에
      한 번 연결된(생성된) 객체를 계속 사용하는 것이 좋다.
*/

package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn
{
	// 변수 선언(정보은닉)
	private static Connection dbConn;
	//-- 자동 null 초기화 
	
	// 메소드 정의 → 연결.. 연결상태가 뭘 들고있다면 그거 그냥 실행
	// 뭘 들고 있지 않은 null인 상태라면 이걸줘라
	public static Connection getConnection() 
	{
		// 탄광 입구와 내부의 끈.. 커넥션 객체를 만드는 것
		// 이건 되게 시간, 비용 많이 드는 일.. 한 번 레일을 깔면 계속 쓸 수 있게 해야겠지? 그래서 싱글톤 쓰는 것
		// 즉, 연결되지 않은 경우에만 연결을 시도하겠다는 의미
		// → 싱글톤(디자인 패턴).. 디자인 패턴이란? 일종의 공식.. 이런경우에는 이걸써라~ 이런걸 정해놓은.. 설계 유형?
		if (dbConn == null)	
		{
			try
			{
				String url = "jdbc:oracle:thin:@211.238.142.156:1521:xe"; // xe 오라클 기본아이덴티티 식별자, 1521 오라클 기본포트
				//-- 『211.238.142.150』는 소라클 서버 ip 주소를 기재하는 부분
				//    원격지의 오라클이 아니라 로컬의 오라클 서버일 경우는 
				//   『localhost』이나 『127.0.0.1』과 같이 loop back address로 기재하는 것도 가능
				//    『1521』은 오라클 리스너 기본 Port Number
				//     『xe』는 오라클 SID(Express Edition 은 xe)
				
				String user = "scott";	//-- 오라클 사용자 계정 이름
				String pwd = "tiger";	//-- 오라클 사용자 계정 암호
				
				Class.forName("oracle.jdbc.driver.OracleDriver");	// 이 이름을 쓰는 클래스를 찾아줘
				//-- OracleDriver 클래스에 대한 객체 생성
				
				dbConn = DriverManager.getConnection(url, user, pwd);	// 이 작업 수행하는 동안 예외발생할 수 있으므로 try catch
				//-- 오라클 서버 실제 연결
				// getConnection은 스태틱, 클래스 메소드(DriverManager.)로 호출했으니까..ㅎ 
				//   갖고 있는 인자값(매개변수)은 오라클주소, 계정명, 패스워드
				// 드라이버 객체. 얘한테 끈 달라고 요청하는 것.. 
			} catch (Exception e)	// ClassNotFoundException, SQlException
			{
				System.out.println(e.toString());
				//-- 오라클 서버 연결 실패 시 오류 메세지 출력 부분
			}
		}
		
		return dbConn;
		//-- 구성된 연결 객체 반환
	}
	
	// getConnection() 메소드의 오버로딩 → 연결
	public static Connection getConnection(String url, String user, String pwd)
	{
		if (dbConn == null)
		{
			try
			{	// 위 구문과 똑같은데 매개변수로 넘겨받은 것만 지움
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
		// dbConn 변수(멤버 변수)는
		// Database 가 연결된 상태일 경우 Connection을 갖는다.
		// 연결되지 않은 상태라면... null 을 갖는다.
		if (dbConn != null)
		{
			try
			{
				// 연결 객체의 isClosed() 메소드를 통해 연결 상태 확인
				//-- 연결이 닫혀있는 경우 true 반환
				//   연결이 닫혀있지 않은 경우 false 반환
				if(!dbConn.isClosed())
					dbConn.close();
					//-- 연결 객체의 close() 메소드를 통해 연결 종료
			
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
	
		dbConn = null;
		//-- 연결 객체 초기화
	}
}





