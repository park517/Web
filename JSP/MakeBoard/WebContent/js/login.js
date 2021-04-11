let id_list = document.getElementById('id_list').value;
id_list = id_list.replace("[","");
id_list = id_list.replace("]","");
id_list = id_list.replaceAll(" ","");
id_list = id_list.split(",");
let mid = document.getElementById('mid');
function id_check() {
	
	if(!id_list.includes(mid.value)) {
		alert("없는 아이디 입니다. 다시 입력해주세요.");
		mid.value ="";
	}
	else {
		document.login_form.submit();
	}
}