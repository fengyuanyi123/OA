<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath",basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>密码修改</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.min.js"></script>
	<style type="text/css">
	    div{
	    	width:350px;
	    	height: 300px;
	    	
	    }
		.wz{
		   position: absolute;
		   top: 200px;
		   left: 500px 
		}
		a{
  			display: inline-block; 
  			background-color:deepskyblue;
  			text-align:center;
  			text-decoration: none;
  			width:50px;
  			height:30px;  	
  			padding-top:4px;
  			color:white;
  			border-radius:10px;		
  		}
  		.in:hover{
  			border: 2px solid aquamarine;
		   	box-shadow:2px 2px 2px 2px aquamarine;	
		}
		.bs:hover {
		     opacity: 0.7;  
		         
	     }
		
	</style>
	<script type="text/javascript">
		function sub(sname){
			alert(sname);
			$.post('Passwordmodification.do',{
				methodName:'psswordModifiCation',				
				userName:sname,
				Oldpassword:$("#Oldpassword").val(),
				Newpassword:$("#Newpassword").val(),
				Newpword:$("#Newpword").val(),
			},function(data){
				if(data==0){
					$("#updatePwdError").html("输入的旧密码错误");
				}else if(data==1){
					$("#updatePwdError").html("两次输入的新密码不相同");
				}else if(data==2){
					$("#updatePwdError").html("新密码数据修改失败");
				}else{
					$("#updatePwdError").html("密码修改成功");
				}
			},"json");
		}
				
		//失去焦点
  		function foc(){
  			$("#updatePwdError").html("");
  		}
  		
  		//数据重置
  		function rese(){
  			$("#forms")[0].reset();
  		}
  		
	</script>

  </head>
  
  <body>
 	<div class="wz">
	    <form action="" method="post" id="forms" >
	    	<table>
	    		<tr>
	    			<td><lable >旧密码:</lable></td>
	    			<td><input type="password" class="in" onfocus="foc();" placeholder="请输入旧密码" id="Oldpassword"/></td>
                 </tr>
                 <tr>
	    			<td><lable >新密码:</lable></td>
	    			<td><input type="password" class="in" onfocus="foc();" placeholder="请输入新密码" id="Newpassword"/></td>
                 </tr>
                 <tr>
	    			<td><lable >确认密码:</lable></td>
	    			<td><input type="password" class="in" onfocus="foc();" placeholder="请确认新密码" id="Newpword"/></td>
                 </tr>
                 <tr >
                 	<td colspan="2" style="text-align:center;" >
                 		<a href="javascript:void(0);" onclick="sub('${loginUser.userName}');" class="bs">提交</a>
                 	    <a href="javascript:void(0);" onclick="rese()" class="bs">重写</a>
                 	</td>
                 </tr>
                 <tr>
	  				<td id="updatePwdError" colspan="2" style="text-align:center; color:red;"></td>  
	  			</tr>
                 
    		</table>
   		</form>
	</div>
  </body>
</html>