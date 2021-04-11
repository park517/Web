  let randomNumber = Math.floor(Math.random() * 100)+1;
  const inputNumber = document.querySelector(".inputNumber");
  const result  = document.querySelector(".result");
  const hint = document.querySelector(".hint");
  const guessField = document.querySelector(".guessField");
  const submitButton = document.querySelector(".submitButton");
  const add = document.querySelector(".add");
  let count =1;
  let resetButton;


  function checkGuess() {
    let userGuess =Number(guessField.value);
    if(userGuess ===0){
      hint.textContent='0을 선택한 당신.. 설명도 안읽고.. 다시 해주세요!';
    }
    else{

      
      if( count ===1){
        inputNumber.textContent='Previous : ';
      }

      inputNumber.textContent += userGuess+ ' ';



      if(userGuess === randomNumber){
        result.textContent ="축하드립니다 정답을 맞추셨습니다.";
        result.style.backgroundColor ='green';
        hint.textContent = '';
        setGameOver();

      } else if(count ===10){
        result.textContent ="게임이 종료되었습니다."
        result.style.backgroundColor ='red';
        hint.textContent = '';
        setGameOver();
      } else{
        result.textContent ='틀리셨습니다 다시 입력해주세요';
        result.style.backgroundColor ='red';
        if(randomNumber > userGuess){
          hint.textContent = '입력 하신 숫자가 너무 작습니다';
        }
        else if(randomNumber < userGuess){
          hint.textContent = '입력하신 숫자가 너무 큽니다.';
        }

      }
    
    count++;
    guessField.value ='';
    guessField.focus();
    }

  }

  submitButton.addEventListener('click', checkGuess);

  function setGameOver(){
    guessField.disabled = true;
    submitButton.disabled =true;
    resetButton = document.createElement('button');
    resetButton.textContent ='게임 다시 시작';
    document.body.appendChild(resetButton);
    resetButton.addEventListener('click', resetGame);
  }

  function resetGame() {
    count =1;
    const resetField = document.querySelectorAll('.resultField p');
    for(let i =0; i<resetField.length ; i++){
      resetField[i].textContent = '';
    }
  resetButton.parentNode.removeChild(resetButton);
  guessField.disabled = false;
  submitButton.disabled = false;
  guessField.value ='';
  guessField.focus();
  randomNumber = Math.floor(Math.random()*100)+1;
}
