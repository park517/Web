
temp = decodeURI(location.href).split("?"); 
console.log(temp);


if(temp[1] !=null){
    print_menu();
}


function go_menu(table_id){

    content =document.getElementById(table_id).innerText; 
    location.href="Menu.html?"+ content;
    




}

function print_menu(){
    console.log(temp[0]);
    console.log(temp[1] +"호출 완료되었습니다");
    document.getElementById("table1_text").innerText= temp[1];
}
