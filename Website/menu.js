// 어플 상에서 버튼으로 메뉴 토글
const toggleBtn = document.querySelector('.navbar_toogleBtn');
const menu   = document.querySelector('.navbar_menu');

toggleBtn.addEventListener('click', ()=>{
    menu.classList.toggle('active');
});







