/*===================================
    Test002.java
    - ������ ���� �ǽ� 2
 ====================================*/

package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.util.DBConn;

public class Test002 
{
	public static void main(String[] args) 
	{
		try 
		{
			Scanner sc = new Scanner(System.in);
			Connection conn = DBConn.getConnection();
			
			do 
			{
				System.out.print("�̸� �Է�(-1 ����) : ");
				String name = sc.next();
				
				if (name.equals("-1"))
					break;
				
				System.out.print("��ȭ��ȣ �Է� : ");
				String tel = sc.next();
				
				if (conn != null)
				{
					System.out.println("�����ͺ��̽� ���� ����~!!!");
					
					try
					{
						String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL)"
								+ " VALUES(MEMBERSEQ.NEXTVAL, ?, ?)";
						
						PreparedStatement pstmt = conn.prepareStatement(sql);
						
						pstmt.setString(1, name);
						pstmt.setString(2, tel);
						
						int result = pstmt.executeUpdate();
						
						if (result > 0)
							System.out.println("ȸ�� ���� �Է� ����~!!!");
						
					} catch (Exception e) 
					{
						System.out.println(e.toString());
					}
				}
			} while (true);
			
			DBConn.close();
			System.out.println();
			System.out.println("�����ͺ��̽� ���� ����~!!!");
			System.out.println("���α׷� ����");
			
		} catch (Exception e) 
		{
			System.out.println(e.toString());
		}
	}

}
