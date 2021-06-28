<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/53a8c415f1.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="./login.css">
     <link rel="stylesheet" href="/static/css/login.css" type="text/css">
<title>Insert title here</title>
</head>
<script type="text/javascript">

$(function() {
	if (!commonjs.isEmpty('${userInfo}')) {
		location.href="/";
	}
})

function doLogin() {
	var host =  window.location.hostname;
	
	var jsonObj = new Object();
	jsonObj.email = $("input[name='email']").val();
	jsonObj.pass = $("input[name='password']").val();
	
	$.ajax({
		type : "POST",
		url : "/login",
		data : JSON.stringify(jsonObj),
		cache : false,
		contentType : 'application/json',
		aync: false,
		success : function(response,textStatus,request) {
			location.href="/";
		},
		error : function(jqXHR, status, e) {
			alert("로그인에 실패하였습니다.");
		}
	});
}


</script>
<body>
<div class="wrap">
        <div class="login">
            <h2>Log-in</h2>
            <div class="login_sns">
            </div>
            <div class="login_id">
                <h4>E-mail</h4>
                <input type="email" name="email" id="inputemail" placeholder="Email">
            </div>
            <div class="login_pw">
                <h4>Password</h4>
                <input type="password" name="password" id="inputpass" placeholder="Password">
            </div>
            <div class="login_etc">
                <div class="checkbox">
                <input type="checkbox" name="" id=""> Remember Me?
                </div>
                <div class="forgot_pw">
                <a href="">Forgot Password?</a>
            </div>
            </div>
            <div class="submit">
                <input type="button" onclick="doLogin();" value="login">
            </div>
        </div>
    </div>
</body>
</html>