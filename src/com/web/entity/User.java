package com.web.entity;

import java.io.Serializable;
/**
 * 角色菜单实例化表
 * @author Administrator
 *
 */
public class User implements Serializable{
	//编号
	private int sid;
	//姓名
	private String sname;
	//账号
	private String userName;
	//密码
	private String userPass;
	//部门负责人
	private int Persona;
	//状态
	private int state;
	//部门
	private String department;
	//时间
	private String stime;
	/**
	 * 无参构造
	 */
	public User() {}
	/**
	 *构造方法
	 * @param sid
	 * @param sname
	 * @param userName
	 * @param userPass
	 * @param persona
	 * @param state
	 * @param department
	 * @param stime
	 */
	public User(int sid, String sname, String userName, String userPass,
			int persona, int state, String department, String stime) {
		this.sid = sid;
		this.sname = sname;
		this.userName = userName;
		this.userPass = userPass;
		this.Persona = persona;
		this.state = state;
		this.department = department;
		this.stime = stime;
	}
    //get和set封装方法
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

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public int getPersona() {
		return Persona;
	}

	public void setPersona(int persona) {
		Persona = persona;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}
	

	
}