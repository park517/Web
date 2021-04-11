
let random_number = [];
let count =1;
let cnt =0;
let click_cnt=0;
let time=0;
let start_time=0;
let end_time=0;
let can =0;
insert_number();


function insert_number(){

    while(!(cnt ==9)){

        let a = Math.floor((Math.random()*9)+1);
        if(!random_number.includes(a)){
            random_number[cnt] =a;
            cnt++;
        }
    }
    for(let i=0 ; i<9 ;i++){   
        document.getElementById("box"+(i+1)).textContent = random_number[i];
    }   

}


function reset(){
    console.log("재시작 되었습니다");
    random_number = [];
    count =1;
    cnt =0;
    click_cnt=0;
    time=0;
    start_time =0;
    end_time =0;
    can ==2;
    console.log(click_cnt);
    insert_number();
    start();
}
    

function check(id){
    if(can ==0){
        alert("시작 버튼을 눌러주세요");
    }
    if(can ==2){
        alert("재시작 버튼을 눌러주세요");
    }
    if(can ==1){ 
        console.log(click_cnt);
        click_cnt++;
        console.log(id);
        const select = document.getElementById(id);
        console.log(select.textContent);

        if(select.textContent==count){
            select.textContent="";
            count++;
        }
    
    if(count==10)  end();
    }
    

 }

function start(){
    if(can ==2){
        alert("재시작 버튼을 눌러주세요");
    }
    else{
    can = 1;
    alert("확인 누르시면 게임이 시작됩니다");
    start_time = new Date();
    }
}
function end(){
    end_time  =new Date();
    time = end_time-start_time; 
    alert("성공까지 "+time/1000+"초 걸리셨고 "+click_cnt+" 번 만에 성공하셨습니다.");
    can = 2;
    
    

}
