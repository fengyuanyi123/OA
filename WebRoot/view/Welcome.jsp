<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Welcome.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>easyui/themes/icon.css">
	<script type="text/javascript" src="<%=basePath%>easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		function addTab(title,url){
			$('#tab').tabs('add',{
				title: title,
				selected: true,
				closable: true,
				content : "<iframe width='100%' height='100%'  frameborder='0' scrolling='auto' src='"+url+"'></iframe>"
			});
			
		}
	</script>

  </head>
  
  <body class="easyui-layout">   
    <div data-options="region:'north',title:'North Title',split:true" style="height:100px;">
    	<p>欢迎你，${loginUser.userName}</p>
    </div>   
	<div data-options="region:'west',title:'系统界面',split:true" style="width:200px;">
		<div id="aa" class="easyui-accordion" data-options="fit:true">   
  			<c:forEach items="${menuList}" var="m2">
				<c:if test="${m2.level==2}">
		    		<div title="${m2.name}" data-options="iconCls:'icon-save'" style="overflow:auto;padding:10px;">   
						<ul>
							<c:forEach items="${menuList}" var="m3">
								<c:if test="${m3.parentid==m2.mid}">
									<li><a href="javascript:void(0);" onclick="addTab('${m3.name}','<%=basePath%>${m3.url}');">${m3.name}</a></li>
								</c:if>
							</c:forEach>
						</ul>
		    		</div>   
				</c:if>		
			</c:forEach>
		</div>  

		
	</div>   
    <div data-options="region:'center'" style="padding:5px;background:#eee;">
    	<div id="tab" class="easyui-tabs" style="width:500px;"data-options="fit:true">   
		    <div title="欢迎界面" style="padding:20px;display:none;">   
		        tab1    
		    </div>   
		</div> 
    </div>   
</body>
</html>
