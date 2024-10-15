<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Upload Form</title>
<link rel="stylesheet" href="/Spring/resources/css/user.css">
</head>
<body>
	<img alt="마루" src="/Spring/resources/image/마루.jpg" onclick="location.href='/Spring/'">
	<form action="/Spring/user/file/upload" method="post" id="uploadFrom" enctype="multipart/form-data">
		<table>
			<tr>
				<th>상품명</th>
				<td><input type="text" id="imageName" name="imageName" size="35"></td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea id="imageContent" name="imageContent" rows="10" cols="50"></textarea>
				</td>
			</tr>
			<!-- 1. 단일 파일 처리 -->
			<!-- <tr>
				<td colspan="2" align="center">
				<input class="btn" type="file" name="img">
			</td> -->
				
			<!-- 
				2. 다중 파일 처리: Array
				다중 업로드 할때는 name 속성의 이름이 같아야 함!
				같은 name 속성으로 여러개 들어와서 서버에서 Array로 받음
				
				즉 여기서는 개수가 정해져 있어 배열 Array로 받음
			 -->
			<!-- <tr>
				<td colspan="2" align="center">
					<input class="btn" type="file" name="img">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input class="btn" type="file" name="img">
				</td>
			</tr> -->
			
			<!--
				3. 다중 파일 처리: Array 
				한번에 1개 또는 여러개(드래그) 선택 
				>> 한번에 여러개가 들어오기 때문에 List로 받음
				
				사용자가 선택할 파일 개수를 몰라서 List로 받음
			-->
			<tr>
				<td colspan="2" align="center">
					<input class="btn" type="file" name="img[]" multiple="multiple">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input class="btn" type="submit" value="이미지 업로드">
					<input class="btn" type="reset" value="취소">
				</td>
			</tr>
		</table>
	</form>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="/Spring/resources/js/file.js"></script>
</body>
</html>