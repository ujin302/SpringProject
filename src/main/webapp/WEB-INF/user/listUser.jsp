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
.example table .id {
    width: 30%;
}
.example table .name {
    width: 40%;
}
.example table .pwd {
    width: 30%;
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
	        총 게시물 수: <span>${paging.totalA }</span>개
	        
	    </div>
	    
	    <div class="example">
	        <table>
	            <thead>
	                <tr>
	                    <th class="name">이름</th>
	                	<th class="id">아이디</th>
	                    <th class="pwd">비밀번호</th>
	                </tr>
	            </thead>                
	            <tbody>
	            	<c:if test="${list != null }">
	            		<c:forEach items="${list }" var="dto">
	            			<tr>
		                		<td class="name">${dto.name }</td>
		                		<td class="id"><a href="/Spring/user/userInfo?pg=${paging.currentPgae }&id=${dto.id}">${dto.id}</a></td>
		                		<td class="pwd">
		                			<c:forEach begin="0" end="${dto.pwd.length() }" step="1">*</c:forEach>
		                		</td>
		                	</tr>
	            		</c:forEach>
	            	</c:if>
	            </tbody>
	        </table>
	        <div class="paging">${paging.pagingHTML }</div>
	    </div>
	</div>
<script type="text/javascript">
function userPaging(pg) {
	location.href = "/Spring/user/list?pg=" + pg;
}
</script>
</body>
</html>