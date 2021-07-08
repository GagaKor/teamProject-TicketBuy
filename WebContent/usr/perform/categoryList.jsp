<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../part/head.jspf"%>


<script type="text/javascript">
function randomNumber() {
	var rndNum = Math.round(Math.random() * 4);
	return rndNum;
}

function randomImage() {
	var number = randomNumber();
	var bannerImage = new Array();

	bannerImage[0] = "<img src=${pageContext.request.contextPath}/static/img/9_1.jpg>";
	bannerImage[1] = "<img src=${pageContext.request.contextPath}/static/img/5_1.jpg>";
	bannerImage[2] = "<img src=${pageContext.request.contextPath}/static/img/7_1.jpg>";
	bannerImage[3] = "<img src=${pageContext.request.contextPath}/static/img/8_1.jpg>";
	bannerImage[4] = "<img src=${pageContext.request.contextPath}/static/img/10_1.jpg>";

	return bannerImage[number];
}
</script>

<div class="bn-box-1 con">


	<span class="img-box"> <script type="text/javascript">
		document.write(randomImage());
	</script>
	</span>

</div>
<div class="list1 con">
	<ul class="row">
		<c:forEach items="${list}" var="list">
		
		<li class="cell"><a
			href="./detail.do?performId=${list.performId}&placeId=${list.placeId}&adm=${adm}">
				<p class="img-box">
					<img src="${pageContext.request.contextPath}/static/img/${list.performImg}">

				</p>
				<div
					style="border: 1px solid #ccc; margin-left: 5px; margin-right: 5px; text-align: center; padding: 10px; height: 90px
					word-break:break-all;">
					<strong style="margin-bottom: 10px;">${list.categoryName} &lt;${list.performName}&gt;</strong><br>
					<span>${list.seasonDate}</span><br>
					<span>${list.placeName}</span>
	
				</div>
		</a></li>
		
</c:forEach>
	</ul>
</div>



<%@ include file="../../part/foot.jspf"%>