<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP '01-layout.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- easyui -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
	<!-- ztree -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/ztree/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.all-3.5.js"></script>
	<script type="text/javascript">
		$(function(){
			//alert
			//$.messager.alert("标题","内容","info");
			/* $.messager.confirm("提示信息","你确定删除吗？",function(r){
					$.messager.alert("提示",r,"info");
			}); */
			$.messager.show({
				title:"欢迎",
				msg:"欢迎root登陆系统",
				timeout:3000,
				showType:'slide'
			});
		});
	</script>
  </head>
  
  <body class="easyui-layout">
    <!-- 使用div描述每个区域 data-options="region:'north'" -->
    <div title="物流管理系统" style="height: 100px" data-options="region:'north'">
    	<!-- 制作下拉菜单 -->
    	<a data-options="iconCls:'icon-help',menu:'#mm'" class="easyui-menubutton">控制面板</a>
    	<!-- 使用div制作下拉菜单选项 -->
    	<div id="mm">
    		<div data-options="iconCls:'icon-edit'">修改密码</div>
    		<div>联系管理员</div>
    		<div class="menu-sep"></div>
    		<div>退出系统</div>
    	</div>
    </div>
    <div style="width:200px" data-options="region:'west'">
		<!-- 制作折叠菜单面板accordion data-options="fit:true"填充父面板 -->
		<div class="easyui-accordion" data-options="fit:true">
			<!-- 使用子div表示每个菜单面板-->
			<div title="菜单一"><a id="but1" class="easyui-linkbutton">选项卡</a></div>
			<script type="text/javascript">
				$(function(){
					$("#but1").click(function(){
						//判断选项卡是否存在
						var bool=$("#mytabs").tabs("exists","系统管理");
						if(bool){
							//已经存在，选中即可
							$("#mytabs").tabs("select","系统管理");
						}else{
							$("#mytabs").tabs("add",{
								title:'系统管理',
								iconCls:'icon-edit',
								closable:true,
								content:'<iframe frameborder="0" height="100%" width="100%" src="https://www.baidu.com"></iframe>',
							});
						}
					});
				});
			</script>
			<div title="菜单二">
				<!-- 展示ztree效果:标准json构建 -->
				<ul id="ztree1" class="ztree"></ul>
				<script type="text/javascript">
					$(function(){
						//动态创建ztree
						var setting1={};
						//构造节点数据
						var zNodes1=[
						            {"name":"节点1","children":[{"name":"子节点1"}]},//每个json对象表示一个节点数据
						            {"name":"节点2","children":[{"name":"子节点1"}]},
						            {"name":"节点3","children":[{"name":"子节点1"}]}
						            ];
						//调用api初始化ztree
						$.fn.zTree.init($("#ztree1"),setting1,zNodes1);
					});
				</script>
			</div>
			<div title="菜单三">
				<!-- 简单json构建 -->
				<ul id="ztree2" class="ztree"></ul>
				<script type="text/javascript">
					$(function(){
						//动态创建ztree
						var setting2={
								data:{
									simpleData:{
										enable:true//使用简单json构建
									}
								}
						};
						//构造节点数据
						var zNodes2=[
						            {"id":"1","pId":"0","name":"节点1"},//每个json对象表示一个节点数据
						            {"id":"2","pId":"1","name":"节点2"},
						            {"id":"3","pId":"0","name":"节点3"}
						            ];
						//调用api初始化ztree
						$.fn.zTree.init($("#ztree2"),setting2,zNodes2);
					});
				</script>
			</div>
			<div title="菜单四">
				<!-- 发送ajax获取简单json -->
				<ul id="ztree3" class="ztree"></ul>
				<script type="text/javascript">
					$(function(){
						//ajax动态创建ztree
						var setting3={
								data:{
									simpleData:{
										enable:true//使用简单json构建
									}
								},
								callback:{
									//为ztree节点绑定单击事件,treeNode为json对象
									onClick: function(event,treeId,treeNode){
										//动态添加选项卡,如果是不主页面，就添机选项卡
										if(treeNode.page!=undefined){
											//判断选项卡是否存在
											var bool=$("#mytabs").tabs("exists",treeNode.name);
											if(bool){
												//已经存在，选中即可
												$("#mytabs").tabs("select",treeNode.name);
											}else{
												$("#mytabs").tabs("add",{
													title:treeNode.name,
													closable:true,
													content:'<iframe frameborder="0" height="100%" width="100%" src="'+treeNode.page+'"></iframe>',
												});
											}
										}
									}
								}
						};
						//发送ajax请求
						$.post(
							"${pageContext.request.contextPath}/json/menu.json",
							{},
							function(data){
								//调用api初始化ztree
								$.fn.zTree.init($("#ztree3"),setting3,data);
							},
							"json"
						);
					});
				</script>
			</div>
		</div>
	</div>
    <div data-options="region:'center'">
    	<!-- 选项卡面板tabs -->
    	<div id="mytabs" class="easyui-tabs" data-options="fit:true">
			<!-- 使用子div表示每个面板 data-options="closable:true"关闭选项卡-->
			<div title="面板一" data-options="closable:true">
				<table id="myTable"></table>
				<!-- 使用easyUI的api动态创建datagrid -->
				<script type="text/javascript">
					$(function(){
						var myIndex=-1;
						
						$("#myTable").datagrid({
							//定义标题列
							columns:[[
							          {title:'编号',field:'id',checkbox:true},
							          {width:150,title:'姓名',field:'name',editor:{
							        	  							type:'validatebox',
							        	  							options:{}
							          									}},
							          {width:150,title:'年龄',field:'age',editor:{
	        	  													type:'numberbox',
	        	  													options:{}
	          															}},
							          ]],
							url:'${pageContext.request.contextPath}/json/datagrid.json',
							rownumbers:true,
							//定义工具栏
							toolbar:[
							         {text:'添加',iconCls:'icon-add',handler:function(){
							        	 $("#myTable").datagrid("insertRow",{
							        		 index:0,//行索引
							        		 row:{}
							        	 });
							        	 $("#myTable").datagrid("beginEdit",0);
							        	 myIndex=0;
							         }},
							         {text:'修改',iconCls:'icon-edit',handler:function(){
							        	 var rows=$("#myTable").datagrid("getSelections");
							        	 if(rows.length==1){
							        		 var row=rows[0];
							        		 myIndex=$("#myTable").datagrid("getRowIndex",row);
							        	 }
							        	 $("#myTable").datagrid("beginEdit",myIndex);
							         }},
							         {text:'保存',iconCls:'icon-save',handler:function(){
							        	 $("#myTable").datagrid("endEdit",myIndex);
							         }},
							         {text:'删除',iconCls:'icon-search',handler:function(){
							        	 var rows=$("#myTable").datagrid("getSelections");
							        	 if(rows.length==1){
							        		 var row=rows[0];
							        		 myIndex=$("#myTable").datagrid("getRowIndex",row);
							        	 }
							        	 $("#myTable").datagrid("deleteRow",myIndex);
							         }}
							         ],
							//分页,请求时会发送分页参数，page页数,rows每页显示数
							pagination:true,
							//数据表格提供,用于监听结束编辑
							onAfterEdit:function(index,data,changes){
								alert("结束编辑了,"+data.name);
							}
						});
					});
				</script>
			</div>
			<div title="面板二" data-options="closable:true"></div>
			<div title="面板三" data-options="closable:true"></div>
		</div>
    </div>
    <div style="width:100px" data-options="region:'east'">东部</div>
    <div style="height: 50px" data-options="region:'south'">南部</div>
  </body>
</html>
