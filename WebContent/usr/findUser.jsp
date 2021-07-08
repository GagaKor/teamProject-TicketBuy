<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../part/head.jspf"%>

<script>
	function validCheck_id() {
		var frm = document.idFrm;
		if (frm.phone.value.length != 11) {
			alert("올바른 핸드폰 번호를 입력해주세요.");
			frm.phone.focus();
			return;
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
		<c:if test="${empty f}">
			<form name="idFrm" method="post" action="./findUser.do?f=id"
				class="shadow">
				<h1>아이디 비밀번호 찾기</h1>
				<div>
					<label>핸드폰</label> <input type="text" name="phone"
						placeholder="'-' 제외하고 입력하세요" required />
				</div>
				<div class="login_box" style="margin: auto, 10px; text-align: center;">
					<button type="button" class="btn" onclick="validCheck_id()">
						<i class="fas fa-check"></i> 찾기
					</button>
					<a href="./main.do" class="btn">취소</a>
				</div>

			</form>

		</c:if>
		<c:if test="${f == 'rs' }">
			<div class="shadow">
			<hr />
			<div style="text-align: center;">
			<label>아이디</label>
			<label>${memberId}</label>
			</div>
			<hr />
			<div style="text-align: center;">
			<label>비밀번호</label>
			<label>${memberPw}</label>
			</div>
			<hr />
			<div class="login_box" style="margin: auto, 10px; text-align: center;">
				<button type="button" class="btn"
					onclick="location.href='./login.do'">
					<i class="fas fa-check"></i> 로그인
				</button>
				<a href="./main.do" class="btn">홈</a>
			</div>
			</div>
		</c:if>
	</div>

<%@ include file="../part/foot.jspf"%>