<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../part/head.jspf"%>
<script>
		function sendit() {
			var frm = document.loginForm;
			var userid = frm.userid;
			var userpw = frm.userpw;

			if (userid.value == "") {
				alert("아이디를 입력하세요!");
				userid.focus();
				return false;
			}
			if (userpw.value == "") {
				alert("비밀번호를 입력하세요!");
				userpw.focus();
				return false;
			}
			frm.submit();
		}
	</script>
<style>
form {
	padding: 10px 40px 40px 40px;
	
}

label {
	display: inline-block;
	width: 60px;
	text-align: right;
	margin-right: 4px;
}

form div {
	margin-top: 20px;
}

input {
	width: 150px;
}
</style>
	<script src="https://kit.fontawesome.com/68abb170e0.js"
		crossorigin="anonymous"></script>
	<div class="login_con">

		<form name="loginForm" method="post" action="./login.do"
			class="shadow">
			<h1>로그인</h1>
			<div>
				<label>사용자ID</label> <input type="text" name="userid" required />
			</div>
			<div>
				<label>비밀번호</label> <input type="password" name="userpw" required />
			</div>
			<div class="login_box">
				<button type="button" class="btn" onclick="sendit()">
					<i class="fas fa-check"></i> 로그인
				</button>
				<a href="./findUser.do?f=" class="btn">아이디/비밀번호 찾기</a>
				<a href="./join.do" class="btn">회원가입</a>
			</div>

		</form>

	</div>

	<%@ include file="../part/foot.jspf"%>