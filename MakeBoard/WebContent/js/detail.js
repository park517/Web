

function comment_delete(value) {
	let check = confirm("댓글을 삭제 하시겠습니까?");
	if(check ===true) {
		location.href="/list/comment/delete?sid="+value;
	}
}

function comment_update(value) {
	let check = confirm("댓글을 수정 하시겠습니까?");
	let scontent = document.getElementById('scontent');
	if(check ===true) {
		let comment = prompt("수정할 댓글을 입력해주세요!");
		scontent.value = comment;
		document.comment_update_form.submit();
	}
}
