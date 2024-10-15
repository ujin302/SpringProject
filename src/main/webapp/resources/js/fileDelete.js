function checkItem() {
	let isCheck = true;
	
	$('.itemNo').each(function(){
		if(!$(this).prop('checked')) {
			isCheck = false;
		}
	});
	
	if(isCheck) $('#checkboxNo').prop('checked', true);
	else $('#checkboxNo').prop('checked', false);
}

function deleteItem() {
	var itemList = [];
	var count = 0;
	$('.itemNo').each(function(){
		if($(this).prop('checked')) {
			itemList.push($(this).parent().text())
		}
	});
	
	console.log(itemList);
	
	$.ajax({
		type: 'post',
		url: '/Spring/user/file/delete',
		data: {
			'seqList' : itemList
		},
		traditional: true,  // 배열 데이터를 서버에 전송할 때 필요한 설정
		success: function(data) {
			alert('삭제 완료하였습니다.');
			window.location.reload();
		},
		error: function(e) {
			alert(e);
			console.log(e);
		}
	});
}

$(function() {
	// 전체 선택 & 해제
	$('#checkboxNo').change(function() {
		$('.itemNo').prop('checked', $('#checkboxNo').prop('checked'))
	});
	
	// 체크박스 아이템 선택
	$('.itemNo').change(checkItem);
	
	// 이미지 삭제
	$('#deleteBtn').click(deleteItem);
});