const express = require('express'); //  설치한 express를 불러온다
const app = express(); //express를 활성화 시킨다.


 //db 연동부분
const db_config = require(__dirname + '/config/database.js'); //config/database.js 에 저장해놓은 mysql 정보를 불러온다.
const poll =db_config.poll();
console.log("poll 생성"+poll);



const bodyParser = require('body-parser'); //bobyPaser : API 요청에서 받은 body 값을 파싱하는 역할을 수행하는 것을 불러온다,
const moment = require('moment'); // 날짜 포멧을 위한 모듈
const path = require('path'); 
const { addListener } = require('process');
const methodOverride = require('method-override');
const { createPool } = require('mysql');
const port = process.env.PORT || 3000;



app.use(methodOverride('_method'));
app.use(express.static(path.join(__dirname,'/'))); //정적 파일을 쉽게 접근 할 수 있도록 경로를 바꿔준다. __dirname은 현재 폴더를 이야기 한다. 
//지금 현재 폴더 밑에 있는 파일들을 로드하여 사용 하겠다는 뜻 다수의 폴더를 선언 해줄 수도 있다.

app.set('views', __dirname + '/views'); // views 파일들을 현재 폴더 밑에 있는 views라는 폴더안에 있는 것을 사용하겠다는 뜻
app.set('view engine', 'ejs'); // view engine 으로 ejs를 사용한다.

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended : false})); // 어플리케이션에 연결 


app.get('/', function (req, res) { // '/'  즉 기본 index 경로로 요청이 되면 응답객체 res로 보낸다 . inedx페이지 입니다 라는 스트링을 응답한다.
    res.redirect('/list');
});


app.get('/list', function (req, res) {// /list 주소로 요청하게 될때 발생되는 이벤트 sql문을 실행시켜  rows에 담는다.
    poll.getConnection(function(err,conn){

        let sql = 'SELECT * FROM board1';    
        conn.query(sql, function (err, rows, fields) { // sql문을 서버를 통해 요청하면 콜백함수로 에러 유무 err와 sql문으로 얻어진 테이블 값들이
        //rows에 담긴다.
        console.log("2 conn의 값은: "+(conn));
        console.log("rows의 값은 : "+rows);
        for(let i =0; i<rows.length; i++){
            console.log('rows'+JSON.stringify(rows[i])); // 객체안의 값들을 보기위해 log로 찍어보았다.
            rows[i].date = moment( rows[i].date).format('YYYY-MM-DD'); //db에서 date 타입을 Date로 했기에 필요없는 부분은 날리려구 포멧해주어서 다시 넣어준다.
        }
    
        if(err) console.log('query is not excuted. select fail...\n' + err); // 만일 오류가 있으면 로그 띄우기
        else  res.render('board.ejs', {list : rows}); //오류가 안뜬다면 board.ejs 로 rows값들을 list에 넣어 보낸다.
    
        });
        
        conn.release();
    });
    
});

app.get('/write', function (req, res) { // /write로 요청시  write.ejs로 이동한다.
    
    res.render('write.ejs');  
});

app.get('/read/:number', function(req,res) {
    let number = req.params.number;
    console.log("number: "+number);
    let sql = 'select * from board1 where number=?';

    poll.getConnection(function(err,conn) {

        console.log("sql: "+sql);
        conn.query(sql,number, function(err,rows){
            console.log("rows : "+JSON.stringify(rows));
            if(err) console.log('query is not excuted. select fail...\n' + err);
            else {
                res.render("read.ejs",{list : rows});
            }
        });
        conn.release();
    });
 
    
    
});

app.post('/writeAf', function (req, res) { //write.ejs에서 버튼 누를시 wirteAf 포스트가 발생하여 insert sql문을 실행한다.
    let body = req.body; //왜인지는 모르겠지만 body말고 다른 변수 쓰면 안됨..ㅎ 여기서 바디란 요청 객체인 write.ejs의 body를 말한다.
    // console.log("body: "+body);
    let sql = 'INSERT INTO  board1(title,context,name,date,password) VALUES(?,?,?,curdate(),?)'
    let params = [body.title,body.context, body.name,body.password];  // body child ,form안에 있는 title ,content 이름을 가진 엘리멘트의 값을 가져온다.
    console.log(sql);
    console.log('params' +JSON.stringify(params));

    poll.getConnection(function(err,conn) {

        conn.query(sql, params, function(err) { // sql를 실행하고 VALUES 으로 params를 보낸다.
            if(err) console.log('query is not excuted. insert fail...\n' + err);
            else res.redirect('/list'); //오류 미 발생시 /list 돌아간다.
        });
    
        conn.release();
    });
    
   
});

app.get('/read/remove/:number',function(req,res){
    let number = req.params.number;
    let sql ='delete from board1 where number =?'
    let params = number;

    poll.getConnection(function(err,conn) {

        conn.query(sql,params,function(err){
            if(err) console.log('query is not excuted. insert fail...\n' + err);
            else res.redirect('/list'); //오류 미 발생시 /list 돌아간다.
        });
    
        conn.release();
    });
    
 
});


app.get('/read/update/:number',function(req,res){
    
    let number = req.params.number;
    let sql ='select * from board1 where number =?'
    let params = number;

    poll.getConnection(function(err,conn) {

        conn.query(sql,params,function(err,rows){
            if(err) console.log('query is not excuted. insert fail...\n' + err);
            else res.render("update.ejs",{list : rows});
        });
    
        conn.release();
    });

 
    
});


app.post('/updateAf', function (req, res) { //write.ejs에서 버튼 누를시 wirteAf 포스트가 발생하여 insert sql문을 실행한다.
    let body = req.body; //왜인지는 모르겠지만 body말고 다른 변수 쓰면 안됨..ㅎ 여기서 바디란 요청 객체인 write.ejs의 body를 말한다.
    // console.log("body: "+body);  
    let sql = 'UPDATE board1 set title=?,context=? where number =? '
    let params = [body.title,body.context, body.number];  // body child ,form안에 있는 title ,content 이름을 가진 엘리멘트의 값을 가져온다.
    console.log("update 에서 number +"+body.number);

    poll.getConnection(function(err,conn) {

        conn.query(sql, params, function(err) { // sql를 실행하고 VALUES 으로 params를 보낸다.
            if(err) console.log('query is not excuted. update fail...\n' + err);
            else res.redirect('/list'); //오류 미 발생시 /list 돌아간다.
        });
    
        conn.release();
    });

});






app.listen(port, () => console.log('Server is running on port 3000...')); //3000포트로 응답한다. 