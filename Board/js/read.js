const btn_update = document.getElementById('update');
const btn_delete = document.getElementById('delete');
const list = document.getElementById('list');
let number = btn_update.className;
const db_password =list.className;


btn_update.addEventListener('click',function(){
    if(db_password === null){
    alert("수정하시겠습니까?");
    let url='/read/update/'+number;
    location.href=url;
    }
    else if(db_password !==null) {
        let password = prompt("비밀번호를 입력해주세요");
    
        if(password == db_password) {
            let url='/read/update/'+number;
            location.href=url;
        }

        else {
            alert("비밀번호가 맞지 않습니다 다시 입력해주세요");
        }
    
        }
});

btn_delete.addEventListener('click',function() {

    if(db_password === null){
        alert("삭제되었습니다.");
        let url='/read/remove/'+number;
        location.href=url;
    }
    else if(db_password !==null) {
    let password = prompt("비밀번호를 입력해주세요");

    if(password == db_password) {
        alert("삭제되었습니다.");
        let url='/read/remove/'+number;
        location.href=url;
    }

    else {
        alert("비밀번호가 맞지 않습니다 다시 입력해주세요");
    }

    }
   

});