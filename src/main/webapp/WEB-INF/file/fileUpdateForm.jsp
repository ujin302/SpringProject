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
	<form id="uploadFrom" enctype="multipart/form-data">
		<input hidden id="seq" name="seq" value="${uploadDTO.seq}"/>
		<table>
			<tr>
				<th>상품명</th>
				<td><input type="text" id="imageName" name="imageName" size="35" value="${uploadDTO.imageName}"></td>
			</tr>
			<tr>
				<td colspan="3">
					<textarea id="imageContent" name="imageContent" rows="10" cols="70" placeholder="${uploadDTO.imageContent}" >${uploadDTO.imageContent}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<img id="camera" alt="카메라" src="/Spring/resources/image/camera.png" width="50">
					<span id="showImageList">
						<img width="100px" alt="" src="https://kr.object.ncloudstorage.com/bitcamp-9th-bucket-129/storage/${uploadDTO.imageFileName }">
					</span>
					
					<input class="btn" id="imgBtn" type="file" name="img" hidden>
				</td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<input class="btn" type="reset" value="취소">
					<input class="btn" type="button" value="상품목록" onclick="location.href='/Spring/user/file/list'">
					<input class="btn" type="button" id="updateBtn" value="상품수정">
					
				</td>
			</tr>
		</table>
	</form>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="/Spring/resources/js/file.js"></script>
</body>
</html>