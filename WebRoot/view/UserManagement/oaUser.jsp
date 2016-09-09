<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user.jsp' starting page</title>
    
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
            url:'oaUser.do?methodName=oaUser&pageNo=1&pageSize=10',
            frozenColumns:[[{field:'hhhh',checkbox:true}]],
            rownumbers:true,
            striped:true,
            pagination:true,
            columns:[[
		        {field:'sid',hidden:true},    
		        {field:'sname',title:'姓名',width:100,align:'center'}, 
		        {field:'userName',title:'登录名',width:100,align:'center'},   
		        {field:'persona',title:'部门负责人   是:否',width:300,align:'center'}, 
		        {field:'state',title:'在线状态',width:100,align:'center'}, 
		        {field:'department',title:'部门',width:100,align:'center'}
		           
  		    ]],
  		    toolbar: [{
  		        text:'添加',
				iconCls:'icon-add',
				handler: function(){
				    $('#win').window('open');//open a window
				}    
			},'-',{
				text:'修改',
				iconCls: 'icon-modify',
				handler: function(){
				    alert('修改按钮');
			    }
			},'-',{
				text:'刷新',
				iconCls: 'icon-refresh',
				handler: function(){
				    searchStaff(1,10);
				}
			},'-',{
				text:'删除',
				iconCls: 'icon-delete',
				handler: function(){
				    alert('删除按钮');
				}
			},'-',{
				text:'密码重置',
				iconCls: 'icon-reload',
				handler: function(){
				    alert('密码重置');
				}	
			}]
		  		    
        });
        
        var pager = $('#dg').datagrid("getPager");
        pager.pagination({
            onSelectPage:function(pageNumber,pageSize){
            	searchStaff(pageNumber,pageSize);
            }
        });
         //添加菜单时加载父级菜单列表
        //$('#department').combobox({
        //	url:'toAddMenu.do?methodName=toAddMenu',
        //	textField:'name',
        //	valueField:'mid',
        //	value:'-1'
       // });
    });
   
    //组合查询
	function searchStaff(pageNo,pageSize){
		$.post('oaUser.do',{
			methodName:'oaUser',
			pageNo:pageNo,
			pageSize:pageSize,
			sname:$("#SearchStaffname").val(),
			userName:$("#SearchStaffAccount").val(),
			Persona:$("#SearchStaffbumebn").val(),//.combo("getValue"),
			state:$("#SearchStaffStatus").val()//.combo("getValue")
		},function(data){
			$('#dg').datagrid('loading');
			$("#dg").datagrid("loadData",{
				rows:data.rows,
				total:data.total
			});
			$('#dg').datagrid('loaded');

		},'json');
	}
	//重置搜索条件
	function searchsStaffReset(){
		//$("#searchForm")[0].reset();
		$("#searchForm").form("reset");
	}
    </script>

  </head>
  
  <body>
  	<div id="bar">
      	  <form id="searchForm">
      	  	  <table>
      	  	  	  <tr>
      	  	  	  	  <td><label>姓名：</label></td>
      	  	  	  	  <td><input type="text" id="SearchStaffname" style="width:150px;"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
      	  	  	  	  <td><label>账号：</label></td>
      	  	  	  	  <td><input type="text" id="SearchStaffAccount" style="width:150px;"/></td>
      	  	  	  	  <td></td>
      	  	  	  </tr>
      	  	  	  <tr>
      	  	  	  	  <td><label>部门负责人：</label></td>
      	  	  	  	  <td>
      	  	  	  	  	<select id="SearchStaffbumebn" style="width:150px;">
      	  	  	  	  		<option value="-1">全部</option>
      	  	  	  	  		<option value="1">是</option>
      	  	  	  	  		<option value="2">否</option>
      	  	  	  	  	</select>
      	  	  	  	  </td>
      	  	  	  	  <td><label>状态：</label></td>
      	  	  	  	  <td>
      	  	  	  	  	<select id="SearchStaffStatus" style="width:150px;">
      	  	  	  	  		<option value="-1">全部</option>
      	  	  	  	  		<option value="1">正常</option>
      	  	  	  	  		<option value="2">异常</option>
      	  	  	  	  	</select>
      	  	  	  	  </td> 
     	  	  		  <td style="text-align:center;">
      	  	  	  	     <a href="javascript:void(0);"  onclick="searchStaff(1,10);" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>	 
      	  	  	  	     <a href="javascript:void(0);"  onclick="searchsStaffReset();" class="easyui-linkbutton" data-options="iconCls:'icon-search'">重置</a>	 
      	  	  	  	  </td> 
      	  	  	  </tr>
      	  	   </table>
      	  </form>
      </div>
    <table id="dg"></table> 
    <div id="win" class="easyui-window" title="新添菜单" style="width:400px;height:300px;top:50px;" data-options="iconCls:'icon-edit',modal:true,collapsible:false,minimizable:false,maximizable:false,resizable:false">
   	     <form id="ff">	
     		<table>
     			<tr>
	      	  	  	<td class="labelTD"><label for="Sname">姓名：</label></td>
	      	  	  	<td><input type="text" name="Sname" id="Sname" class="elsyui-validatebox in" required/></td>
	      	  	</tr>
	      	  	<tr>
	      	  	  	<td class="labelTD"><label for="userName">账号：</label></td>
	      	  	  	<td><input type="text" name="userName" id="userName" class="elsyui-validatebox in" required/></td>
	      	  	</tr>
	      	  	<tr>
	      	  	  	<td class="labelTD"><label>部门负责人：</label></td>
	      	  	  	<td>
	      	  	  	  	<input type="radio" name="persona" value="1"/>是
	      	  	  	  	<input type="radio" name="persona" value="2"/>否
	      	  	  	</td>
	      	  	</tr>
	      	  	<tr>
	      	  	  	<td class="labelTD"><label for="parentid">状态：</label></td>
	      	  	  	<td>
	      	  	  	  	<input type="radio" name="state" value="1"/>是
	      	  	  	  	<input type="radio" name="state" value="2"/>否
	      	  	  	</td>
	      	  	</tr>
	      	  	<tr>
	      	  	  	  <td class="labelTD"><label for="dept">部门：</label></td>
	      	  	  	  <td>
	      	  	  	  	  <select name="department" id="department" class="easyui-combobox in" style="width:205px;"></select>
	      	  	  	  </td>
	      	  	  </tr>
	      	  	  <tr>
	      	  	  	  <td colspan="2" style="text-align:center;">
	      	  	  	  	  <a href="javascript:addstaff();" class="easyui-linkbutton" data-options="iconCls:'icon-add2'">添加员工</a>
	      	  	  	  </td>
	      	  	  </tr>
	      	  	   
     		
     		</table>
        </form>  
     </div>
  </body>
</html>
