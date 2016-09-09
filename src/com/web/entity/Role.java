package com.web.entity;

import java.io.Serializable;

public class Role implements Serializable{
	//编号
	private int did;
	//名称
	private String dname;
	//状态
	private int state;
	//说明
	private String explains;
	//角色无参构造
	public Role(){}
	//角色构造器
	public Role(int did, String dname, int state, String explains) {
		this.did = did;
		this.dname = dname;
		this.state = state;
		this.explains = explains;
	}
	//角色set和get封装方法
	
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getExplains() {
		return explains;
	}
	public void setExplains(String explains) {
		this.explains = explains;
	}
	
	
	

}
