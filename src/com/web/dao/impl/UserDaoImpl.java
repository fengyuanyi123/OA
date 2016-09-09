package com.web.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;






import com.web.dao.UserDao;
import com.web.entity.Menu;
import com.web.entity.User;

import com.web.util.DBUtil;
import com.web.util.Page;
import com.web.vo.OaUser;
import com.web.vo.Rolevo;




public class UserDaoImpl implements UserDao{

	/**
	 * 通过用户名加载一个用户对象（登录）
	 * @param userName 用户输入的帐号
	 * @return 返回null表示用户的帐号不存在 
	 */
	public User loadUserByName(String userName){
		System.out.println("UserDaoImpl数据层");
		String sql = "select * from staff where userName=?";
		
		List<Object[]> list = DBUtil.executeQuery(sql, new Object[]{userName});
		User user = null;
		if(null != list && list.size() > 0){
			Object[] ob = list.get(0);
		    user = new User((Integer)ob[0],String.valueOf(ob[1]),userName,String.valueOf(ob[3]),(Integer)ob[4],(Integer)ob[5],String.valueOf(ob[6]),String.valueOf(ob[7]));
		    
		}
		return user;
	}
	
	/**
	 * 通过登录的账号的id加载次用拥有的菜单集合
	 * @param uid
	 * @return
	 */
	public List<Menu> loadMenusBySid(int Sid){
		//查询数据库
		String sql="select m.* from staffdep sd,depmenu dm,menu m where sd.did=dm.did and dm.mid=m.mid and m.isshow=1 and sd.sid=?";
		//用List集合接收
		List<Object[]> list=DBUtil.executeQuery(sql, new Object[]{Sid});
		System.out.println(list);
		//用数组集合接收
		List<Menu> menuList = new ArrayList<Menu>();
		Menu m=null;
		if(null!=list&&list.size()>0){
			for(Object[] os : list){
				m = new Menu((Integer)os[0], String.valueOf(os[1]), String.valueOf(os[2]), (Integer)os[3], (Integer)os[4], (Integer)os[5]);
				menuList.add(m);
				
			}
		}
		return menuList;	
	}
	
	/**
	 * 加载所有用户信息
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<OaUser> loadAllOaUser(int pageNo, int pageSize,String sname,
			String userName,String Persona,String state){
		StringBuffer sql = new StringBuffer(" from staff s where 1=1");
		if(null != sname&&!"".equals(sname))sql.append(" and s.sname like '%"+sname.trim()+"%'");
		if(null != userName&&!"".equals(userName))sql.append(" and s.userName like '%"+userName.trim()+"%'");
		if(null != Persona&&Integer.valueOf(Persona)>0)sql.append(" and s.Persona="+Persona);
		if(null != state&&Integer.valueOf(state)>0)sql.append(" and s.state="+state);
		
		
		String dsql="select s.sid,s.sname,s.userName,s.Persona,s.state,s.department ";
		List<Object[]> list = DBUtil.executeQuery(dsql+sql.toString()+" limit ?,?", new Object[]{(pageNo-1)*pageSize,pageSize});
        
		List<OaUser> userList = new ArrayList<OaUser>();
        OaUser o = null;
       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       // sdf.format((Date)os[5])
        if(null!=list&&list.size()>0){
        	for(Object[] os:list){
				o = new OaUser((Integer)os[0], String.valueOf(os[1]), String.valueOf(os[2]),1==(Integer)os[3]?"是":"否", 1==(Integer)os[4]?"正常":"异常",String.valueOf(os[5]));
				userList.add(o);
			}
		}
		dsql = "select count(*) ";
		list = DBUtil.executeQuery(dsql+sql.toString(), null);
        long total = (Long)list.get(0)[0];
        return new Page<OaUser>(pageNo,pageSize,userList,total);
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
		//修改旧密码
		String sql="update staff set userPass=? where userName=?";
		//修改密码
		DBUtil.executeDML(sql, new Object[]{Newpassword,userName});
		//查询旧密码SQL语句
		sql="select *from staff where userName=?";
		//查询数据库获取结果集
		List<Object[]> list=DBUtil.executeQuery(sql, new Object[]{userName});
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		User us=null;
		//遍历集合，实例化staff对象
		for(Object[] ob:list){
			us=new User((Integer)ob[0],String.valueOf(ob[1]),userName,String.valueOf(ob[3]),(Integer)ob[4],(Integer)ob[5],String.valueOf(ob[6]),sdf.format((Date)ob[7]));
		}
		return us;
		
	}
	
	/**
	 * 用户管理————密码修改
	 * 通过员工账号查询数据库，判断输入的旧密码是否正确，正确返回true,错返回false
	 * @param sname
	 * @param Oldpassword
	 * @return
	 */
	public boolean selectstaffNewpassword(String userName,String Oldpassword){
		//true为旧密码正确，false旧密码错误
		boolean b=false;
		//查询旧密码SQL语句
		String sql="select *from staff where userName=?";
		List<Object[]> list=DBUtil.executeQuery(sql, new Object[]{userName});
		System.out.println(userName);
		if(list!=null&&list.size()>0&&Oldpassword.equals(list.get(0)[3])){
			//输入的旧密码正确，返回true
			b=true;
		}else{
			//输入的旧密码错误，返回false
			b=false;
		}
		return b;
	}
	
