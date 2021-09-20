/*=========================
   Test001.java
   - ������ ���� �ǽ� 1
=========================*/

package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.util.DBConn;

public class Test001
{
	public static void main(String[] args)
	{
		try
		{
			Connection conn = DBConn.getConnection();
			
			if (conn != null)
			{
				System.out.println("�����ͺ��̽� ���� ����~!!!");
				
				try
				{
					/* 1
					Statement stmt = conn.createStatement();
					
					String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL)"
							+ " VALUES(MEMBERSEQ.NEXTVAL, '������', '010-2222-2222')";
					
					int result = stmt.executeUpdate(sql);
					
					if (result > 0)
						System.out.println("������ �Է� ����~!!!");
					
					stmt.close();
					DBConn.close();
					*/
					
					// 2
					// ������ �غ� �� �Ű����� ��ġ ���� ��?�� Ȱ��
					String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL)"
							+ " VALUES(MEMBERSEQ.NEXTVAL, ?, ?)";
					
					//  �۾� ��ü ���� �� ������ ����
					PreparedStatement ptstmt = conn.prepareStatement(sql);
					// �۾� ��ü�� �����Ǵ� ������ �������� �Ѱ���� �ϱ� ������
					// �������� �غ�Ǿ� �־�� �Ѵ�.
					
					// IN �Ű����� �Ѱ��ֱ�
					ptstmt.setString(1, "�̻���");
					ptstmt.setString(2, "010-4444-4444");
					
					// �۾� ��ü ���� 
					int result = ptstmt.executeUpdate();	// �������� �̹� �Ѱܳ��� ������..
					
					if (result > 0)
						System.out.println("������ �Է� ����~!!!");
					
					ptstmt.close();
					DBConn.close();
					
					//-- ���� 1, 2������ Statement �� sql �� ��ġ�� �ٲ㼭 �����ص� 
					//   �������� �� ������ �ȴٴ� ���� Ȯ���Ͽ���
					
				} catch (Exception e)
				{
					System.out.println(e.toString());
				}
				
			}
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}


/*
 * 
�����ͺ��̽� ���� ����~!!!
������ �Է� ����~!!!
*/
