<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>登录</title>
  
  <script type="text/javascript" src="../vue.js"></script>
  <script type="text/javascript" src="../vue-resource.js"></script>
  <script src="../layui.js"></script>
  <link rel="stylesheet" href="../css/layui.css" id="layui">
  <style type="text/css">
  	.window{
  		width: 400px;
  		position: absolute;
  		margin-left: -200px;
  		margin-top: -80px;
  		top: 30%;
  		left: 50%;
  		display: block;
  		z-index: 100;
  		background: #fff;
  		padding: 20;
  	}
  
  	.title{
  		text-align: center;
  		font-size: 20px;
  		padding-top: 30px;
  	}
  </style>
  
</head>

<body style="background:#f1f1f1;">
	<div class="window">
		<div class="title">
			登录界面
		</div>
		<form class="layui-form" method="post">
			<div class="layui-form-item" style="margin-top:20px;margin-right: 100px;">
				<label class="layui-form-label">用户名：</label>
				<div class="layui-input-block">
					<input type="text" id="name" name="user" required lay-verify="required" placeholder="请输入用户名" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item" style="margin-right: 100px;">
				<label class="layui-form-label">密码：</label>
				<div class="layui-input-block">
					<input type="password" id="password" name="password" required lay-verify="required" placeholder="请输入密码" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item" style="margin-right: 100px;" id="authBox">
				<label class="layui-form-label">权限：</label>
				<div class="layui-input-block" style="width: 190px;">
					<select name="auth" id="auth" lay-verify="required">
						<option value="注册用户">注册用户</option>
						<option value="管理员">管理员</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item" id="regBox">
				<div style="text-align: right;margin-right: 100px;">
					<a href="javascript:reg();">我要注册</a>
				</div>
			</div>
			<div class="layui-form-item">
				<div style="text-align: center;">
					<input type="button" id="loginBtn" onclick="login()" class="layui-btn" value="登&nbsp;&nbsp;&nbsp;&nbsp;录">
					<input type="button" id="regBtn" onclick="handleReg()" class="layui-btn" value="注&nbsp;&nbsp;&nbsp;&nbsp;册" style="display: none;">				
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		layui.use(['form','jquery','layer'],function(){
			window.$ = layui.$;
			layer = layui.layer;
		});

		/* 登录 */
		function login(){
			var name = $("#name").val();
			var password = $("#password").val();
			var auth = $("#auth").val();
			if(!name){
				alert("用户名不允许为空！");
				return;
			}
			if(!password){
				alert("密码不允许为空！");
				return;
			}
			$.post('/users/login.do',{
				name:name,
				password:password,
				auth:auth
			},function(data){
				if(data.code < 0){
					alert(data.errMsg);
				}else if(data.code == 1){
					location.href = "/front/index.do";
				}else{
					location.href = "/#/";
				}
			},'json');
		}

		/* 显示注册  */
		function reg(){
			$(".title").html("注册界面");
			$("#authBox").hide();
			$("#regBox").hide();
			$("#loginBtn").hide();
			$("#regBtn").show();
		}

		/* 注册 */
		function handleReg(){
			var name = $("#name").val();
			var password = $("#password").val();
			$.post("/users/reg.do",{
				name:name,
				password:password
			},function(data){
				if(data.code < 0){
					alert(data.errMsg);
				}else{
					alert("注册成功！");
					location.href="/front/login.jsp";
				}
			});
		}	
</script>
</body>

</html>


