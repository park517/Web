const btn_sub_insert = document.getElementById('btn_sub_insert');
const sub_password = document.getElementById('sub_password');

btn_sub_insert.addEventListener('click', function () {

    if (!sub_password.value) {

        alert("비밀번호를 입력해주세요.");
    } else {
        alert("댓글이 입력되었습니다.");
        document
            .sub_form
            .submit();
    }
});

function sub_delete(sub_pwd, sub_id) {

    let pwd = prompt("비밀번호를 입력해주세요");
    if (pwd == sub_pwd) {
        alert("삭제되었습니다.");
        let url = '/read/sub/remove/' + sub_id;
        location.href = url;
    } else {
        alert("비밀번호를 다시 입력해주세요");
    }

}

function sub_update(sub_pwd,sub_id) {
    
    let pwd = prompt("비밀번호를 입력해주세요");
    if (pwd == sub_pwd) {
        let url = "/read/sub/update/"+sub_id;
        let name = "popup test";
        let option = "width = 500, height = 500, top = 100, left = 200, location = no"
        window.open(url, name, option);
    
    }
    else {
        alert("비밀번호를 다시 입력해주세요");
    }
}

