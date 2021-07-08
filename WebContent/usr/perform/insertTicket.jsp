<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ include file="../../part/head.jspf" %>
<style type="text/css">
.thumnail{ padding-bottom: 10px; border: none; } 
.thumnail img{ width: 100%; height: 100%; object-fit: cover; margin-bottom: 10px; } 
.container{ width:100%; height:auto; float: left; border: none;}
</style>
<script type="text/javascript">
function valCheck(){
	var frm=document.insertFrm;
	if(frm.ticketPrice.value == ""){
		alert('티켓 가격을 입력하세요.');
		return;
		}
 	frm.submit();
}
</script>
<hr />
<div style="width: 990px; margin: 0 auto; position: relative;">
<div style="float: left; margin-left:50px; width: 40%; height:1000px;">
<form action="./insertTicket.do?" method="post" name="insertFrm" onsubmit="valCheck();">
<input type="hidden" name="performId" value="${perform.performId}">

<div class="container">
	<div class="thumnail">
	<img src="${pageContext.request.contextPath}/static/img/${perform.performImg}">
	</div>
	<div>
	<p><strong>${perform.performName }</strong></p>
	</div>
</div>
<hr />
<table style="width: 100%;">
<tr>
    <th>출연진</th>
	<c:forEach var="actor" items="${actorList }">
	<td>
    <b>${actor.name}</b> <br />
    (${actor.part})
	</td>	
	</c:forEach>
  </tr>
</table>
<hr />
<table style="height:70%; width: 100%;">  
  <tr>
    <td></td>
  </tr>
  <tr>
    <th>장소</th>
    <td>${place.placeaddr}</td>
  </tr>
  <tr>
    <th>기간</th>
    <td> <fmt:formatDate value="${startDate}" type="date"/> ~  <fmt:formatDate value="${endDate}" type="date"/> </td>
  </tr>
  <tr>
    <th>관람시간</th>
    <td>${perform.runTime }</td>
  </tr>
  <tr>
    <th>가격</th>
    <td><input type="text" name="ticketPrice" placeholder="가격을 입력하세요."/>원 <br /><br />
     &nbsp; <input type="button" value="등록" onclick="valCheck()" />&nbsp;
     <input type="button" value="취소" onclick="location.href='${pageContext.request.contextPath}/usr/perform/deletePerform.do?performId=${perform.performId}'"/></td>
  </tr>
</table>
</form>
</div>

<section style="float: right; margin-right:50px; width: 40%;">
<h3>공연 일정</h3>
<table>
 <c:forEach var="sch" items="${schList}">
  <tr>
    <td>일정</td>
    <td><fmt:formatDate value="${sch.schDate}" type="both"/> </td>
  </tr>
 </c:forEach>
</table>
</section>
</div>


<%@ include file="../../part/foot.jspf" %>
