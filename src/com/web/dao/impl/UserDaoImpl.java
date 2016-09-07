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
	 * @param sname
	 * @param Oldpassword
	 * @return
	 */
	public boolean selectstaffNewpassword(String userName,String Oldpassword){
		//修改旧密码
		String sql="update staff set userPass=? where userName=?";
		//修改密码
		DBUtil.executeDML(sql, new Object[]{userName,Oldpassword});
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
		return true;
		
	}

	
}
