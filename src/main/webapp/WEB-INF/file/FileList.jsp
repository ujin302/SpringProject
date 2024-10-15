<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
img {
	width: 100px;
	cursor: pointer;
	margin: auto;
    display: block;
}
#board {
	width: 1000px;
	margin: 0 auto;
    display: block;
}
table {
    display: block;
    width: 100%;
    font-size: 14px;
    text-align: center;
}
table tr:after {
    content: '';
    display: block;
    float: none;
    clear: both;
}
table .left {
    text-align: left;
}
thead, tbody, tr {
    display: block;
    width: 100%;
}
td, th {
    display: block;
    float: left;
    padding: 10px 0;
}
table thead {
    border-bottom: solid 2px orange;
    font-weight: bold;
}
table tbody tr {
    border-bottom: 1px dotted #ccc;
}
.example table .imageName {
    width: 20%;
}
.example table .imageContent {
    width: 50%;
}
.paging {
	width: 1000px;
	text-align: center;
	margin-top: 20px;
}
#currentpaging {
	color: orange;
	font-size: 15pt;
	padding: 3px 7px;
	border: 1px solid lightgray;
	
}
#paging {
	font-size: 15pt;
	color: black;
	padding: 3px 7px;
}
</style>
</head>
<body>
	<img id="maru" alt="마루" src="/Spring/resources/image/마루.jpg" onclick="location.href='/Spring/'">
	<div id="board">    
	    <div class="exec">
	        <!-- 게시물 수  -->
	        총 게시물 수: <span>${totalA }</span>개
	        
	    </div>
	    <div class="example">
	        <table>
	            <thead>
	                <tr>
	                	<th class="no"><input id="checkboxNo" type="checkbox">번호</th>
	                    <th class="imageName">이름</th>
	                	<th class="imageContent">내용</th>
	                	<th class="imageOriginalName">이미지</th>
	                </tr>
	            </thead>                
	            <tbody>
	            	<c:if test="${list != null }">
	            		<c:forEach items="${list }" var="dto">
	            			<tr>
	            				<td class="no"><input class="itemNo" type="checkbox"> ${dto.seq }</td>
		                		<td class="imageName">${dto.imageName }</td>
		                		<td class="imageContent"><a href="/Spring/user/file/fileInfo?seq=${dto.seq}">${dto.imageContent}</a></td>
		                		<td class="imageOriginalName"><img alt="" src="https://kr.object.ncloudstorage.com/bitcamp-9th-bucket-129/storage/${dto.imageFileName }"></td>
		                	</tr>
	            		</c:forEach>
	            	</c:if>
	            </tbody>
	            <tfoot>
	            	<tr>
	            		<td><input class="btn" id="deleteBtn" type="button" value="선택 삭제"></td>
	            	</tr>
	            </tfoot>
	        </table>
	    </div>
	</div>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="/Spring/resources/js/fileDelete.js"></script>
</body>
</html>