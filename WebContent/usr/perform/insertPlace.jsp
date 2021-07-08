<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/jsp/static/common.css" />
<script type="text/javascript">
	function valCheck() {
		var frm = document.pFrm;
		if (frm.placeName.value == "") {
			alert("장소명을 입력하세요");
			return;
		}
		if (frm.placeaddr.value == "") {
			alert("주소를 입력하세요");
			return;
		}
		if (frm.phonNum.value == "") {
			alert("전화번호를 입력하세요");
			return;
		}
		if (frm.totalSeatNum.value == "") {
			alert("총 좌석 개수를 입력하세요");
			return;
		}
		if (frm.placeRow.value == "") {
			alert("행을 입력해주세요.");
			return;
		}
		if (frm.placeCol.value == "") {
			alert("열을 입력해주세요.");
			return;
		}
		frm.submit();
	}
</script>

<body style="margin: auto;">

	<form action="./insertPlace.do" method="post" name="pFrm">
		<input type="text" name="placeName" size="30" placeholder="장소이름">
		&nbsp; <input type="text" name="placeaddr" size="45" placeholder="주소">
		<br> <input type="text" name="phonNum" size="20"
			placeholder="전화번호"> &nbsp; <input type="text"
			name="totalSeatNum" size="20" placeholder="총 좌석 갯수"> &nbsp;
		<input type="text" name="placeRow" size="20" placeholder="행">
		&nbsp; <input type="text" name="placeCol" size="20" placeholder="열">
		&nbsp; <input type="button" value="등록" onclick="valCheck()"> <input
			type="button" value="뒤로가기" onclick="history.back()"><br>
	</form>

</body>
</html>
