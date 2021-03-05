const memo_context = document.getElementById('memo_input');
const btn_input = document.getElementById('.btn_input');
const do_list = document.getElementById('do_list');


window.addEventListener("keydown",(e)=>{
  if(e.keyCode==13) input();
});


let i =0;

function input(){
    
    let text =memo_context.value;
    console.log(text);
   
    let row = 
    `<div class="memo">
    <input type="checkbox" name="checkbox" id="checkbox">
    <textarea name=""  cols="25" rows="1">${text}</textarea>
    </div>
    `;
    i++;
    memo_context.value ="";
    do_list.innerHTML +=row;
    memo_context.focus();

    

} 
function remove_lastItem(){

    
    var list = document.querySelectorAll('.do_list > div');
    var liLen = list.length-1;
    console.log(liLen);
    do_list.removeChild(list[liLen]);    
}


function remove_select(){
    let memo_list =document.querySelectorAll('.do_list>div');
    let checkbox_list = document.querySelectorAll('.do_list > div >#checkbox');
    var liLen = checkbox_list.length-1;
    for(let i in checkbox_list){
        if(checkbox_list[i].checked==true)
        do_list.removeChild(memo_list[i]);
    }
}

function remove_all(){

   do_list.innerHTML ="";
}

// function remove(){
//     do_list.removeChild(do_list.childNodes[] ); 
// }

