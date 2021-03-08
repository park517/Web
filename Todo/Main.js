
const btn_input = document.getElementById('.btn_input');
const do_list = document.getElementById('do_list');
let text_list =[];
const i =0;

window.addEventListener("keydown",(e)=>{
    if(e.keyCode==13) input();
  });
  
  if(localStorage.getItem('memo')!=null){
      print();
  }
  

function input(){
    let text = prompt("할 일을 입력해주세요!");
    
    console.log(text);
   
    let row = 
    `<div class="memo">
    <input type="checkbox" name="checkbox" id="checkbox">
    <div >${text}</div>
    </div>
    `;

    do_list.innerHTML +=row;
    text_list.push({'memo' : text});
    localStorage.setItem('memo',JSON.stringify(text_list));
    
} 
function remove_lastItem(){

    
    let list = document.querySelectorAll('.do_list > div');
    let liLen = list.length-1;
    let remove_confirm  = confirm("정말로 삭제 하시겠습니까?");
    if(remove_confirm) {

        do_list.removeChild(list[liLen]);
        text_list.splice(liLen,1);
        replace();
    }



}

function remove_select(){
    let memo_list =document.querySelectorAll('.do_list>div');
    let checkbox_list = document.querySelectorAll('.do_list > div >#checkbox');
   
    let liLen = checkbox_list.length-1;
    let remove_confirm  = confirm("정말로 삭제 하시겠습니까?");
    if(remove_confirm) {
   
        for(let i =liLen ; i>=0;i--){
            if(checkbox_list[i].checked==true) {
                text_list.splice(i,1);
                console.log("i : "+i);
                do_list.removeChild(memo_list[i]);
                console.log(i+"번째 내용 삭제");
                
                console.log(text_list);
            }
        }
        replace();
    }
   
}
function remove_all(){
    let remove_confirm  = confirm("정말로 삭제 하시겠습니까?");
    if(remove_confirm) {
         do_list.innerHTML ="";
         localStorage.removeItem('memo');
    }
}

function print(){
    text_list= JSON.parse(localStorage.getItem('memo'))
    console.log(text_list);
    for(let i = 0 ; i<text_list.length ;i++){
        let text  = text_list[i].memo;
        let row = 
        `<div class="memo">
        <input type="checkbox" name="checkbox" id="checkbox">
        <div >${text}</div>
        </div>
        `;
        
        do_list.innerHTML +=row;
    }
}

function replace(){
    localStorage.removeItem('memo');
    localStorage.setItem('memo',JSON.stringify(text_list));
}

