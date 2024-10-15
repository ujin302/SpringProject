// 아이디 중복 체크 	
function checkId() {
	console.log("blur")
	
	if(!$(this).prop("readonly")) {
		$.ajax({
			type: 'get',
			url: '/Spring/user/checkId',
			data: {'id' : $('#id').val()},
			dataType: 'text',
			success: function(data) {
				console.log(data.trim());
				if(data.trim() === "true") {
					$('#idDiv').text('사용 가능');
				}
				else $('#idDiv').text('사용 불가능');
			}
		})
	}
}

// 아이디 중복 체크 후, 사용자가 id를 다시 설정하였을 경우 
let focusId = null;
document.getElementById('id').addEventListener("focus", () => {
	focusId = document.getElementById('id').value;
});

document.getElementById('id').addEventListener("blur", () => {
	if(focusId != document.getElementById('id').value) { // id 변경 
		document.getElementById('id').dataset['checkid'] = false; // 중복체크 X
	}
});

// 회원가입 시, 유효성 검사 
function Join(e) {
	// 1. 아이디 유효성 확인
	if($('#idDiv').text() == '사용 불가능') {
		alert("아이디 중복체크 하세요.");
		e.preventDefault();
		return false;
	}
	
	// 2. 이름 유효성 확인
	if(document.getElementById('name').value == '') {
		alert("이름을 작성하세요.");
		e.preventDefault();
		return false;
	}
	
	// 3. 비밀번호 유효성 확인
	if(document.getElementById('pwd').value == '') {
		alert("비밀번호을 작성하세요.");
		e.preventDefault();
		return false;
	}
	
	$.ajax({
		type: 'post',
		url: '/Spring/user/write',
		data: $('#writeFrom').serialize(),
		success: function(data) {
			console.log('회원가입');
			alert("회원가입을 축하합니다.");
			location.href="/Spring/user/list";
		},
		error: function(e) {
			console.log(e);
			alert('실패');
		}
	});	
}

// 회원정보 수정 
function userInfoUpdate(e) {
	if(confirm("회원 정보를 수정하시겠습니까? ")) {
		$.ajax({
			type: 'post',
			url: '/Spring/user/update',
			data: $('#updateFrom').serialize(),
			success: function(data) {
				console.log('회원수정');
				alert("회원정보가 수정되었습니다.");
				location.href="/Spring/user/list?pg="+$('#pg').text();
			},
			error: function(e) {
				console.log(e);
				location.reload();
			}
		});
	}
}


// 회원 탈퇴 
function userInfoDelete(e) {
	$.ajax({
		type: 'get',
		url: '/Spring/user/getExistPwd?id=' + $('#id').val(),
		dataType: 'json',
		success: function(data) {
			console.log('회원탈퇴: 비밀번호 가져오기');
			console.log(JSON.stringify(data));
			var input = prompt('비밀번호를 입력해주세요.');
			
			if(input == data.pwd) {
				if(confirm("탈퇴 하시겠습니까?")) {
					$.ajax({
						type: 'get',
						url: '/Spring/user/delete?id=' + $('#id').val(),
						success: function(data) {
							console.log('회원탈퇴');
							alert("탈퇴되었습니다.");
							location.href="/Spring/user/list";
						},
						error: function(e) {
							console.log(e);
						}
					});
				}
			}else alert('비밀번호가 일치하지 않습니다.')
		},
		error: function(e) {
			console.log(e);
		}
	});
}


$(function() {
	// 1. id 중복 체크
	$('#id').blur(checkId);
});