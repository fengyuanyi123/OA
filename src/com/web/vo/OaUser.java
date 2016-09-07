package com.web.vo;

import java.io.Serializable;

public class OaUser implements Serializable {
	private int sid;
	private String sname;
	private String userName;
	private String persona;
	private String state;
	private String department;
	
	
	public OaUser(int sid, String sname, String userName, String persona,
			String state, String department) {
		this.sid = sid;
		this.sname = sname;
		this.userName = userName;
		this.persona = persona;
		this.state = state;
		this.department = department;
		
	}

	public OaUser() {}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	
	
	
	
	

	
}
