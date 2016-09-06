package com.web.controller;

import java.io.File;
import java.io.IOException;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sun.faces.el.ELUtils;

public class FileuploadServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//创建文件处理工厂对象
		DiskFileItemFactory dff=new DiskFileItemFactory();
		//创建文件上传处理文件
		ServletFileUpload sfu=new ServletFileUpload(dff);
		
		try {
			//读取请求的二进制数据
			List<FileItem> files=sfu.parseRequest(req);
			for(FileItem fi:files){
				//判断当前数据是否为普通表单字段（除文件上传以外的其他字段）
				if(fi.isFormField()){
					//判断当前数据是否为普通表单字段（除文件上传以外的其他字段）
				}else{
					//文件上传的二进制数据字段
					String fileName=fi.getName();
					String suffix=fileName.substring(fileName.lastIndexOf("."));
//					if(".jpg".equalsIgnoreCase(suffix)||".png".equalsIgnoreCase(suffix)||".gif".equalsIgnoreCase(suffix)||".hmp".equalsIgnoreCase(suffix)){
//						if(fi.getSize() <=1024*1024*1){
							Random random=new Random();//随机数
							int i=random.nextInt(10000);//0到10000之间的随机整数
							long l=new Date().getTime();//当前系统时间的毫秒数
							fileName=i+""+l+suffix;
							System.out.println(fileName);
							
							String path=this.getServletContext().getRealPath("files")+"/"+fileName;
							File f=new File(path);
							//将二进制数据写入文件保存在硬盘上
							fi.write(f);
							//插入数据库 修改数据
							String savePath="files/"+fileName;
							req.setAttribute("fileuploadMsg", "文件上传已完成！");
//						}else{
//							req.setAttribute("fileuploadMsg", "文件大小不鞥超过1M");
//						}
//					}else {
//						req.setAttribute("fileuploadMsg", "文件上传失败！");
				}
			}	
		} catch (FileUploadException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	    req.getRequestDispatcher("veiw/fileupload.jsp").forward(req, resp);
	
	}
}
