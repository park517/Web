const btn_input = document.getElementById('.btn_input');
const do_list = document.getElementById('do_list');


window.addEventListener("keydown",(e)=>{
  if(e.keyCode==13) input();
});


let i =0;

function input(){
    let text = prompt("할 일을 입력해주세요!");
    
 
    console.log(text);
   
    let row = 
    `<div class="memo">
    <input type="checkbox" name="checkbox" id="checkbox">
    <div >${text}</div>
    </div>
    `;
    i++;
    do_list.innerHTML +=row;
 

    

} 
function remove_lastItem(){

    
    let list = document.querySelectorAll('.do_list > div');
    let liLen = list.length-1;
    let remove_confirm  = confirm("정말로 삭제 하시겠습니까?");
    if(remove_confirm) {

        do_list.removeChild(list[liLen]);    
    }



}


function remove_select(){
    let memo_list =document.querySelectorAll('.do_list>div');
    let checkbox_list = document.querySelectorAll('.do_list > div >#checkbox');
    var liLen = checkbox_list.length-1;
    let remove_confirm  = confirm("정말로 삭제 하시겠습니까?");
    if(remove_confirm) {
   
        for(let i in checkbox_list){
            if(checkbox_list[i].checked==true)
            do_list.removeChild(memo_list[i]);
        }
    }
   
    

}

function remove_all(){
    let remove_confirm  = confirm("정말로 삭제 하시겠습니까?");
    if(remove_confirm) {
         do_list.innerHTML ="";
    }
    

}

