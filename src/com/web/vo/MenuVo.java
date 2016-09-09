package com.web.vo;

import java.io.Serializable;

/**
 * 菜单列表权限实例化表
 * @author Administrator
 *
 */

public class MenuVo implements Serializable{
	//编号
	private int mid;
	//菜单列表
	private String name;
	//地址
    private String url;
    //是否展现
    private String isshow;
    //等级
    private String level;
    //父级菜单
    private String parentid;
    
    public String toString(){
		return "{\"mid\":"+mid+",\"name\":"+name+",\"url\":"+url+",\"isshow\":"+isshow+",\"level\":"+level+",\"parentid\":"+parentid+"}";
	}
    
    //菜单列表构造器
	public MenuVo(int mid, String name, String url, String isshow,
			String level, String parentid) {
		this.mid = mid;
		this.name = name;
		this.url = url;
		this.isshow = isshow;
		this.level = level;
		this.parentid = parentid;
	}

	//菜单列表无惨构造
	public MenuVo() {}

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

	public String getIsshow() {
		return isshow;
	}

	public void setIssshow(String isshow) {
		this.isshow = isshow;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	
	
    
	
    
    
	
}
