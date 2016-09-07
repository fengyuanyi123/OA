package com.web.dao;

import java.util.List;


import com.web.entity.Menu;
import com.web.entity.User;

import com.web.util.Page;
import com.web.vo.OaUser;





/**
 * 
 * @author Administrator
 *
 */
public interface UserDao {
	/**
	 * 通过用户名加载一个用户对象（登录）
	 * @param userName 用户输入的帐号
	 * @return 返回null表示用户的帐号不存在 
	 */
	public User loadUserByName(String userName);
	
	
	/**
	 * 通过登录的账号的id加载次用拥有的菜单集合
	 * @param uid
	 * @return
	 */
	public List<Menu> loadMenusBySid(int Sid);
	
	/**
	 * 加载所有用户信息
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<OaUser> loadAllOaUser(int pageNo, int pageSize,String sname,String userName,String Persona,String state);
	
	
	/**
	 * 用户管理————密码修改
	 * @param sname
	 * @param Oldpassword
	 * @return
	 */
	public boolean selectstaffNewpassword(String userName,String Oldpassword);
	
	
}

	