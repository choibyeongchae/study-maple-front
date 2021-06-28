<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../include/head.jsp"/>
<title>Insert title here</title>
</head>
<script type="text/javascript">

$(function() {
	if (!commonjs.isEmpty('${userInfo}')) {
		location.href="/home";
	}
})

function doLogin() {
	
	var jsonObj = new Object();
	
	if(commonjs.isEmpty($("input[name=email]").val())) {
		alert("이메일을 입력 해 주세요");
		$("input[name=email]").focus();
		return;
	}
	
	if(commonjs.isEmpty($("input[name=password]").val())) {
		alert("패스워드를 입력 해 주세요");
		$("input[name=password]").focus();
		return;
	}
	
	jsonObj.mbr_email = $("input[name='email']").val();
	jsonObj.mbr_pass = $("input[name='password']").val();
	
	$.ajax({
		type : "POST",
		url : "/login",
		data : JSON.stringify(jsonObj),
		cache : false,
		contentType : 'application/json',
		aync: false,
		success : function(data) {
			location.href="/home";
		},
		error : function(error) {
			alert("아이디 혹은 비밀번호를 확인 해 주세요");
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