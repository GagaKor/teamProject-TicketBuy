<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/jsp/static/common.css" />
<script type="text/javascript">
 function placeSearch(){
	 var frm=document.pFrm;
	 if(frm.place.value==""){
		 alert("장소명을 입력하세요");
		 return;
	 }
	 frm.submit();
 }
 function setData(placeName, placeaddr, phonNum, totalSeatNum, placeId) {
	opener.document.insertFrm.placeName.value=placeName;
	opener.document.insertFrm.placeaddr.value=placeaddr;
	opener.document.insertFrm.phonNum.value=phonNum;
	opener.document.insertFrm.totalSeatNum.value=totalSeatNum;
	opener.document.insertFrm.placeId.value=placeId;
	self.close();
}

</script>

<body style="margin: auto;">

<form action="./find.do" method="get" name="pFrm">
	<input type="hidden" name="search" value="y">
	<table>
		<tr>
			<td>장소 입력<input type="text" name="place"> 
						<input type="button" value="검색" onclick="placeSearch()">
						<input type="button" value="장소 추가" onclick="location.href='./insertPlace.do?insert=n'">
			</td>
		</tr>
		<c:if test="${param.search=='y'}"> 
		<c:if test="${empty placeList}"> 
		<tr> <td> ※검색한 결과가 없습니다.※ </td>
		</c:if>
		</c:if>
		
		<c:if test="${!empty placeList }"> 
			
		<c:forEach var = "list" items="${placeList }">
		<tr>
		 <td ><a href="javascript:setData('${list.placeName}', '${list.placeaddr}', '${list.phonNum}','${list.totalSeatNum}','${list.placeId}')">${list.placeName}</a> &nbsp;
		&nbsp; ${list.placeaddr }&nbsp; &nbsp; ${list.phonNum }</td>
		</tr>
		</c:forEach>
		</c:if>
		
		
	</table>
	</form>

</body>
</html>
