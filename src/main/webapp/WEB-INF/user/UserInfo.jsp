<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/Spring/resources/css/user.css">
</head>
<body>
	<img id="maru" alt="마루" src="/Spring/resources/image/마루.jpg" onclick="location.href='/Spring/'">
	<form method="post" id="updateFrom">
	<div hidden="" id="pg">${pg}</div>
		<table>
			<tr>
				<th>이름</th>
				<td><input id="name" name="name" value="${userDTO.name }"></td>
			</tr>
			<tr>
				<th>아이디</th>
				<td>
					<input data-checkId="false" id="id" name="id" value="${userDTO.id }" readonly="readonly">
					<div id="idDiv"></div>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" class="pwd" id="pwd" name="pwd" value="${userDTO.pwd }"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input class="btn" type="button" value="회원목록" onclick="location.href='/Spring/user/list?pg=${pg}'">
					<input class="btn" type="button" value="정보수정" onclick="userInfoUpdate(event)">
					<input class="btn" type="button" value="회원탈퇴" onclick="userInfoDelete(event)">
					<input class="btn" type="reset" value="다시입력">
				</td>
			</tr>
		</table>
	</form>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="/Spring/resources/js/user.js"></script>
</body>
</html>