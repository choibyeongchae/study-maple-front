<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function doLogout() {
	$.ajax({
		type : "POST",
		url : "/member/core/logout",
		cache : false,
		contentType : 'application/json',
		aync: false,
		success : function(data) {
			if (data.code == 200) {
				location.href = "/";
			}
		},
		error : function(jqXHR, status, e) {
			alert('로그아웃 처리과정에 문제가 발생하였습니다.');
		}
	});
}
</script>
</head>
<body>
	<!-- Header Section Begin -->
    <header class="header-section">
        <div class="container">
            <div class="logo">
                <a href="./index.html">
                    <img src="img/logo.png" alt="">
                </a>
            </div>
            <div class="nav-menu">
                <nav class="mainmenu mobile-menu">
                    <ul>
                        <li class="active"><a href="./index.html">뉴스</a>
                        	 <ul class="dropdown">
                        	 	<li><a href="#">공지사항</a></li>
                        	 </ul>
                        </li>
						<li><a href="./about-us.html">가이드</a>
							<ul class="dropdown">
								<li><a href="#">직업소개</a></li>
								<li><a href="#">확률형 아이템</a></li>
							</ul></li>

						<li><a href="./speaker.html">커뮤니티</a>
                            <ul class="dropdown">
                                <li><a href="#">자유게시판</a></li>
                            </ul>
                        </li>
                        <li><a href="./speaker.html">랭킹</a>
                            <ul class="dropdown">
                                <li><a href="#">월드 랭킹</a></li>
                                <li><a href="#">유니온 랭킹</a></li>
                            </ul>
                        </li>
                        <li><a href="./schedule.html">고객지원</a>
                        	<li><a href="#">1:1문의/도움말</a></li>
                        </li>
                        <li><a href="./blog.html">마이페이지</a></li>
                        <li><a href="/member/signup">회원가입</a></li>
                    </ul>
                </nav>
                
                <c:choose>
                	<c:when test="${empty userInfo }">
                		<a href="/member/login" class="primary-btn top-btn"> Login</a>
                	</c:when>
                	<c:otherwise>
                		<a href="#" onclick = "doLogout()" class="primary-btn top-btn"> Logout</a>
                	</c:otherwise>
                </c:choose>
                
            </div>
            <div id="mobile-menu-wrap"></div>
        </div>
    </header>
    <!-- Header End -->
</body>
</html>