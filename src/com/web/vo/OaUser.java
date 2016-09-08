package com.web.vo;

import java.io.Serializable;
/**
 * 角色菜单实例化表
 * @author Administrator
 *
 */
public class OaUser implements Serializable {
	//编号
	private int sid;
	//姓名
	private String sname;
	//账号
	private String userName;
	//部门负责人
	private String persona;
	//状态
	private String state;
	//部门
	private String department;
	
	//角色构造方法
	public OaUser(int sid, String sname, String userName, String persona,
			String state, String department) {
		this.sid = sid;
		this.sname = sname;
		this.userName = userName;
		this.persona = persona;
		this.state = state;
		this.department = department;
		
	}
    //角色无参构造
	public OaUser() {}
    //角色set和get方法
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
