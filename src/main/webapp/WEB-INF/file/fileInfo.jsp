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
		<table>
			<tr>
				<td rowspan="3">
					<img width="100px" alt="" src="https://kr.object.ncloudstorage.com/bitcamp-9th-bucket-129/storage/${uploadDTO.imageFileName }">
				</td>
				<td>번호: ${uploadDTO.seq }</td>
			</tr>
			<tr>
				<td>상품명: ${uploadDTO.imageName }</td>
			</tr>
			<tr>
				<td>
					파일명: ${uploadDTO.imageFileName }
				</td>
			</tr>
			<tr>
				<td colspan="2" height="200" valign="top">
					<pre>${uploadDTO.imageContent }</pre>
				</td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<input class="btn" type="button" value="상품목록" onclick="location.href='/Spring/user/file/list'">
					<input class="btn" type="button" value="상품수정" onclick="location.href='/Spring/user/file/updateForm?seq=${uploadDTO.seq}'">
					<input class="btn" type="button" value="상품수정" onclick="location.href='/Spring/user/file/delete?seq=${uploadDTO.seq}'">
				</td>
			</tr>
		</table>
	</form>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="/Spring/resources/js/file.js"></script>
</body>
</html>