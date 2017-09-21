var right = '<font color="green">√</font>';
var wrong = '<font color="red">x</font>';
//通讯录列表初始化
function initAddressList(userId){
	var url = ctx+'addrList?userId='+userId;
//	alert(url);return;
	$('#dgAddressList').datagrid({    
	    url:url,
	    fitColumns:true,
	    pagination:true,
	    columns:[[    
	        {field:'id',title:'<input type="checkbox" />',width:10,align:'center',checkbox:true},
	        {field:'name',title:'<b>姓名</b>',width:100,align:'center',sortable:true},    
	        {field:'tel',title:'<b>电话</b>',width:100,align:'center',sortable:true}, 
	        {field:'email',title:'<b>电子邮箱</b>',width:100,align:'center',sortable:true}
	        /*{field:'userId',title:'<b>操作</b>',width:100,align:'center',sortable:true}*/
	    ]]
	}); 
	 var pa = $('#dgAddressList').datagrid('getPager');
	 pa.css('position','relative');
     $(pa).pagination({
         pageSize: 20,//每页显示的记录条数，默认为20  
         pageList: [20, 30, 50,80],//可以设置每页记录条数的列表  
         beforePageText: '第',//页数文本框前显示的汉字  
         afterPageText: '页    共 {pages} 页',
         displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
         buttons: [{
     		iconCls:'icon-add',
     		handler:addOrEditUserAddr
     	},'-',{
     		iconCls:'icon-edit',
     		handler:editUserAddr
     	},'-',{
     		iconCls:'icon-remove',
     		handler:deleteUserAddr
     	}]
     });
     $('#userIdAddr').val(userId);
}

//通讯录用户表单   typeAddr=1:修改用户
function addOrEditUserAddr(type){
	window.typeAddr=type;
	var title = '通讯录添加用户';
	if(typeAddr===1){
		title = '通讯录修改用户';
	}
	$('#userFormAddr').form('reset');
	$('#userDialogAddr').css('display','block');
	$('#userDialogAddr').dialog({    
	    title: title,    
	    width: 300,    
	    height: 270,  
	    top:100,
	    closed: false,    
	    cache: false,    
	    modal: true
	});
	
	if(type===1){
		var checked = $('#dgAddressList').datagrid('getChecked');
		var selected=checked[0];
		$('#userNameAddr').val(selected.name);
		$('#telAddr').val(selected.tel);
		$('#emailAddr').val(selected.email);
		
		$('#nameSpan').html(right);
		$('#telSpan').html(right);
		$('#emailSpan').html(right);
		
		$('#userIdAddr').attr("value",selected.userId);
		$('#addrId').attr("value",selected.id);
	}else{
		$('#addrId').val(0);
		$('#nameSpan').html('');
		$('#telSpan').html('');
		$('#emailSpan').html('');
	}
}

//通讯录修改用户
function editUserAddr(){
	var selected = $('#dgAddressList').datagrid('getChecked');
	if(selected.length==0){
		$.messager.alert('提示','请选择用户进行修改！');
		return;
	}
	if(selected.length>1){
		$.messager.alert('提示','请选择一个用户进行修改！');
		return;
	}
	addOrEditUserAddr(1);
}

//通讯录删除用户
function deleteUserAddr(){
	var selected = $('#dgAddressList').datagrid('getChecked');
	if(selected.length==0){
		$.messager.alert('提示','请选择要删除的用户！');
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
				   url: "delAddr",
				   data: "ids="+ids,
				   success: function(msg){
					   $('#dgAddressList').datagrid('reload');
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

//通讯录检查姓名
function checkNameAddr(){
	var userIdAddr = $('#userIdAddr').val();
	var userNameAddr = $.trim($('#userNameAddr').val());
	var addrId = $('#addrId').val();
	var flag=true;
	if(userNameAddr.length==0){
		 $.messager.alert('提示','姓名不能为空！');
		 flag=false;
	}else{
		$.ajax({
			   type: "get",
			   url: ctx+"checkNameAddr",
			   data: {
				   userId:userIdAddr,
				   name:userNameAddr,
				   addrId:addrId
			   },
			   async:false,
			   dataType:'json',
			   success: function(json){
				   if(json.msg){
					   $.messager.alert('提示','姓名已存在，请重新输入！');
	//				   $('#userNameAddr').focus();
					   flag = false;
				   }
				 }
			});
	}
	if(flag){
		$('#nameSpan').html(right);
	}else{
		$('#nameSpan').html(wrong);
	}
	return flag;
}

//通讯录检查电话
function checkTelAddr(){
	var userTelAddr = $.trim($('#telAddr').val());
	var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
	if(!myreg.test(userTelAddr)) { 
		$.messager.alert('提示','请输入有效的手机号码！'); 
		$('#telSpan').html(wrong);
//		 $('#telAddr').focus();
	    return false; 
	} 
	$('#telSpan').html(right);
	return true;
}

//通讯录检查邮箱
function checkEmailAddr(){
	var userEmailAddr = $.trim($('#emailAddr').val());
	var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	 if(!reg.test(userEmailAddr)){
		 $.messager.alert('提示','请输入有效的电子邮件！');
		 $('#emailSpan').html(wrong);
//		 $('#emailAddr').focus();
		 return false;
	 }
	 $('#emailSpan').html(right);
	 return true;
}

//重置
function btclearUserAddr(){
	$('#userNameAddr').val('');
	$('#telAddr').val('');
	$('#emaiAddr').val('');
}

//提交
function subUserAddr(){
	var name = $('#userNameAddr').val();
	var tel= $('#telAddr').val();
	var email = $('#emailAddr').val();
	var userId = $('#userIdAddr').val();
	var addrId = $('#addrId').val();
	var url = ctx+'saveOrEditAddr?addrId=0&name='+name+'&tel='+tel+'&email='+email+'&userId='+userId;
	var msg = '添加用户成功。';
	if(window.typeAddr==1){
		url = ctx+'saveOrEditAddr?addrId='+addrId+'&name='+name+'&tel='+tel+'&email='+email+'&userId='+userId;
		msg = '修改用户成功。';
	}
//	alert(url);return;
	$('#userFormAddr').form({    
	    url:url,    
	    onSubmit: function(){   
	    	if(!checkNameAddr()){
	    		return false;
	    	}
	    	if(!checkTelAddr()){
	    		return false;
	    	}
	    	if(!checkEmailAddr()){
	    		return false;
	    	}
	    	return true;
	    },    
	    success:function(data){    
	    	$('#userDialogAddr').dialog('close');
	    	$('#dgAddressList').datagrid('reload');
	    	$.messager.show({
	    		title:'提示',
	    		msg:msg,
	    		timeout:2000,
	    		showType:'slide'
	    	});
	    }    
	});    
}
