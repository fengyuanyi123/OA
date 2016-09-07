package com.web.model.impl;



import java.util.List;

import com.web.dao.UserDao;
import com.web.dao.impl.UserDaoImpl;



import com.web.entity.Menu;
import com.web.entity.User;

import com.web.model.UserModel;
import com.web.util.Page;
import com.web.vo.OaUser;




public class UserModelmpl implements UserModel{

	//模型层持有一个DAO层对象
	private UserDao userDao = new UserDaoImpl();
	
	/**
	 * 通过用户名加载一个用户对象（登录）
	 * @param userName 用户输入的帐号
	 * @return 返回null表示用户的帐号不存在 
	 */
	public User loadUserByName(String userName){
		System.out.print("UserModelMySQLImpl模型==>");
		return userDao.loadUserByName(userName);
	}
	
	/**
	 * 通过登录的账号的id加载次用拥有的菜单集合
	 * @param uid
	 * @return
	 */
	public List<Menu> loadMenusBySid(int Sid){
		return userDao.loadMenusBySid(Sid);     
	}
	
	/**
	 * 加载所有菜单
	 * @return
	 */
	public Page<OaUser> loadAllOaUser(int pageNo, int pageSize,String sname,String userName,String Persona,String state){
		return userDao.loadAllOaUser(pageNo, pageSize, sname, userName, Persona, state);	
	}
	
	
	/**
	 * 用户管理————密码修改
	 * @param sname
	 * @param Oldpassword
	 * @return
	 */
	public boolean selectstaffNewpassword(String userName,String Oldpassword){
		return userDao.selectstaffNewpassword(userName,Oldpassword);
	}
	
	/**
	 * 用户管理————密码修改
	 * @param sname
	 * @param Oldpassword
	 * @User 员工实例化表
	 * @return
	 */
	public User ModifyStaff(String userName,String Newpassword){
		return userDao.ModifyStaff(userName,Newpassword);
	}
}
