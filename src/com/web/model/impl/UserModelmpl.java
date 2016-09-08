package com.web.model.impl;



import java.util.List;

import com.web.dao.UserDao;
import com.web.dao.impl.UserDaoImpl;



import com.web.entity.Menu;
import com.web.entity.Role;
import com.web.entity.User;

import com.web.model.UserModel;
import com.web.util.Page;
import com.web.vo.OaUser;
import com.web.vo.Rolevo;




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
	 * 通过员工账号查询数据库，判断输入的旧密码是否正确，正确返回true,错返回false
	 * @param sname
	 * @param Oldpassword
	 * @return
	 */
	public boolean selectstaffNewpassword(String userName,String Oldpassword){
		return userDao.selectstaffNewpassword(userName,Oldpassword);
	}
	
	/**
	 * 用户管理————密码修改
	 * @User 员工实例化表
	 * 通过员工账号查询数据库，将旧密码改成新密码，返回Employee对象
	 * @param sname
	 * @param Oldpassword
	 * @return
	 */
	public User ModifyStaff(String userName,String Newpassword){
		return userDao.ModifyStaff(userName,Newpassword);
	}
	
	
	/**
	 * 权限管理——角色管理
	 * Rolevo 角色实例化表
	 * @return
	 */
	public Page<Rolevo> loadAllOaRole(int pageNo,int pageSize){
		return userDao.loadAllOaRole(pageNo,pageSize);
	}
	
	/**
	 * 权限管理——角色管理——添加角色
	 * @param names
	 * @return
	 */
	public boolean addRole(String[] roles){
		return userDao.addRole(roles);
	}
	
	/**
	 * 权限管理——角色管理——删除角色
	 * @param names
	 * @return
	 */
	public String deleteRole(String[] roleNames){
		return userDao.deleteRole(roleNames);
	}
	
}
