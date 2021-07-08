<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../part/head.jspf"%>
<script type="text/javascript">
	function buyCheck() {
		var frm = document.tFrm;
		if (frm.schId.value == document.getElementById('noValue').value) {
			alert('관람 일정을 선택해 주세요.');
			return;
		}
		frm.submit();

	}
	var amount;
	function init() {
		amount = document.tFrm.amount.value;
		change();
	}

	function add() {
		hm = document.tFrm.amount;
		sum = document.tFrm.sum;
		hm.value++;
	}

	function del() {
		hm = document.tFrm.amount;
		if (hm.value > 1) {
			hm.value--;
		}
	}
</script>
<div style="width: 990px; margin: 0 auto; position: relative;">

	<br />
	<hr />
	<div class="detail_box_top">
		<div class="detail_info">
			<div class="bx_thumb">
				<div class="bx_img">
					<img
						src="${pageContext.request.contextPath}/static/img/${detail.perform.performImg }"
						width="300" height="350">
				</div>
			</div>


			<div class="etc_info">
				<div class="bx_dummy">
					<em class="info_tit">제목</em> <span class="txt">${detail.categoryName}&lt;${detail.perform.performName}&gt;
					</span>
				</div>
				<div class="bx_dummy">
					<em class="info_tit">장소</em> <span class="txt">${detail.place.placeName}
					</span>
				</div>
				<div class="bx_dummy">
					<em class="info_tit">주소</em> <span class="txt">${detail.place.placeaddr}
					</span>
				</div>

				<div class="bx_dummy">
					<em class="info_tit" style="font-size: 0.8em;">관람시간</em> <span class="txt">${detail.perform.runTime}
					</span>
				</div>
				<div class="bx_dummy">
					<em class="info_tit">일시</em> <span class="txt">${detail.seasonDate}
					</span>
				</div>
				<br />
				<hr />
				<div class="bx_dummy">
					<em class="info_tit">가격</em> <span class="txt"><fmt:formatNumber
							value="${detail.price}" pattern="#,###" />원 </span>
				</div>
				<hr />
			</div>
			<!-- 티켓 구매 예매 하기 -->
			<form action="buyCheck.do" method="post" name="tFrm">
				<table style="width: 500px;">
					<tr>
						<td>
							<div class="bx_dummy">
								<em class="info_tit">일정</em>
							</div>
						</td>
						<td>
							<div>
								<select name="schId" id="#" style="margin-left: 70px;">
									<option id="noValue">----------일정을 선택하세요----------</option>
									<c:forEach var="sch" items="${detail.schList}"
										varStatus="status">
										<option value="${sch.schId}">
											<fmt:formatDate value="${sch.schDate}"
												pattern="yyyy/MM/dd HH:mm" /> (남은 좌석 :
											${detail.remainSeat[status.index]})
										</option>
									</c:forEach>
								</select>
							</div>
					</tr>
					<tr>
						<td colspan="2"
							style="border-top: 1px solid black; text-align: center;"><a
							href="#" class="num" onclick="del();"> ㅡ </a> <input type="text"
							name="amount" value="1" size="3" onchange="change();"
							style="text-align: right; width: 30px;" /> <a href="#"
							class="num" onclick="add();" style="font-size: 1.2em;"> + </a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<div class="bt_wrap">
								<a href="#" onclick="buyCheck();" class="on">예매하기</a>
							</div></td>
					</tr>
				</table>
			</form>

			<hr style="width: 500px;">
		</div>
	</div>

	<h3 style="text-align: center;">출연진</h3>
	<table style="margin: auto;">
		<tr>
			<c:forEach var="actor" items="${detail.actorList}">
				<td style="padding-left: 20px;">${actor.name}<br />
					${actor.part}
				</td>
			</c:forEach>
		</tr>
	</table>
	<hr />

	<br>

	<div style="margin: auto;">
		<div class="thumnaild">
			<img
				src="${pageContext.request.contextPath}/static/img/${detail.perform.detailImg }">
		</div>
	</div>
</div>
<hr />
<br />
<br />

<%@ include file="../../part/foot.jspf"%>