	/**
	 * 权限管理——角色管理
	 * Rolevo 角色实例化表
	 * @return
	 */
	public Page<Rolevo> loadAllOaRole(int pageNo,int pageSize){
		//查询数据库
		String sql = "select d.did,d.dname,d.state,d.explains from department d limit ?,? ";
		List<Object[]> list = DBUtil.executeQuery(sql, new Object[]{(pageNo-1)*pageSize,pageSize});
		List<Rolevo> roleList = new ArrayList<Rolevo>();
		Rolevo r = null;
		if(null != list&&list.size()>0){
			for(Object[] os:list){
				r = new Rolevo((Integer)os[0],String.valueOf(os[1]),1==(Integer)os[2]?"使用":"停用",String.valueOf(os[3]));
				roleList.add(r);
			}
		}
		//查询返回行数
		sql = "select count(*) from department";
		list = DBUtil.executeQuery(sql, null);
        long total = (Long)list.get(0)[0];
        return new Page<Rolevo>(pageNo,pageSize,roleList,total);	
	}
	
	/**
	 * 权限管理——角色管理——添加角色
	 * @param names
	 * @return true为添加成功，否则反之
	 */
	public boolean addRole(String[] roles){
		//先查询数据库中是否已有此角色名称
		String existsData="select * from department where dname=?";
		List<Object[]> lists=DBUtil.executeQuery(existsData, new Object[]{roles[0]});
		if(lists.size()>0){
			//数据库已存在角色名称
			return false;
		}else{
			String sql="insert into department(dname,state,explains) values(?,?,?)";
			//添加用户
			DBUtil.executeDML(sql, new Object[]{String.valueOf(roles[0]),String.valueOf(roles[1]),String.valueOf(roles[2])});
			return true;
		}
		
		
		
	}
	
	/**
	 * 权限管理——角色管理——删除角色
	 * @param names
	 * @return
	 */
	public String deleteRole(String[] roleNames){
		//执行删除角色SQL语句
		String sql="delete from department where dname=?";
		//遍历数组，删除数据库内的用户
		for(String roleName : roleNames){
			DBUtil.executeDML(sql, new Object[]{roleName});
		}
		//再次查询数据库是否存在已删除用户
		sql="select * from department where dname=? ";
		String deleteFail="";
		for(String roleName : roleNames){
			List<Object[]> list=DBUtil.executeQuery(sql,  new Object[]{roleName});
			if(list.size()>0){
				//记录删除失败的用户
				deleteFail+=String.valueOf(list.get(0)[1])+",";
			}
		}
		return deleteFail;
	}
	
}
