
// table를 id 값으로 불러와준다
let table = document.getElementById("menu_table");

// main에서 클릭한 테이블의 이름을 가져와서 변수에 저장한다.
let temp = location.href.split("?"); 
let table_name = temp[1];

//누른 테이블의 이름을 적는다
let table_title = document.getElementById('table_name');
table_title.textContent = table_name;

// 순번을 정하기 위한 order과 총 가격을 저장할 sum , 이 모든 정보를 저장한 객체 menu_list를 만든다.
let order =1;
let sum =0;
let menu_list = [];
let total_price_view = document.getElementById("sum");


// localStorage에 있는 값을 가져와서 안에 값이 있는지 확인
let output = localStorage.getItem(table_name); //Table1_menu_list
if(output!=null){
    write();
}


function menu_click(menu_name){
    // (요리 이름, 요리 가격)에서 , 기준으로 나누기
    let menu = document.getElementById(menu_name).textContent.split(",")
    // sum에 가격을 number형으로 만들어서 더하기
    sum += Number(menu[1]);
    
    // tabel menu의 table에  열 값 추가
    let row = `<tr align="center">
                <td>${order}</td>
                <td>${menu[0]}</td>
                <td>1</td>
                <td>${menu[1]}원</td> 
                </tr>`       
    table.innerHTML += row;

    //menu_list에 값 저장
    menu_list.push({'order' : order , 'menu_name' : menu[0], 'count' : '1', 'price' : menu[1]})

    //menu_list 값을 localStorage에 저장
    order ++;
    total_price_view.value=sum;
    localStorage.setItem(table_name,JSON.stringify(menu_list));
    
}




function reset(){
    let table = document.getElementById("menu_table");
    table.innerHTML ="";
    total_price_view.value =0;
    sum =0;
    localStorage.removeItem(table_name);
}


function complete(){
    // if(menu_list[0] ==null){
    //     alert("최소 하나 이상의 메뉴를 선택해주세요");
    // }
    let table_area_name =table_name+"_list";
    console.log("컴플리트 버튼 활성화 됨");
    
    url =encodeURI ="Main.html?"+1+"?"+table_area_name+"?"+table_name;
    location.href=url;
    console.log(url);
}



function write(){
    let output_list = JSON.parse(output);
    console.log(output_list);
    for(let i=0 ; i<output.length ; i++){

        let row = `<tr align="center">
                <td>${output_list[i].order}</td>
                <td>${output_list[i].menu_name}</td>
                <td>1</td>
                <td>${output_list[i].price}원</td> 
                </tr>`       
        table.innerHTML += row;

    }

}

