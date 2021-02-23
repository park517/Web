//   메뉴를 표시할 textarea 아이디 값을 Menu로 부터 가져온다
let temp = decodeURI(location.href).split("?");  //temp : "Main.html?"+1+"?"+table_area_name+"?"+table_name;
// Menu에서 저장한 list 값을 불러온다.

// temp에 저장된 테이블 이름 가져오기 Table번호_list 
let table_area_id = temp[2];
console.log(temp[3]);
print_menu();


//만약 리스트가 널 값이 아니라면 메뉴를 표시한다.
   


 //menu.html으로 이동한다.
function go_menu(table_id){

    let content =document.getElementById(table_id).innerText; 
    location.href="Menu.html?"+ content;
 
}




//메뉴 리스트에 있는 내용을 textarea에 저장한다.
function print_menu(){
    
    for(let a = 1; a<=9 ; a++){
        let table_area = document.getElementById("Table"+a+"_list");
        let output = localStorage.getItem("Table"+a);
        let menu_list = JSON.parse(output);
        if(menu_list !=null){
            for (let i=0 ;i<menu_list.length ; i++){
                console.log("menu name :"+menu_list[i].menu_name);
                table_area.textContent += (menu_list[i].menu_name +'\n');
            }
        }
      
    }
    
   
}
