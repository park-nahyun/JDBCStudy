/*===================================
    Test003.java
    - ������ ���� �ǽ� 2
 ====================================*/

package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.util.DBConn;

public class Test003 
{
	public static void main(String[] args) {
		try {
			Connection conn = DBConn.getConnection();
			
			if (conn != null) {
				System.out.println("�����ͺ��̽� ���� ����~!!!");
				
				try {
					String sql = "SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					
					ResultSet rs = pstmt.executeQuery();
					
					while (rs.next()) {
						int sid = rs.getInt("SID");
						String name = rs.getString("NAME");
					    String tel = rs.getString("TEL");
		                  
		                 String str = String.format("%3d %7s %10s", sid, name, tel);
		                 System.out.println(str);

						
					}
					rs.close();
					pstmt.close();
				} catch (Exception e) {
				    System.out.println(e.toString());

				}
		         DBConn.close();
		         
		         System.out.println("\n�����ͺ��̽� ���� ����");
		         System.out.println("���α׷� ����");

			}
		} catch (Exception e) {
		    System.out.println(e.toString());

		}
	}
}
