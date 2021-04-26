package com.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ScoreProcess {

	private ScoreDAO dao;
	
	public ScoreProcess()
	{
		dao = new ScoreDAO();
	}
	
	
	// 성적 입력
	public void sungjukInsert() throws ClassNotFoundException, SQLException
	{
		dao.connection();
		int count = dao.count();
		
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println();
			System.out.printf("%d번 학생 성적 입력 : ", (++count));
			String name = sc.next();
			
			if (name.equals("."))
				break;
			int kor = sc.nextInt();
			int eng = sc.nextInt();
			int mat = sc.nextInt();
			
			ScoreDTO dto = new ScoreDTO();
			dto.setName(name);
			dto.setKor(kor);
			dto.setEng(eng);
			dto.setMat(mat);
			
			int result = dao.add(dto);
			if (result > 0)
				System.out.println(">> 성적 입력이 완료되었습니다.");
		} while (true);
		dao.close();
	}
	
	
	// 성적 출력
	public void selectAll() throws ClassNotFoundException, SQLException
	{
		dao.connection();
		System.out.printf("전체 인원 : %d명", dao.count());
		System.out.println("번호	이름	국어	영어	수학	총점	평균	석차");
		for (ScoreDTO dto:dao.lists())
		{
			System.out.printf("%3s %6s %5d %7d %7d %7d %8.1f%7d\n"
					, dto.getSid(), dto.getName(), dto.getKor(), dto.getMat()
					, dto.getEng(), dto.getTot(),  dto.getAvg(), dto.getRank());
		}
		dao.close();
	}	
	
	// 이름 검색 출력
	public void selectName() throws ClassNotFoundException, SQLException
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("검색할 이름을 입력하세요 : ");
		String name = sc.next();
		
		dao.connection();
		
		ArrayList<ScoreDTO> list = dao.lists(name);
		System.out.printf("검색된 인원 %d명\n", list.size());
		System.out.println("번호	이름	국어	영어	수학	총점	평균	석차");
		for (ScoreDTO dto:dao.lists())
		{
			System.out.printf("%3s %6s %5d %7d %7d %7d %8.1f%7d\n"
					, dto.getSid(), dto.getName(), dto.getKor(), dto.getMat(), dto.getEng()
					, dto.getTot(), dto.getAvg(), dto.getRank());
		}
		dao.close();
	}
	
	
	
	// 수정
	public void update() throws ClassNotFoundException, SQLException
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("수정할 학생의 번호를 입력하세요 : ");
		int sid = sc.nextInt();
		
		dao.connection();
		
		ArrayList<ScoreDTO> list = dao.lists(sid);
		
		if (list.size() > 0)
		{
			System.out.println("번호	이름	국어	영어	수학	총점	평균	석차");
			for (ScoreDTO dto:dao.lists())
			{
				System.out.printf("\"%3s %6s %5d %7d %7d %7d %8.1f%7d\n"
						, dto.getSid(), dto.getName(), dto.getKor(), dto.getMat()
						, dto.getTot(), dto.getAvg(), dto.getRank());
			}
			
			System.out.print("수정 데이터 입력(이름 국어 영어 수학) : ");
			String name = sc.next();
			int kor = sc.nextInt();
			int eng = sc.nextInt();
			int mat = sc.nextInt();
			
			ScoreDTO dto = new ScoreDTO();
			dto.setName(name);
			dto.setKor(kor);
			dto.setEng(eng);
			dto.setMat(mat);
			dto.setSid(String.valueOf(sid));
			
			int result = dao.modify(dto);
			
			if (result > 0)
				System.out.println(">> 수정이 완료되었습니다.");
		}
		else
			System.out.println("수정 대상이 존재하지 않습니다.");
			
		
	}
	
	
	// 삭제
	public void delete() throws ClassNotFoundException, SQLException
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.print(">> 삭제할 번호 입력 : ");
		int sid = sc.nextInt();

		ArrayList<ScoreDTO> arrayList = dao.lists(sid); 
		
		if(arrayList.size() > 0) 
		{ 
			System.out.println("번호 이름 국어 영어 수학 총점 평균 석차"); 
			for (ScoreDTO obj : arrayList)
			{ 
				System.out.printf("%s %3s %3d %3d %3d %3d %5.1f %3d\n" 
						, obj.getSid(), obj.getName() ,obj.getKor(), obj.getEng(), obj.getMat() 
						, obj.getTot(), obj.getAvg(), obj.getRank()); 
			} 
			System.out.print("정말 삭제하시겠습니까?(Y/N) : "); 
			String response = sc.next(); 
			if(response.equals("Y") || response.equals("y")) 
			{
				int result = dao.remove(sid); 
				if(result > 0) 
					System.out.println(">> 삭제가 완료되었습니다."); 
			} 
			else 
			{ 
				System.out.println(">> 취소되었습니다."); 
			}
		} 
		else 
		{ 
			System.out.println(">> 삭제 대상이 존재하지 않습니다."); 
		} 
		dao.close();
	}
		
	
}
