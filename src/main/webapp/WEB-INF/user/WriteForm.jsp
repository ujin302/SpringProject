<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/Spring/resources/css/user.css">
<!-- DispatcherServlet 에게 요청 URL를 전송하게 된다.
	하지만, 해당 URL이랑 매핑되는 값이 없어 오류가 발생한다.
	servlet-context.xml 파일에서 해당 내용을 정의해줘야 한다. 
-->
</head>
<body>
	<img id="maru" alt="마루" src="/Spring/resources/image/마루.jpg" onclick="location.href='/Spring/'">
	<form action="write" method="post" id="writeFrom">
		<table>
			<tr>
				<th>이름</th>
				<td><input id="name" name="name" placeholder="이름 입력"></td>
			</tr>
			<tr>
				<th>아이디</th>
				<td>
					<input data-checkId="false" id="id" name="id" placeholder="아이디 입력">
					<div id="idDiv"></div>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" class="pwd" id="pwd" name="pwd" placeholder="비밀번호 입력"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input class="btn" type="button" value="회원가입" onclick="Join(event)">
					<input class="btn" type="reset" value="다시입력">
				</td>
			</tr>
		</table>
	</form>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="/Spring/resources/js/user.js"></script>
</body>
</html>