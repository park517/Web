let id_list = document.getElementById("id_list").value;
console.log(typeof(id_list));
let mid = document.getElementById("mid");
console.log(id_list);
id_list =id_list.replace("[","");
id_list =id_list.replace("]","");
id_list =id_list.replaceAll(" ","");

function id_check(value) {
	console.log(value);
	if(id_list.includes(value)) {
		alert("중복 된 값입니다 다시 입력해주세요.");
		mid.value ="";
	}
}

function password_check(value) {
	let mpassword =document.getElementById("mpassword").value;
	let rmpassword =document.getElementById("rmpassword");

	if(value !== mpassword) {
		alert("비밀번호가 다릅니다 다시 입력해주세요");
		rmpassword.value="";
	}
}
