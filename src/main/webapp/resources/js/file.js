$('#camera').click(function() {
	$('#imgBtn').trigger('click');
})

$('#imgBtn').change(function() {
	$('#showImageList').empty();
	
	for(var i=0; i< this.files.length; i++) {
		readURL(this.files[i]);
	}
	
})

function readURL(file) {
	var reader = new FileReader();
	var show;
	
	reader.onload = function(e) {
		var img = document.createElement('img');
		img.src = e.target.result;
		img.width = 70;
		img.height = 70;
		$('#showImageList').append(img);
	}
	
	reader.readAsDataURL(file);
}

$(function(){
	$('#uploadAjaxBtn').click(function() {
		let formData = new FormData($('#uploadAjaxForm')[0]);
		
		console.log(formData);
		
		// 파일 선택 여부 확인
	    if ($('#imgBtn')[0].files.length === 0) {
	        alert("업로드할 파일을 선택하세요.");
	        return;
	    }
    
		$.ajax({
			type: 'post',
			enctype: 'multipart/form-data',
			processData: false,
			contentType: false,
			url: '/Spring/user/file/upload',
			data: formData,
			success: function(data) {
				//alert(data);
				location.href = "/Spring/user/file/list";
			},
			error: function(e) {
				console.log(e);
			}
		});
	});
	
	$('#updateBtn').click(function() {
		let formData = new FormData($('#uploadFrom')[0]);
		console.log(formData);
		
		$.ajax({
			type: 'post',
			enctype: 'multipart/form-data',
			processData: false,
			contentType: false,
			url: '/Spring/user/file/update',
			data: formData,
			success: function(data) {
				//alert(data);
				location.href = "/Spring/user/file/fileInfo?seq=" + $('#seq').val();
			},
			error: function(e) {
				alert(e);
				console.log(e);
			}
		});
	});
});

/*
	FileReader 란?
		FileReader는 type이 file인 input 태그 또는 API 요청과 같은 인터페이스를 통해 
		File 또는 Blob 객체를 편리하게 처리할수있는 방법을 제공하는 객체이며
		abort, load, error와 같은 이벤트에서 발생한 프로세스를 처리하는데 주로 사용되며,
		File 또는 Blob 객체를 읽어서 result 속성에 저장한다.
		
		FileReader도 비동기로 동작한다.
		
		FileReader.onload()
		load 이벤트의 핸들러. 이 이벤트는 읽기 동작이 성공적으로 완료되었을 때마다 발생한다.
	
	
	processData
	 - 기본값은 true
	 - 기본적으로 Query String으로 변환해서 보내진다('변수=값&변수=값')
	 - 파일 전송시에는 반드시 false로 해야 한다.(formData를 문자열로 변환하지 않는다)
	 
	contentType
	  - 기본적으로 "application/x-www-form-urlencoded; charset=UTF-8"
	  - 파일 전송시에는 'multipart/form-data'로 전송이 될 수 있도록 false로 설정한다
*/