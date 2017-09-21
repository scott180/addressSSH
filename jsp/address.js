$().ready(function(){

	initDgUserList();
	type = 0;//全局变量   type=1 修改用户， type=0 添加用户
	typeAddr = 0;//全局变量   type=1  通讯录修改用户，type=0  通讯录添加用户
});

//用户列表初始化
function initDgUserList(){
	var url = ctx+'userList';//http://localhost:8080/AddressSSH/userList
//	alert(url);return;
	$('#dgUserList').datagrid({    
	    url:url,
	    fitColumns:true,
	    pagination:true,
	    columns:[[    
	        {field:'id',title:'<input type="checkbox" />',width:10,align:'center',checkbox:true},
	        {field:'name',title:'<b>姓名</b>',width:100,align:'center',sortable:true},    
	        {field:'cityName',title:'<b>城市</b>',width:100,align:'center',sortable:true},    
	        {field:'opt',title:'<b>操作</b>',width:100,align:'center',
	        	formatter: function(value,row,index){
//	        		console.log(row);
					return "<a href='javascript:void(0);' onclick='addressList("+value+");'>通讯录</a>";
			}
	        }    
	    ]]
	}); 
	 var p = $('#dgUserList').datagrid('getPager');
	 p.css('position','relative');
     $(p).pagination({
         pageSize: 20,//每页显示的记录条数，默认为20  
         pageList: [20, 30, 50,80],//可以设置每页记录条数的列表  
         beforePageText: '第',//页数文本框前显示的汉字  
         afterPageText: '页    共 {pages} 页',
         displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
         buttons: [{
     		iconCls:'icon-add',
     		handler:addOrEditUser
     	},'-',{
     		iconCls:'icon-edit',
     		handler:editUser
     	},'-',{
     		iconCls:'icon-remove',
     		handler:deleteUser
     	}]
     });
}


//用户表单   type=1:修改用户
function addOrEditUser(type){
	window.type=type;
	var title = '添加用户';
	if(type===1){
		title = '修改用户';
	}
	$('#userForm').form('reset');
	$('#userDialog').css('display','block');
	$('#userDialog').dialog({    
	    title: title,    
	    width: 300,    
	    height: 270,  
	    top:100,
	    closed: false,    
	    cache: false,    
	    modal: true
	});
	$('#userName').textbox({    
	    iconCls:'icon-man', 
	    iconAlign:'right',
	    required:true,
	    missingMessage:'姓名不能为空！',
	    validType:'minLength[1]'
	});
	$('#cityList').combobox({    
	    url:ctx+'cityList',    
	    valueField:'id',    
	    textField:'name'
	}); 
	if(type===1){
		var selected = $('#dgUserList').datagrid('getSelected');
		$('#userName').textbox('setValue',selected.name);
		$('#cityList').combobox('setValue', selected.cityId);
	}else{
		$('#cityList').combobox('setValue', '1');
	}
//	$("input",$("#userName").next("span")).blur(checkName);
}

//编辑用户验证
function editUser(){
	var selected = $('#dgUserList').datagrid('getChecked');
	if(selected.length==0){
		$.messager.alert('提示','请选择用户进行修改！');
		return;
	}
	if(selected.length>1){
		$.messager.alert('提示','请选择一个用户进行修改！');
		return;
	}
	addOrEditUser(1);
}

//删除用户
function deleteUser(){
	var selected = $('#dgUserList').datagrid('getChecked');
	if(selected.length==0){
		$.messager.alert('提示','请选择删除的用户！');
		return;
	}
	var ids='';
	for(var i in selected){
		ids+=selected[i].id;
		if(i!=selected.length-1){
			ids+=',';
		}
	}
	$.messager.confirm('确认','您确认想要删除用户吗？',function(r){    
	    if (r){    
	    	$.ajax({
				   type: "get",
				   url: "delUser",
				   data: "ids="+ids,
				   success: function(msg){
					   $('#dgUserList').datagrid('reload');
					   $('#dgAddressList').datagrid('reload');
					   $("#addrLayout").panel({
							title:'通讯录'
				      });
				    	$.messager.show({
				    		title:'提示',
				    		msg:'删除用户成功。',
				    		timeout:2000,
				    		showType:'slide'
				    	});
					 }
				});
	    }    
	}); 
}

//重置
function btclearUser(){
	$('#userName').textbox('setValue','');
	$('#cityList').combobox({    
	    url:ctx+'cityList',    
	    valueField:'id',    
	    textField:'name',
	    value:'1'
	});      
	return;
}

//提交
function subUser(){
	var userName = $.trim($('#userName').textbox('getValue'));
	var city = $('#cityList').combobox('getValue');
	var url = ctx+'saveOrEdit?userId=0&name='+userName+'&city='+city;
	if(window.type==1){
		var selected = $('#dgUserList').datagrid('getSelected');
		url = ctx+'saveOrEdit?userId='+selected.id+'&name='+userName+'&city='+city;
	}
//	alert(url);return;
	$('#userForm').form({    
	    url:url,    
	    onSubmit: function(){    
	    	if(userName.length==0){
	    		$('#userName').textbox('clear');
	    		$("input",$("#userName").next("span")).focus(); 
	    		return false;
	    	}
	    	if(checkName()){
	    		$.messager.alert('提示','姓名重复，请重新输入。');
	    		return false;
	    	}
	    	return true;
	    },    
	    success:function(data){    
	    	$('#userDialog').dialog('close');
	    	$('#dgUserList').datagrid('reload');
	    	$.messager.show({
	    		title:'提示',
	    		msg:'添加用户成功。',
	    		timeout:2000,
	    		showType:'slide'
	    	});
	    	/*if(window.type=1){
		    	  $("#addrLayout").panel({
		    			title:userName+' 通讯录'
		          });
	    	}*/
	    	
	    }    
	});    
}

//检查用户是否重命名
function checkName(){
	var flag;
	var userId=0;
	var userName =  $.trim($('#userName').textbox('getValue'));
	if(window.type==1){
		var selected = $('#dgUserList').datagrid('getSelected');
		userId=selected.id;
		//$.messager.alert('提示',userId+'----'+userName);
	}
	$.ajax({
		   type: "get",
		   url: ctx+"checkName",
		   data: {
			   userId:userId,
			   name:userName
		   },
		   async:false,
		   dataType:'json',
		   success: function(json){
			      flag = json.msg;
			 }
		});
	return flag;
}

//打开通讯录
function addressList(userId){
	var rows = $('#dgUserList').datagrid('getRows'); 
	var title="";
	for(var i in rows){
		if(rows[i].id==userId){
			title+=rows[i].name+' 通讯录';
		}
	}
	  $("#addrLayout").panel({
			title:title
      });
	  initAddressList(userId);
}


