<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Upload Ajax Form</title>
<link rel="stylesheet" href="/Spring/resources/css/user.css">
</head>
<body>
	<img id="maru" alt="마루" src="/Spring/resources/image/마루.jpg" onclick="location.href='/Spring/'">
	<form id="uploadAjaxForm" enctype="multipart/form-data">
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
			<tr>
				<td colspan="2" align="center">
					<img id="camera" alt="카메라" src="/Spring/resources/image/camera.png" width="50">
					<span id="showImageList">이미지 미리보기</span>
					
					<input class="btn" id="imgBtn" type="file" name="img[]" multiple="multiple" hidden>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input class="btn" id="uploadAjaxBtn" type="button" value="이미지 업로드">
					<input class="btn" type="reset" value="취소">
				</td>
			</tr>
		</table>
	</form>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="/Spring/resources/js/file.js"></script>
<script type="text/javascript">

</script>
</body>
</html>
