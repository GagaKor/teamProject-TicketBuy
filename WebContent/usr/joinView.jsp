<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../part/head.jspf"%>

<script>
	var check = "";
	function checkId() {
		var frm = document.jFrm;
		if (frm.userid.value.length < 5) {
			alert("5자 이상의 아이디를 입력하세요.");
			frm.userid.focus();
			return;
		}
		<c:forEach var="item" items="${memberId}">
		if (frm.userid.value == '${item}') {
			alert("중복된 아이디 입니다.");
			return false;
		}
		</c:forEach>
		check = frm.userid.value;
		alert('사용가능한 아이디 입니다.');
	}
	function validCheck() {
		var frm = document.jFrm;
		if (frm.userid.value.length < 5) {
			alert("5자 이상의 아이디를 입력하세요.");
			frm.userid.focus();
			return;
		}
		if (frm.userid.value != check) {
			alert("아이디 중복확인 해주세요.");
			frm.userid.focus();
			return;
		}
		if (frm.userpw.value.length < 6) {
			alert("6자 이상의 비밀번호를 입력하세요.");
			frm.userpw.focus();
			return;
		}
		if (frm.reUserpw.value != frm.userpw.value) {
			alert("비밀번호를 확인해 주세요.");
			frm.reUserpw.focus();
			return;
		}
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


<div class="login_con_j">

		<form name="jFrm" method="post" action="./join.do"
			class="shadow">
			<h1>회원가입</h1>
			<div>
				<label>사용자ID</label> <input type="text" name="userid" required />
				<button type="button" class="btn_s" onclick="checkId()">
					중복<i class="fas fa-check"></i> 
				</button>
			</div>
			<div>
				<label>비밀번호</label> <input type="password" name="userpw" required />
			</div>
			<div>
				<label>비밀번호 확인</label> <input type="password" name="reUserpw" required />
			</div>
			<div>
				<label>핸드폰번호</label> <input type="text" name="phone" placeholder="'-' 제외하고 입력하세요" required />
			</div>
			<div class="login_box">
				<button type="button" class="btn" onclick="validCheck()">
					<i class="fas fa-check"></i> 회원가입
				</button>
				<a href="./main.do" class="btn">취소</a>

			</div>

		</form>

	</div>

<%@ include file="../part/foot.jspf"%>