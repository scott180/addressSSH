<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>通讯录</title>
</head>
<%
	String path = request.getContextPath();
	String ctx = request.getScheme() + "://"
	        + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
%>
<script type="text/javascript">
 var ctx = '<%= ctx %>';
</script>
<script type="text/javascript" src="<%= ctx %>/js/jquery.js"></script>
<script type="text/javascript" src="<%= ctx %>/js/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%= ctx %>/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%= ctx %>/themes/icon.css">
<script type="text/javascript" src="<%= ctx %>/jsp/address.js"></script>
<script type="text/javascript" src="<%= ctx %>/jsp/addressList.js"></script>


<body>
	<!-- 页面布局  人员列表、通讯录 -->
	<div id="cc" class="easyui-layout" style="width:1350px;height:600px;">   
	    <div data-options="region:'west',title:'人员列表',split:true" style="width:450px;">
	    	<table id="dgUserList"  style="width:420px;height:600px"></table>  
	    </div>   
	   <div data-options="region:'center',title:'通讯录'" style="padding:5px;background:#eeffff;" id="addrLayout">
	    	<table id="dgAddressList" style="height:560px"></table>  
	    </div>   
	</div>  
	
	<!-- 用户列表操作：增删改 -->
	<!-- <div id="tb">
	<a href="javascript:void(0);" onclick="addUser()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"/a>
	<a href="javascript:void(0);" onclick="editUser()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true"/a>
	<a href="javascript:void(0);" onclick="deleteUser()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"/a>
	</div> -->
	
	<!-- 用户对话框 -->
	<div id="userDialog" style="display:none" >
	<br/><br/>
	<center>
		<form id="userForm" method="post" >
		<table border="1" cellpadding="8" cellspacing="0" id="userTable" >
		<tr>
			<td>姓名</td>
			<td> <input type="text" style='width:80px;' id="userName" name="userName"></td>
		</tr>
		<tr>
			<td>城市</td>
			<td>
				<!-- <select id="cityList"  style='width:80px;' name="cityList"></select> -->
				<input id="cityList"  name="cityList" style='width:80px;'> 
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<input type="reset" id="clearUser" value="重置" onclick="btclearUser()">  
			<!-- <button id="clearUser" onclick="btclearUser()">重置</button>  -->
			<button id="userSubmit" onclick="subUser()">提交</button>
			</td>
		</tr>
		</table>
		</form>
		</center>
	</div>  
	
	<!-- 通讯录用户对话框 -->
	<div id="userDialogAddr" style="display:none" >
	<br/><br/>
	<center>
		<form id="userFormAddr" method="post" >
		<input type="hidden" id="userIdAddr">
		<input type="hidden" id="addrId">
		<table border="1" cellpadding="8" cellspacing="0" id="userTableAddr" >
		<tr>
			<td>姓名</td>
			<td width="100px"> <input type="text" style='width:77px;' id="userNameAddr" name="userNameAddr" onblur="checkNameAddr()">
			<span id="nameSpan"></span></td>
		</tr>
		<tr>
			<td>电话</td>
			<td> <input type="text" style='width:77px;' id="telAddr" name="telAddr" onblur="checkTelAddr()">
			<span id="telSpan"></span></td>
		</tr>
		<tr>
			<td>email</td>
			<td> <input type="text" style='width:77px;' id="emailAddr" name="emailAddr" onblur="checkEmailAddr()">
			<span id="emailSpan"></span></td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
			<input type="reset" id="clearUserAddr" value="重置" onclick="btclearUserAddr()">  
			<!-- <button id="clearUser" onclick="btclearUser()">重置</button>  -->
			<button id="userSubmitAddr" onclick="subUserAddr()">提交</button>
			</td>
		</tr>
		</table>
		</form>
		</center>
	</div> 
</body>
</html>