<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../../part/head.jspf"%>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function valCheck() {
		var frm = document.sFrm;
		var today = new Date();
		today = getFormatDate(today);
		if (frm.startDate.value == "") {
			alert("시작 날짜를 입력하세요.");
			return;
		}
		if (frm.endDate.value == "") {
			alert("종료 날짜를 입력하세요.");
			return;
		}
		if (frm.startDate.value.valueOf() <= today.valueOf()) {
			alert("오늘 이후 날짜를 입력하세요.");
			return;
		}
		if (frm.endDate.value.valueOf() < frm.startDate.value.valueOf()) {
			alert("종료 날짜를 시작 날짜 이후로 입력하세요.");
			return;
		}
		for (var i = 0; i < frm.sTime.length; i++) {
			if (frm.sTime[i].value == "") {
				alert("스케쥴 시간 입력하세요.");
				return;
			}
		}
		frm.submit();

	}
	function getFormatDate(date) {
		var year = date.getFullYear();
		var month = (1 + date.getMonth());
		month = month >= 10 ? month : '0' + month;
		var day = date.getDate();
		day = day >= 10 ? day : '0' + day;
		return year + '-' + month + '-' + day;
	}
	$(document)
			.ready(
					function() {
						$('.btnAdd')
								.click(
										function() {
											$('.buttons')
													.append(
															'<label > 시간 선택 : <input type="time" name="sTime" /> </label> <input type="button" class="btnRemove" value="삭제"><br>'); // end append                            
											$('.btnRemove')
													.on(
															'click',
															function() {
																$(this)
																		.prev()
																		.remove(); // remove the textbox
																$(this)
																		.next()
																		.remove(); // remove the <br>
																$(this)
																		.remove(); // remove the button
															});
										}); // end click                                            
					}); // end ready
</script>
<div style="width: 990px; margin: 0 auto; position: relative;">
<form action="./insertSchSeat.do" method="post" name="sFrm">
	<h3>좌석 배치 미리보기</h3>
	<table>
		<%
		int placeRow = (int) (request.getAttribute("placeRow"));
		int placeCol = (int) (request.getAttribute("placeCol"));
		String[] c = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "k" };
		for (int i = 1; i <= placeCol; i++) {
		%>
		<tr>
			<%
			for (int j = 1; j <= placeRow; j++) {
				String seatName = c[i - 1] + "-" + j;
			%>
			<td><%=seatName%></td>
			<%
			}
			%>
		</tr>
		<%
		}
		%>
	</table>
	<input type="hidden" name="performId" value="${performId}" /> <input
		type="hidden" name="placeId" value="${placeId}" /> 기간 선택 : <input
		type="date" name="startDate" /> ~ <input type="date" name="endDate" />
	<br /> <input type="hidden" name="sTime" value="23%3A00" />
	<div class="buttons">
		<label> 시간 선택 : <input type="time" name="sTime" />
		</label> <input type="button" class="btnAdd" value="추가"><br>
	</div>



	<input type="button" value="확인" onclick="valCheck()"> <input
		type="button" value="취소"
		onclick="location.href='${pageContext.request.contextPath}/usr/perform/deletePerform.do?performId=${performId}'"><br>
</form>

</div>
<%@ include file="../../part/foot.jspf"%>
