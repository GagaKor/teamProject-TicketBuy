<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../part/head.jspf"%>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
function placeSearch() {
	var x = screen.width/2 - 700/2;
    var y = screen.height/2 - 450/2;
	window.open("find.do?search=n","장소찾기","width=800,height=300,scrollbars=yes, left=900,top=450");
}
function onOff(){
	frm=document.insertFrm;
	if(frm.actorInfo.value == 'n'){
		document.getElementById("info").style.display = "none";
		}
	if(frm.actorInfo.value == 'y'){
		document.getElementById("info").style.display = "block";
		}
}

function valCheck(){
	var frm=document.insertFrm;
	 if(frm.performName.value==""){
		 alert("제목을 입력하세요.");
		 frm.performName.focus();
		 return;
	 }
	 if(frm.performDetail.value==""){
		 alert("내용을 입력하세요.");
		 frm.performDetail.focus();
		 return;
	 }
	 if(frm.runTime.value==""){
		 alert("관란시간을 입력하세요.");
		 frm.performDetail.focus();
		 return;
	 }
	 if(frm.performPic.value==""){
		 alert("공연 이미지를 첨부해주세요.");
		 frm.performName.focus();
		 return;
	 }
	 if(frm.detailPic.value==""){
		 alert("공연 이미지를 첨부해주세요.");
		 frm.performName.focus();
		 return;
	 }
	 if(frm.placeId.value==""){
		 alert("장소를 선택하세요.");
		 frm.placeId.focus();
		 return;
	 }
	 if(frm.actorInfo.value == 'y') {
		 for(var i=0; i < frm.name.length; i++){
			if(frm.name[i].value == ""){
				alert("출연진 이름을 입력하세요.");
				frm.name[i].focus();
				return;
			}
			if(frm.part[i].value == ""){
				alert("배역을 입력하세요.");
				frm.part[i].focus();
				return;
			}
		 }
		}
	frm.submit();
}
 $(document).ready (function () {                
            $('.btnAdd').click (function () {                                        
                $('.buttons').append (                        
                    '<input type="text" name="name" placeholder="출연자이름"> <input type="text" name="part" placeholder="배역"> <select name="actorCode"><option value="0" >감독</option> <option value="1" >주연</option> <option value="2" >조연</option> <option value="3" >가수 </option></select> <input type="button" class="btnRemove" value="삭제"><br>'                    
                ); // end append                            
                $('.btnRemove').on('click', function () { 
                    $(this).prev().remove (); // remove the textbox
                    $(this).prev().remove (); // remove the textbox
                    $(this).prev().remove (); // remove the textbox
                    $(this).next ().remove (); // remove the <br>
                    $(this).remove (); // remove the button
        });
    }); // end click                                            
}); // end ready 
</script>
<div style="width: 990px; margin: 0 auto; position: relative;">
<form action="./insertPerform.do" name="insertFrm" method="post"
	enctype="multipart/form-data">

	<hr />
	<h3>공연 정보</h3>
	<hr />
	<input type="text" placeholder="제목을 입력하세요." name="performName" /> <br />
	<label><input TYPE='radio' name='categoryCode' value='0'
		checked='checked' />연극</label> <label><input TYPE='radio'
		name='categoryCode' value='1' />뮤지컬</label> <label><input
		TYPE='radio' name='categoryCode' value='2' />콘서트</label><br />
	<textarea maxlength="5000" name="performDetail"
		placeholder="내용을 입력하세요."></textarea>
	<br /> <input type="text" name="runTime" placeholder="관람시간" />

	<p>공연 이미지 &nbsp;</p>
	<input type="file" name="performPic" /> <br />
	<p>내용 이미지 &nbsp;</p>
	<input type="file" name="detailPic" /> <br /> <br />
	<h3>장소 정보</h3>
	<input type="hidden" name="placeId" id="placeId" /> 공연장 :<input
		type="text" name="placeName" size="30" placeholder="공연장" readonly>
	<input type="button" value="찾기" onclick="placeSearch()"> <br>
	주소 :<input type="text" name="placeaddr" size="45" readonly> <br>
	전화번호 : <input type="text" name="phonNum" size="20" readonly> <br />

	총 좌석 갯수 : <input type="text" name="totalSeatNum" size="10" readonly>
	<hr />
	<h3>출연진 정보</h3>
	<hr />
	<br /> <label><input type="radio" name="actorInfo"
		id="actorInfo" value="y" onclick="onOff()">출연진 정보 있음</label> <label><input
		type="radio" name="actorInfo" id="actorInfo" value="n"
		onclick="onOff()" checked>출연진 정보 없음</label> <input type="hidden"
		name="name" value="0"> <input type="hidden" name="part"
		value="0"> <input type="hidden" name="actorCode" value="0">
	<div class="buttons" id="info" style="display: none;">
		<input type="text" name="name" placeholder="출연자이름"> <input
			type="text" name="part" placeholder="배역"> <select
			name="actorCode">
			<option value="0">감독</option>
			<option value="1">주연</option>
			<option value="2">조연</option>
			<option value="3">가수</option>
		</select> <input type="button" class="btnAdd" value="추가"><br>
	</div>
	<br /> <br /> <input type="button" onclick="valCheck()" value="다음" />
	<input type="button" onclick="history.back()" value="뒤로가기" />
</form>
</div>

<%@ include file="../../part/foot.jspf"%>