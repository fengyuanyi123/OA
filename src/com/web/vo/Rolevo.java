package com.web.vo;

import java.io.Serializable;

public class Rolevo implements Serializable{
	//编号
	private int did;
	//名称
	private String dname;
	//状态
	private String state;
	//说明
	private String explains;
	//角色构造方法
	public Rolevo(int did, String dname, String state, String explains) {
		this.did = did;
		this.dname = dname;
		this.state = state;
		this.explains = explains;
	}
	//角色无参构造器
	public Rolevo(){}
	
	
	
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getExplains() {
		return explains;
	}
	public void setExplains(String explains) {
		this.explains = explains;
	}

	
	
	
	
	
	
	

}
