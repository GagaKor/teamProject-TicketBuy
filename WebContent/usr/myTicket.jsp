<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../part/head.jspf"%>

<div style="width: 990px; margin: 0 auto; position: relative;">
<br />
<br />
	<div class="detail_box_top">
		<c:if test="${!empty list}">
		<h2 style="text-align: center;">Reservation</h2>
			<c:forEach var="list" items="${list}">
				<div class="detail_info">
				<div class="bx_thumb">
				<div class="bx_img">
					<img
						src="${pageContext.request.contextPath}/static/img/${list.perform.performImg }"
						width="300" height="350">
				</div>
				
				</div>
				<div class="etc_info">
				<hr />
					<div class="bx_dummy">
						<em class="info_tit">예매번호</em> <span
							class="txt" style="font-size: 1.2em;">${list.reservationNo}
						</span>
					</div>
					<br />
					<div class="bx_dummy">
						<em class="info_tit">종류</em> <span class="txt">${list.categoryName}
						</span>
					</div>
					<br />
					<div class="bx_dummy">
						<em class="info_tit">제목</em> <span class="txt">${list.perform.performName}
						</span>
					</div>
					<br />
					<div class="bx_dummy">
						<em class="info_tit">장소</em> <span class="txt">${list.place.placeName}
						</span>
					</div>
					<br />
					<div class="bx_dummy">
						<em class="info_tit">주소</em> <span class="txt">${list.place.placeaddr}
						</span>
					</div>
					<br />
					<div class="bx_dummy">
						<em class="info_tit">일정</em> <span class="txt"><fmt:formatDate
								value="${list.sch.schDate}" pattern="yyyy/MM/dd HH:mm" /> </span>
					</div>
					<hr />
				</div>
				</div>
			</c:forEach>
		</c:if>
		<c:if test="${!empty em}">
			<h3 style="text-align: center;">예매 내역</h3>
			<div class="bx_dummy">
				<em class="info_tit" style="font-size: 1.5em;">예매 내역이 없습니다.</em>
			</div>
		</c:if>
	</div>
</div>

<%@ include file="../part/foot.jspf"%>