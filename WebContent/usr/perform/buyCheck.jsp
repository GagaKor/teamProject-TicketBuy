<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../part/head.jspf"%>
<script type="text/javascript">
function buyConfirm(){
    if (!confirm("예매 하시겠습니까?")) {
    } else {
    	document.cfrm.submit();
    }

}
</script>
<div style="width: 990px; margin: 0 auto; position: relative;">


<br />
<hr />
<br />
	<form action="buyTicket.do" method="post" name = "cfrm">
	<input type="hidden" name="schId" value="${sch.schId}">
	<input type="hidden" name="amount" value="${amount}">
	
	<div class="detail_box_top">
		<h2 style="text-align: center;">구매정보</h2>
		<div class="detail_info">
			<div class="bx_thumb">
				<div class="bx_img">
					<img
						src="${pageContext.request.contextPath}/static/img/${perform.performImg }"
						width="300" height="350">
				</div>
			</div>


			<div class="etc_info">
				<div class="bx_dummy">
					<em class="info_tit">제목</em> <span class="txt" style="font-size: 1.5em;">${categoryName}&lt;${perform.performName}&gt;
					</span>
				</div>
				<hr />
				<div class="bx_dummy">
					<em class="info_tit">장소</em> <span class="txt">${place.placeName}
					</span>
				</div>
				<div class="bx_dummy">
					<em class="info_tit">주소</em> <span class="txt">${place.placeaddr}
					</span>
				</div>
				<hr />
				<div class="bx_dummy">
					<em class="info_tit">일시</em>
					<span class="txt"><fmt:formatDate value="${sch.schDate}" pattern="yyyy/MM/dd HH:mm"/>
					</span>
				</div>
				<hr />
				<div class="bx_dummy">
					<em class="info_tit">매수</em> <span class="txt">${amount}장
					</span>
				</div>
				<hr />
				<div class="bx_dummy">
					<em class="info_tit">총합</em> <span class="txt"><fmt:formatNumber value="${price*amount}" pattern="#,###" />원
					</span>
				</div>
				<hr />
				               <div style="display: flex; justify-content: space-around">
                  <div class="bt_wrap">
                     <a onclick="buyConfirm();" class="on" style="cursor: pointer;">구매하기</a>
                  </div>
                  <div class="bt_wrap">
                     <a onclick="history.back();" class="on" style="cursor: pointer;">취소</a>
                  </div>
               </div>
			</div>
			</div>
			</div>
			</form>		
			
</div>
<br />
<br />

<%@ include file="../../part/foot.jspf"%>