const btn_submit =  document.getElementById("btn_submit");
const title = document.getElementById("input_title");
const writer = document.getElementById("input_writer");
const text = document.getElementById("input_text");
const password = document.getElementById('input_password');

btn_submit.addEventListener('click',function(){
    if(!text.value) {
        alert("내용을 입력해주세요");
    }
    else if(!title.value){
        alert("제목을 입력해주세요");
    }
    else if(!writer.value){
        alert("작성자을 입력해주세요");
    }
    else if(!password.value){
        alert("비밀번호를 입력해주세요");
    }
    else {
        document.write_form.submit();
    }

});




