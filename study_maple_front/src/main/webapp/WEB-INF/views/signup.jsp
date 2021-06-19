<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="include/head.jsp"/>

</head>
<style>
#wrap {
	width: 50%;
	margin-left: auto;
	margin-right: auto;
	text-align: left;
}

table {
	border: 3px solid skyblue
}

td {
	border: 1px solid skyblue
}

#title {
	background-color: skyblue
}
</style>
<script type="text/javascript">
	$(function(){
		setDateYear();
		setDateMonth();
		setDateDay();
		
		$("#mbr_email").on("change",function() {
			if($(this).val() == "naver" || $(this).val() == "gmail" || $(this).val() == "nate" || $(this).val() == "daum"){
				$("input[name= email_1]").attr('disabled', true);
			} else {
				$("input[name= email_1]").attr('disabled', false);
			}
		})
	});   
	
	// select box 연도 , 월 표시
	function setDateYear(){
	    var dt = new Date();
	    var year = "";
	    var com_year = dt.getFullYear();
	    // 발행 뿌려주기
	    $("#year").append("<option value=''>년도</option>");
	    // 올해 기준으로 -1년부터 +5년을 보여준다.
	    for(var y = (com_year-100); y <= (com_year); y++){
	        $("#year").append("<option value='"+ y +"'>"+ y + "년" +"</option>");
	    }
	}
	
	function setDateMonth(){
		// 월 뿌려주기(1월부터 12월)
	    var month;
	    $("#month").append("<option value=''>월</option>");
	    for(var i = 1; i <= 12; i++){
	    	if(i<10){
	    		$("#month").append("<option value='0"+ i +"'>"+ i + "월" +"</option>");
	    	} else{
	    		$("#month").append("<option value='"+ i +"'>"+ i + "월" +"</option>");
	    	}
	    }
	}
	
	function setDateDay(){
		 // 일 뿌려주기(1일부터 31일)
	    var day;
	    $("#day").append("<option value=''>일</option>");
	    for(var i = 1; i <= 31; i++){
	    	if(i<10){
	    		$("#day").append("<option value='0"+ i +"'>"+ i + "일" +"</option>");
	    	} else{
	    		$("#day").append("<option value='"+ i +"'>"+ i + "일" +"</option>");
	    	}
	    }
	}

	function signup(){
		var jsonObj = new Object();
		
		if(commonjs.isEmpty($("select[name='email_2']").val())){
			jsonObj.mbr_email = $("input[name='mbr_email']").val() + "@" + $("input[name='email_1']").val();
		} else{
			jsonObj.mbr_email = $("input[name='mbr_email']").val() + "@" + $("select[name = 'email_2']").val();
		}
		
		jsonObj.mbr_pass = $("input[name='mbr_pass']").val();
		jsonObj.mbr_pass_chk = $("input[name='mbr_pass_chk']").val();
		jsonObj.mbr_phone = $("input[name='mbr_phone']").val();
		jsonObj.mbr_birth = $("select[name='year']").val() + $("select[name='month']").val() + $("select[name='day']").val();
		jsonObj.mbr_zip = $("input[name='mbr_zip']").val();
		jsonObj.mbr_adress = $("input[name='mbr_adress']").val();
		
		if (commonjs.isEmpty($("input[name='mbr_email']").val())) {
			alert("이메일을 입력해주세요");
			$("input[name='mbr_email']").focus();
			return false;
		}
		
		if (commonjs.isEmpty($("input[name='mbr_pass']").val())) {
			alert("비밀번호를 입력해주세요");
			$("input[name='mbr_pass']").focus();
			return false;
		}
		
		if (commonjs.isEmpty($("input[name='mbr_pass_chk']").val())) {
			alert("비밀본호 확인을 입력해주세요");
			$("input[name='mbr_pass_chk']").focus();
			return false;
		}
		
		if (commonjs.isEmpty($("input[name='mbr_phone']").val())) {
			alert("전화번호를 입력해주세요");
			$("input[name='mbr_phone']").focus();
			return false;
		}
		
		if (commonjs.isEmpty($("select[name='year']").val())) {
			alert("생년월일 년을 입력해주세요");
			$("input[name='year']").focus();
			return false;
		}
		if (commonjs.isEmpty($("select[name='month']").val())) {
			alert("생년월일 월을 입력해주세요");
			$("input[name='month']").focus();
			return false;
		}
		if (commonjs.isEmpty($("select[name='day']").val())) {
			alert("생년월일 일을 입력해주세요");
			$("input[name='day']").focus();
			return false;
		}
		
		if (commonjs.isEmpty($("input[name='mbr_zip']").val())) {
			alert("우편번호를 입력해주세요");
			$("input[name='mbr_zip']").focus();
			return false;
		}
		
		if (commonjs.isEmpty($("input[name='mbr_adress']").val())) {
			alert("주소를 입력해주세요");
			$("input[name='mbr_adress']").focus();
			return false;
		}
		
		if ($("input[name='mbr_pass']").val() != $("input[name='mbr_pass_chk']").val()) {
			alert("비밀번호가 일치하지않습니다.");
			$("input[name='mbr_pass_chk']").focus();
			return false;
		}
		console.log(JSON.stringify(jsonObj));
		 $.ajax({
			url: "/mbrsignup",
			type: "POST",
			cache: false,
			contentType: "application/json",
			data: JSON.stringify(jsonObj),
			success: function(data){
				if (data.code == 200) {
					alert(data.message);
					location.href="/index";
				} else {
					alert('회원가입에 실패하였습니다.');
				}
			},
			error: function (request, status, error){
				alert('회원가입에 실패하였습니다');
			}
		}); 
	}
