
temp = location.href.split("?"); 
data = temp[1];
content = data;
let x = document.getElementById('table_name');
x.textContent = content;
var order =1;
var sum =0;
var menu_list = [];
const total_price = document.getElementById("sum");
console.log(total_price);
total_price.value = 0;


function menu_click(menu_name){
    
    var menu = document.getElementById(menu_name).textContent.split(",")
    var table = document.getElementById("menu_table");
    sum += Number(menu[1]);
    var row = `<tr align="center">
                <td>${order}</td>
                <td>${menu[0]}</td>
                <td>1</td>
                <td>${menu[1]}원</td> 
                </tr>`       
    table.innerHTML += row;
    menu_list.push({'order' : order , 'menu_name' : menu[0], 'count' : '1', 'price' : menu[1]})

   
    console.log(sum);
    
    order ++;
    total_price.value=sum;
}




function reset(){
    var table = document.getElementById("menu_table");
    table.innerHTML ="";
    total_price.value =0;
    sum =0;
}

function complete(){


}

