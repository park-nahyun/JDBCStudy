/*=====================
   MemberDTO.java
 ======================*/

package com.test;

public class MemberDTO	// 속성만 있는.. 예전에 레코드 클래스라 불렀던 그것..
{
	// 주요 속성 구성
	private String sid, name, tel;

	// getter / setter 구성
	public String getSid()
	{
		return sid;
	}

	public void setSid(String sid)
	{
		this.sid = sid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getTel()
	{
		return tel;
	}

	public void setTel(String tel)
	{
		this.tel = tel;
	}
	
	
}
