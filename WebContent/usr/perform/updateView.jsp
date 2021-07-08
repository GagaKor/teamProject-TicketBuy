<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../part/head.jspf"%>
<style type="text/css">s
.thumnail{ padding-bottom: 10px; border: none; } 
.thumnail img{ width: 20%; height: 20%; object-fit: cover; margin-bottom: 10px; } 
.container{ width:100%; height:auto; float: left; border: none;}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" >
function valCheck(){
	var frm=document.uFrm;
	var today = new Date();
	today = getFormatDate(today);
	 if(frm.performName.value==""){
		 alert("제목을 입력하세요.");
		 return;
	 }
	 if(frm.performDetail.value==""){
		 alert("내용을 입력하세요.");
		 return;
	 }
	 if(frm.ticketPrice.value == ""){
		alert('티켓 가격을 입력하세요.');
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
	 if(frm.startDate.value==""){
		 alert("시작 날짜를 입력하세요.");
		 return;
	 }
	 if(frm.endDate.value==""){
		 alert("종료 날짜를 입력하세요.");
		 return;
	 }
	 
	 if(frm.startDate.value.valueOf() <= today.valueOf() ){
		 alert("오늘 이후 날짜를 입력하세요.");
		 return;
	 }
	 if(frm.endDate.value.valueOf() < frm.startDate.value.valueOf()){
		 alert("종료 날짜를 시작 날짜 이후로 입력하세요.");
		 return;
	 }
	 for(var i=0; i<frm.sTime.length; i++ ){
		 if(frm.sTime[i].value == ""){
			 alert("스케쥴 시간 입력하세요.");
			 return;
		 }
	}
	 frm.submit();
}

function getFormatDate(date){
    var year = date.getFullYear();              
    var month = (1 + date.getMonth());          
    month = month >= 10 ? month : '0' + month;  
    var day = date.getDate();                   
    day = day >= 10 ? day : '0' + day;          
    return  year + '-' + month + '-' + day;       
}

function onOff(){
	frm=document.uFrm;
	if(frm.actorInfo.value == 'n'){
		document.getElementById("info").style.display = "none";
		}
	if(frm.actorInfo.value == 'y'){
		document.getElementById("info").style.display = "block";
		}
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
	 $(document).ready (function () {                
       $('.tbtnAdd').click (function () {                                        
           $('.tbuttons').append (                        
               '<label > 시간 선택 : <input type="time" name="sTime" /> </label> <input type="button" class="tbtnRemove" value="삭제"><br>'                    
           );                           
           $('.tbtnRemove').on('click', function () { 
               $(this).prev().remove (); 
               $(this).next ().remove (); 
               $(this).remove (); 
           });
       });                                           
}); 
$(document).ready (function () { 
	$('.abtnRemove').on('click', function () { 
        $(this).prev().remove (); // remove the textbox
        $(this).prev().remove (); // remove the textbox
        $(this).prev().remove (); // remove the textbox
        $(this).next ().remove (); // remove the <br>
        $(this).remove (); // remove the button
    });
});
</script>
<div style="width: 990px; margin: 0 auto; position: relative;">
<form action="./updatePerform.do" name="uFrm" method="post" enctype="multipart/form-data">
	<input type="hidden" name="performId" value="${perform.performId}" /> <!--Perform Pk get -->
	<input type="hidden" name="placeId" value="${placeId}" /> <!--Place Pk get -->
	<c:forEach var="schList" items="${schList}">
	<input type="hidden" name="schId" value="${schList.schId}" /> <!-- sch Pk get -->
	</c:forEach>
	<c:forEach var="actorList" items="${actorList}">
	<input type="hidden" name="actorId" value="${actorList.actorId}" /> <!-- sch Pk get -->
	</c:forEach>
	<hr /><br />
	<h2>공연 재설정</h2>
	<hr />
	<div class="container">
	<div class="thumnail">
	<img src="${pageContext.request.contextPath}/static/img/${perform.performImg }">
	</div>
	<div>
	<p><strong> <input type="text" value="${perform.performName }" name="performName" placeholder="제목을 입력하세요."/></strong></p>
	</div>
	</div>
	<span>공연 이미지 &nbsp; </span> <input type="file"  name="performPic" value="${perform.performImg }" /> <br /><hr />
	
	<label><input TYPE='radio' name='categoryCode' value='0' <c:if test="${perform.categoryCode == 0 }" >
        	checked='checked'
        	</c:if>  />연극</label> 
	<label><input TYPE='radio' name='categoryCode' value='1' <c:if test="${perform.categoryCode == 1 }" >
        	checked='checked'
        	</c:if> />뮤지컬</label>
	<label><input TYPE='radio' name='categoryCode' value='2' <c:if test="${perform.categoryCode == 2 }" >
        	checked='checked'
        	</c:if> />콘서트</label> <br />
	
	<div class="container">
	<div class="thumnail">
	<img src="${pageContext.request.contextPath}/static/img/${perform.detailImg }">
	</div>
	<div>
	<p><strong> <textarea maxlength="5000" name="performDetail">${perform.performDetail }</textarea></strong></p>
	</div>
	</div>
	<span>내용 이미지 &nbsp; </span> <input type="file"  name="detailPic" value="${perform.detailImg }" /> <br />
	<hr />
	관람시간 : <input type="text" name="runTime" placeholder="관람시간" value="${perform.runTime }" /> <br />
	
	가격    : <input type="text" name="ticketPrice" placeholder="가격을 입력하세요." value="${ticketPrice}"/>
		<br /> <br /> <hr /> <br />
	
	
	
	<h2>출연진 재설정</h2>
	<label><input type="radio" name="actorInfo" id="actorInfo" value="y" onclick="onOff()"
	checked="checked">출연진 정보 있음</label>
	<label><input type="radio" name="actorInfo" id="actorInfo" value="n" onclick="onOff()"  
	<c:if test="${empty actorList}">
	checked="checked"
	</c:if>>출연진 정보 없음</label>
	
	<input type="hidden" name="name" value="0">            
	<input type="hidden" name="part" value="0">            
	<input type="hidden" name="actorCode" value="0"> 
	
	<div class="buttons" id="info"
	<c:if test="${empty actorList}">
	style="display: none;"
	</c:if>>
	
	<input type="button" class="btnAdd" value="추가"><br>
		<c:forEach var="actor" items="${actorList }">            
        <input type="text" name="name" value="${actor.name }"  placeholder="출연자이름">
        <input type="text" name="part" value="${actor.part }" placeholder="배역">
        <select name="actorCode">
        	<option value="0" <c:if test="${actor.actorCode == 0 }" >
        	selected="selected"
        	</c:if>>감독</option>
        	<option value="1" <c:if test="${actor.actorCode == 1 }" >
        	selected="selected"
        	</c:if>>주연</option>
        	<option value="2" <c:if test="${actor.actorCode == 2 }" >
        	selected="selected"
        	</c:if>>조연</option>
        	<option value="3" <c:if test="${actor.actorCode == 3 }" >
        	selected="selected"
        	</c:if>>가수</option>
        </select>
        <input type="button" class="abtnRemove" value="삭제"> <br />
        </c:forEach >        
        </div><br /><br> <hr /> <br />	
       
        <h2>일정 재설정</h2>
        공연 일정   : ${seasonDate} <br />
        공연 시간   : /<c:forEach var="schDate" items="${schDate}">  <fmt:formatDate value="${schDate}" type="Time" pattern="HH시 mm분" />/ </c:forEach><br />
        
        기간 선택   : <input type="date" name="startDate" /> ~ <input type="date" name="endDate" /> <br />
	<input type="hidden" name="sTime" value="23%3A00" />
	<div class="tbuttons" >            
      <label > 시간 선택   : <input type="time" name="sTime" /> </label>
         <input type="button" class="tbtnAdd" value="추가"><br>        
        </div>   
        <br /><hr />   
        <br /><br />
        
            
        <input type="button" onclick="valCheck()" value="수정"/>
	<input type="button" onclick="alert('취소하였습니다.'); history.back();" value="뒤로가기"/>
</form>
</div>

<%@ include file="../../part/foot.jspf"%>