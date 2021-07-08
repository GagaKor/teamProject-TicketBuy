<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../part/head.jspf"%>

<script type="text/javascript">
	function randomBannerNumber() {
		var rndNum = Math.round(Math.random() * 4); //0~7 사이의 난수를 구합니다.  
		return rndNum;
	}
	function randomSubNumber() {
		var rndNum = Math.round(Math.random() * 4); //0~7 사이의 난수를 구합니다.  
		return rndNum;
	}
	function randomBannerImage() {
		var number = randomBannerNumber();
		var bannerImage = new Array();

		bannerImage[0] = "<img src=${pageContext.request.contextPath}/static/img/9_1.jpg>";
		bannerImage[1] = "<img src=${pageContext.request.contextPath}/static/img/5_1.jpg>";
		bannerImage[2] = "<img src=${pageContext.request.contextPath}/static/img/7_1.jpg>";
		bannerImage[3] = "<img src=${pageContext.request.contextPath}/static/img/8_1.jpg>";
		bannerImage[4] = "<img src=${pageContext.request.contextPath}/static/img/10_1.jpg>";

		return bannerImage[number];
	}
	function randomSubImage() {
		var number = randomSubNumber();
		var cnt = 0;
		var subImage = new Array();
		<c:forEach var="list" items="${list}" varStatus="status" >
		subImage[${status.index}] = "<img src=${pageContext.request.contextPath}/static/img/${list.performImg}>";
		 </c:forEach>
		return subImage[number];
	}
</script>


<div class="bn-box-1 con">


	<span class="img-box"> <script type="text/javascript">
		document.write(randomBannerImage());
	</script>
	</span>

</div>
<div class="main_box">
<div class = "main_pic">
		<span class="img-box"> <script type="text/javascript">
		document.write(randomSubImage());
	</script>
	</span>
</div>
    <div class="board_wrap">
        <div class="board_title">
            <strong>최신공연</strong>

        </div>
        <div class="board_list_wrap">
            <div class="board_list">
                <div class="top">
                    <div class="num">New</div>
                    <div class="title">작품명</div>
                    <div class="writer">작성자</div>
                </div>
                <c:forEach var="list" items="${list}" varStatus="status">
                <div>
                    <div class="num">New</div>
                    <div class="title"><a href="./perform/detail.do?performId=${list.performId}&placeId=${placeIdList[status.index]}&adm=${adm}">&lt;${list.performName}&gt;</a></div>
                    <div class="writer">관리자</div>         
                </div>
                </c:forEach>
            </div>

        </div>
    </div>

</div>


<%@ include file="../part/foot.jspf"%>