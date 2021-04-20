/*=====================
   MemberMain.java
 ======================*/

/*
 ○ 문제
    TBL_MEMBER 테이블을 활용하여
    이름과 전화번호를 여러 건 입력받고, 전체 출력하는 프로그램을 구현한다.
    단, 데이터베이스 연동이 이루어져야 하고
    MemberDAO, MemberDTO 클래스를 활용해야 한다.
    
실행 예)

이름 전화번호 입력(1) : 박민지 010-1111-1111
>> 회원 정보 입력 완료~!!!
이름 전화번호 입력(2) : 이희주 010-2222-2222
>> 회원 정보 입력 완료~!!!
이름 전화번호 입력(3) : 심혜진 010-3333-3333
>> 회원 정보 입력 완료~!!!
이름 전화번호 입력(4) : .

------------------------------------------------
전체 회원 수 : 3명
------------------------------------------------
번호		이름			전화번호
1		  박민지			010-1111-1111
2		  이희주			010-2222-2222
3		  심혜진			010-3333-3333
------------------------------------------------
 */
package com.test;

public class MemberMain_trash
{
	public static void main(String[] args)
	{
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		
		int count = dao.count(); // 몇 명인지 담아놓을 변수
		
		do
		{
			System.out.printf("이름 전화번호 입력(%d) : ",(++count));
			String name = sc.next();
			if(name.equals".")
				break;
			
			String tel = sc.next();
			
			dto.setName(name);
			dto.setTel(tel);
			
			int result = dao.add(dto);
			
			if (result > 0)
				System.out.println("회원 정보 입력 완료~!!!");
			
		}while(true);
	}

}
