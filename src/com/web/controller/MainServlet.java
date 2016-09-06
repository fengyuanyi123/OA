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


import com.oa.vo.EmployeeVo;
import com.web.entity.Menu;
import com.web.entity.User;
import com.web.model.UserModel;
import com.web.model.impl.UserModelmpl;
import com.web.util.Page;

import com.web.vo.OaUser;




public class MainServlet extends HttpServlet{

	private UserModel userModel=new UserModelmpl();
	 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//接收所有请求
		
		String methodName=req.getParameter("methodName");
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
		System.out.println(user);
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
		Page<OaUser> page=userModel.loadAllOaUser(pageNo,pageSize);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("rows",page.getDataList());
		map.put("total", page.getTotal());
		String json=JSONObject.fromObject(map).toString();
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write(json);
		resp.getWriter().flush();
	}
	
	/**
	 * 用户管理-员工组合查询
	 */
	public void searchEmployee(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		System.out.println("组合查询");
		//获取分页数据
		int pageNo = Integer.valueOf(req.getParameter("pageNo"));
		int pageSize = Integer.valueOf(req.getParameter("pageSize"));
		req.setCharacterEncoding("utf-8");
		//获取表单数据
		String sname=req.getParameter("sname");
		String userName=req.getParameter("userName");
		String Persona=req.getParameter("Persona");
		String state=req.getParameter("state");
		String[] sta={sname,userName,Persona,state};
		//对搜索条件进行组合查询
		Page<OaUser> page=userModel.searchstaff(sta,pageNo,pageSize);
		System.out.println("返回的页面对象"+page);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows", page.getDataList());
		map.put("total", page.getTotal());
		resp.setCharacterEncoding("utf-8");
		String json=JSONObject.fromObject(map).toString();
		resp.getWriter().write(json);
		resp.getWriter().flush();
	}
//	public void updateEmployeePwd(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		//设置字符编码
//		req.setCharacterEncoding("utf-8");
//		//取出提交的信息
//		String userName=req.getParameter("userName");
//		String Oldpassword=req.getParameter("Oldpassword");
//		String Newpassword=req.getParameter("Newpassword");
//		String Newpword=req.getParameter("Newpword");
//		//修改结果
//		int result=0;
//		//b为true旧密码正确，否则错误
//		boolean b=userModel.selectstaffNewpassword(userName,Oldpassword);
//		if(b){
//			//旧密码正确，判断两次输入的密码是否相同
//			if(null!=newPwd&&null!=newPwdSure&&newPwd.equals(newPwdSure)){
//				//旧密码改成新密码
//				staff emp=userModel.changEmployeePwd(userName,Oldpassword);
//				//返回的数据与newPwd相等，则修改成功
//				if(newPwd.equals(emp.getEmpPwd())){
//					//密码修改成功
//					result=3;
//					
//				}else{
//					//修改失败
//					result=2;
//				}
//			}else{
//				//两次输入的新密码不相同
//				result=1;
//				
//			}
//		}else{
//			//输入的旧密码错误
//			result=0;
//		}
//		
//		resp.setCharacterEncoding("utf-8");
//		resp.setContentType("text/html;charset=utf-8");  
//		resp.getWriter().write(result+"");
//		resp.getWriter().flush();
//		
//	}
	
}

	