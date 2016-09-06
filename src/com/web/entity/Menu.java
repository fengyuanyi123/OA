package com.web.entity;

import java.io.Serializable;



public class Menu implements Serializable{
	private int mid;
	private String name;
    private String url;
    private int issshow;
    private int level;
    private int parentid;
    
    
    
	public Menu(int mid, String name) {
		this.mid = mid;
		this.name = name;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getIssshow() {
		return issshow;
	}
	public void setIssshow(int issshow) {
		this.issshow = issshow;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	public Menu(int mid, String name, String url, int issshow, int level,
			int parentid) {
		this.mid = mid;
		this.name = name;
		this.url = url;
		this.issshow = issshow;
		this.level = level;
		this.parentid = parentid;
	}
	public Menu() {}
    

}
