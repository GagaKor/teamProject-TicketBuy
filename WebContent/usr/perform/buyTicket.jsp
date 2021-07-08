<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../part/head.jspf"%>


<div class="con" >
<div style="padding-top: 40px;">
<div style="font-size: 30px; text-align: center;">구매 완료</div>
<hr />
	<c:forEach var="reIdList" items="${reIdList }">
		<div class="bx_dummy" style="text-align: center; margin: 30px;">
			
			<em class="info_tit">예매번호</em> <span class="txt"
				style="font-size: 1.5em;">${reIdList} </span>
		</div>	
	</c:forEach>
	<div style="text-align: center; margin-top: 30px;">
		<span class="txt" style="font-size: 20px; ">이용해주셔서 감사합니다. </span>
	</div>
	<hr>
	<div class="bt_wrap">
		<a href="../main.do" class="on">홈으로</a>
</div>
	</div>
</div>






<%@ include file="../../part/foot.jspf"%>