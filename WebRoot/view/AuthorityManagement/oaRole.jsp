<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'oaRole.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>easyui/themes/icon.css">
	<script type="text/javascript" src="<%=basePath%>easyui/jquery-1.7.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
     $(function(){
    	$('#win').window('close');
        $('#dg').datagrid({
            url:'oaRole.do?methodName=oaRole&pageNo=1&pageSize=10',
            frozenColumns:[[{field:'hhhh',checkbox:true}]],
            rownumbers:true,
            striped:true,
            pagination:true,
            columns:[[
		        {field:'did',hidden:true},    
		        {field:'dname',title:'名称',width:100,align:'center'}, 
		        {field:'state',title:'状态',width:100,align:'center'},   
		        {field:'explains',title:'说明',width:300,align:'center'} 
	         ]],
	         toolbar: "#bar"
  			});
  		 var pager = $('#dg').datagrid("getPager");
	        pager.pagination({
	            onSelectPage:function(pageNumber,pageSize){
	        		refreshData(pageNumber, pageSize);
	            }
	       
        });
	   });
		//刷新表格数据
	  	function refreshData(pageNo, pageSize){
	  		$('#dg').datagrid('loading');
			$.post("oaRole.do",{
				methodName:'oaRole',
				pageNo:pageNo,
				pageSize:10
			},function(data){
				$("#dg").datagrid("loadData",{
					rows:data.rows,
					total:data.total
				});
				$('#dg').datagrid('loaded');
	  		},"json");
			
	  	}

	  	//删除角色
	  	function deleteRole(){
	  		var rows=new Array();
		  	var ids = [];
		  	var rows = $('#dg').datagrid('getSelections');
		  	if(rows.length==0){
				alert("请选择要删除的员工");
				return;
			  	}
		  	for(var i=0; i<rows.length; i++){
		  	    ids.push(rows[i].dname);
		  	}
		  	var roleName=ids.join(",");
		  	alert(roleName);
		  	//提示用户是否删除
		  	var b=window.confirm("确认要删除选中的角色吗");
		  	if(b){
		  		$.post('deleteRole.do',{
					methodName:'deleteRole',
					roleName:roleName
					},function(data){
						
						if(data==1){
							//全部删除
							$("#deleteFail").html("删除成功");
					
						}else{
							//删除失败的用户
							$("#deleteFail").html("删除失败");
							
							}
						//数据刷新
					  	refreshData(1,10);

						},'json');
		  		
			  	}else{
					return false;
				  	}
		  	}
	  	//打开增加角色窗口
	  	function addRoleWindow(){
	  		$('#win').window('open');  // open a window 
	  		
		}
	  	//增加角色    
	  	function addRole(){
			$.post('addRole.do',{
					methodName:'addRole',
					dname:$("#dname").val(),
					state:$(":input[name='state']:checked").val(),
					explains:$('#explains').val()
					
				},function(data){
						if(data==1){
							$("#message").html("添加成功");
							//数据刷新
						  	refreshData(1,10);
						}else{
							$("#message").html("角色名称重名添加失败");
						}

					},'json');
		}
  		
  		
    </script>

  </head>
  
  <body>
    <table id="dg"></table>
   	<div id="bar">
      	  <form id="searchForm">
      	  	  <table>
      	  	  	  <tr>
      	  	  	  	 <td colspan="10" style="text-align:center;">
      	  	  	  	     <a href="javascript:addRoleWindow();" class="easyui-linkbutton" data-options="iconCls:'icon-refresh'">添加</a>
      	  	  	  	     <a href="javascript:updateEmployee();" class="easyui-linkbutton" data-options="iconCls:'icon-modify'">修改</a>
      	  	  	  	     <a href="javascript:deleteRole();" class="easyui-linkbutton" data-options="iconCls:'icon-delete'">删除</a>
      	  	  	  	     <a href="javascript:refreshData(1,10);" class="easyui-linkbutton" data-options="iconCls:'icon-refresh'">刷新</a>
      	  	  	  	     <a href="javascript:void();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">角色流程</a>
      	  	  	  	     <a href="javascript:roleEmployee();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">角色人员</a>
      	  	  	  	     <a href="javascript:searchRoleEmployee();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">角色权限</a>
      	  	  	  	 	 <td id="deleteFail" style="color:red; width:100px"></td>
      	  	  	  	 </td> 
      	  	  	  </tr>
      	  	  </table>
      	  </form>
      </div>
   	 	<div id="win" class="easyui-window" title="添加角色" style="width:400px;height:300px;top:50px;" data-options="iconCls:'icon-add2',modal:true,collapsible:false,minimizable:false,maximizable:false,resizable:false">  
          <form id="ff">
          	  <table>
	      	  	  <tr>
	      	  	  	  <td class="labelTD"><label for="roleName">职位名称：</label></td>
	      	  	  	  <td><input type="text" name="dname" id="dname" class="easyui-validatebox in" required/></td>
	      	  	  </tr>
	      	  	 
	      	  	  <tr>
	      	  	  	  <td class="labelTD"><label>状态：</label></td>
	      	  	  	  <td>
	      	  	  	  	  <input type="radio" name="state" value="1"/>正常
	      	  	  	  	  <input type="radio" name="state" value="0"/>异常
	      	  	  	  </td>
	      	  	  </tr>
	      	  	   <tr>
	      	  	  	  <td class="labelTD"><label for="roleName">说明：</label></td>
	      	  	  	  <td><input type="text" name="explains" id="explains" class="easyui-validatebox in" required/></td>
	      	  	  </tr>
	      	  	  <tr>
	      	  	  	  <td colspan="2" style="text-align:center;">
	      	  	  	  	  <a href="javascript:addRole();" class="easyui-linkbutton" data-options="iconCls:'icon-add2'">添加角色</a>
	      	  	  	  </td>
	      	  	  </tr>
	      	  	   <tr>
	      	  	  	  <td id="message" colspan="2" style="color:red;text-align:center;"></td>	      	  	  	 
	      	  	  </tr>
	      	  </table>
          </form> 
	  </div>
  </body>
</html>