//취소 버튼 클릭시 로그인 화면으로 이동
function goLoginForm() {
    location.href="/index";
}
function searchZip(){
	new daum.Postcode({
	    oncomplete: function(data) {
	        // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
	        // http://postcode.map.daum.net/guide 에서 예제를 활용하여 커스텀

			$('input[name=mbr_zip]').val(data.zonecode);      // 우편번호(5자리)
			$('input[name=mbr_adress]').val(data.address);       // 기본주소
	    }
	}).open();
}



</script>
<body>
	<!-- 왼쪽, 오른쪽 바깥여백을 auto로 주면 중앙정렬된다.  -->
	<div id="wrap">
		<br>
		<br> <b><font size="6" color="gray">회원가입</font></b> <br>
		<br>
		<br>
		<form>
			<table>
				<tr>
					<td id="title">이메일</td>
					<td>
					<input type="text" name="mbr_email" maxlength="30">@
						<select name="email_2" id = "mbr_email">
							<option value ="">직접입력</option>
							<option value ="naver">naver.com</option>
							<option value ="daum">daum.net</option>
							<option value ="gmail">gmail.com</option>
							<option value ="nate">nate.com</option>
						</select>
						<input type="text" name="email_1" maxlength="30">
						<input type="button" value="중복확인">
					</td>
				</tr>
				<tr>
					<td id="title">이름</td>
					<td><input type="text" name="mbr_name" maxlength="40"></td>
				</tr>
				<tr>
					<td id="title">비밀번호</td>
					<td><input type="password" name="mbr_pass" maxlength="15">
					</td>
				</tr>

				<tr>
					<td id="title">비밀번호 확인</td>
					<td><input type="password" name="mbr_pass_chk" maxlength="15">
					</td>
				</tr>
				<tr>
					<td id="title">휴대전화</td>
					<td><input type="text" name="mbr_phone" /></td>
				</tr>
				<tr>
					<td id="title">생년월일</td>
					<td>
						<input type="hidden" name="mbr_birth">
					<select id="year" name ="year"  title="년도" ></select>
					<select id="month"  name ="month" title="월" ></select>
					<select id="day"  name ="day"title="일" ></select>
					
					</td>
				</tr>
				<tr>
					<td id="title">주소</td>
					<td><input type="text" size="10" name="mbr_zip" placeholder="우편번호"/>
						<input type="button" value="검색" onclick="searchZip(); return false;">
						<input type="text" size="30" name="mbr_adress" />
					</td>
				</tr>

			</table>
			<br> <input type="button" value="가입" onclick="signup();"/> <input type="button" value="취소">
		</form>
	</div>
</body>
</html>