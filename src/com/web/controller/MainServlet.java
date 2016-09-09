package com.web.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;



import com.web.entity.Menu;
import com.web.entity.User;
import com.web.model.UserModel;
import com.web.model.impl.UserModelmpl;
import com.web.util.Page;

import com.web.vo.OaUser;
import com.web.vo.Rolevo;




public class MainServlet extends HttpServlet{

	private UserModel userModel=new UserModelmpl();
	 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		req.setCharacterEncoding("utf-8");
		String methodName=req.getParameter("methodName");
		//接收所有请求
		System.out.println(methodName);
		Class c=MainServlet.class;
		try {
			Method m=c.getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			m.invoke(this, req,resp);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 登录方法
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//取表单数据
		String userName = req.getParameter("userName");
		String userPass = req.getParameter("userPass");
		System.out.println(userName);
		System.out.println(userPass);
		//2、查询数据库
		System.out.print("LoginServlet控制层==>");
		User user = userModel.loadUserByName(userName);
		
		//利用上面的查询判断，并给出不同提示
		if(null != user){
			System.out.println("账号正确");
			if(userPass.equals(user.getUserPass())){
				//密码正确 保存数据到整个项目
				List<Menu> menuList = userModel.loadMenusBySid(user.getSid());
				for(Menu m:menuList){
					System.out.println(m);
				}
				
				req.getSession().setAttribute("loginUser", user);
				req.getSession().setAttribute("menuList", menuList);
				req.getRequestDispatcher("view/Welcome.jsp").forward(req, resp);
			}else{
				System.out.println("密码错误！");
				//密码错了,给出提示，跳转页面到登录页面
				req.setAttribute("loginError", "密码错误！");
				req.getRequestDispatcher("view/login.jsp").forward(req, resp);
			}
		}else{
			//账号不存在
			System.out.println("账号不存在！");
			req.setAttribute("loginError", "账号不存在！");
			req.getRequestDispatcher("view/login.jsp").forward(req, resp);
		}
	}
	
	
	
	/**
	 * 用户员工信息展示
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void oaUser(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int pageNo=Integer.valueOf(req.getParameter("pageNo"));
		int pageSize=Integer.valueOf(req.getParameter("pageSize"));
		//获取表单数据
		String sname=req.getParameter("sname");
		System.out.println(sname);
		String userName=req.getParameter("userName");
		String Persona=req.getParameter("Persona");
		String state=req.getParameter("state");
		
		Page<OaUser> page=userModel.loadAllOaUser(pageNo,pageSize,sname,userName,Persona,state);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("rows",page.getDataList());
		map.put("total", page.getTotal());
		String json=JSONObject.fromObject(map).toString();
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write(json);
		resp.getWriter().flush();
	}
	
	/**
	 * 角色管理--密码修改
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void psswordModifiCation(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//设置字符编码
		req.setCharacterEncoding("utf-8");
		//取出提交的信息
		String userName=req.getParameter("userName");//账号
		String Oldpassword=req.getParameter("Oldpassword");//旧密码
		String Newpassword=req.getParameter("Newpassword");//新密码
		String Newpword=req.getParameter("Newpword");//确认密码
		System.out.println("修改密码");
		System.out.println(userName);
		System.out.println("新密码"+Newpassword+"旧密码"+Oldpassword);
		//修改结果
		int result=0;
		//b为true旧密码正确，否则错误
		boolean b=userModel.selectstaffNewpassword(userName,Oldpassword);
		
		if(b){
			//旧密码正确，判断两次输入的密码是否相同
			if(null!=Newpassword&&null!=Newpword&&Newpassword.equals(Newpword)){
				//旧密码改成新密码
				User user=userModel.ModifyStaff(userName,Newpassword);
				System.out.println("新密码"+user.getUserPass());
				//返回的数据与Newpassword相等，则修改成功
				if(Newpassword.equals(user.getUserPass())){
					//密码修改成功
					result=3;
					
				}else{
					//修改失败
					result=2;
				}
			}else{
				//两次输入的新密码不相同
				result=1;
				
			}
		}else{
			//输入的旧密码错误
			result=0;
		}	
		System.out.println(result);
		
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");  
		resp.getWriter().write(result+"");
		resp.getWriter().flush();
		
	}
	
	/**
	 * 权限管理——角色管理
	 * oaRole 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public void oaRole(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int pageNo = Integer.valueOf(req.getParameter("pageNo"));
		int pageSize = Integer.valueOf(req.getParameter("pageSize"));
		Page<Rolevo> page = userModel.loadAllOaRole(pageNo,pageSize);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("rows",page.getDataList());
		map.put("total", page.getTotal());
		String json=JSONObject.fromObject(map).toString();
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write(json);
		resp.getWriter().flush();
	}
	
    /**
     * 权限管理--角色管理--添加角色
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
	public void addRole(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		System.out.println("添加角色");
		req.setCharacterEncoding("utf-8");
		//获取表单数据
		
		String dname=req.getParameter("dname");
		String state=req.getParameter("state");
		String explains=req.getParameter("explains");
		String[] roles={dname,state,explains};
		//根据结果返回信息
		int result=0;
		if(userModel.addRole(roles)){
			//添加成功
			result=1;
		}
		System.out.println(result+"");
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write(result+"");
		resp.getWriter().flush();
		
		
	}
	/**
	 * 权限管理--角色管理--删除角色
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	
	public void deleteRole(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("角色删除");
		req.setCharacterEncoding("utf-8");
		//获取删除用户的登录名数据
		String roleName=req.getParameter("roleName");
		System.out.println(roleName);
		String[] roleNames=roleName.split(",");
		int result=0;
		String deleteFail=userModel.deleteRole(roleNames);
		System.out.println("角色删除"+deleteFail);
		//用户删除
		if(deleteFail==""){
			//全部删除
			result=1;
		}else{
			//删除失败
			result=0;
		}
		
		//此处有问题待解决
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write(result+"");
		resp.getWriter().flush();
		
	}
	
	
}

	