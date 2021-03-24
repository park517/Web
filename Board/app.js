// express 및 사용 모듈 불러오기

const express = require('express');
const expressLayouts = require('express-ejs-layouts');
const app = express();
const bodyParser = require('body-parser'); //bobyPaser : API 요청에서 받은 body 값을 파싱하는 역할을 수행하는 것을 불러온다,
const moment = require('moment'); // 날짜 포멧을 위한 모듈
const path = require('path');
const {addListener} = require('process');
const methodOverride = require('method-override');
const {createPool} = require('mysql');
const {render} = require('ejs');
const {json} = require('express');
const port = process.env.PORT || 3000; //process.env.PORT는 기본 포트로 heroku를 사용할 때 필요하다
let id;

//db 연동부분

const db_config = require(__dirname + '/config/database.js'); //config/database.js 에 저장해놓은 mysql 정보를 불러온다.
const poll = db_config.poll();
console.log("poll 생성" + poll);

app.use(methodOverride('_method'));
app.use(express.static(path.join(__dirname, '/'))); //정적 파일을 쉽게 접근 할 수 있도록 경로를 바꿔준다. __dirname은 현재 폴더를 이야기 한다.

app.set('views', __dirname + '/views'); // views 파일들을 현재 폴더 밑에 있는 views라는 폴더안에 있는 것을 사용하겠다는 뜻
app.set('view engine', 'ejs'); // view engine 으로 ejs를 사용한다.

// body pasher

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: false})); // 어플리케이션에 연결

// 홈페이지 접속 시 /list로 요청을 보낸다.

app.get('/', function (req, res) {
    res.redirect('/list');
});

// list 페이지 요청 시

app.get('/list', function (req, res) {
    poll.getConnection(function (err, conn) {
        let sql = 'SELECT * FROM board';
        conn.query(sql, function (err, rows, fields) {
            //rows에 담긴다.
            for (let i = 0; i < rows.length; i++) {

                rows[i].date = moment(rows[i].date).format('YYYY-MM-DD'); //db에서 date 타입을 Date로 했기에 필요없는 부분은 날리려구 포멧해주어서 다시 넣어준다.
            }

            if (err) 
                console.log('query is not excuted. select fail...\n' + err);
            else 
                res.render('board.ejs', {list: rows}); //오류가 안뜬다면 board.ejs 로 rows값들을 list에 넣어 보낸다
            }
        );
        +conn.release();
    });

});

app.get('/write', function (req, res) { // /write로 요청시  write.ejs로 이동한다.

    res.render('write.ejs');

});

// 글 눌렀을 시 내용 보여주기

app.get('/read/:number', function (req, res) {
    id = req.params.number;
    let sql = 'select * from board where id=?; ';
    let sql1 = 'select * from sboard where id=?; ';
    // let sqls = mysql.format(sql,number); let sqls1 = mysql.format(sql1,number);

    poll.getConnection(function (err, conn) {
        conn.query(sql, id, function (err, rows) {
            conn.query(sql1, id, function (err, rows1) {
                if (err) 
                    console.log('query is not excuted. select fail...\n' + err);
                else {
                    res.render("read.ejs", {
                        list: rows,
                        sub_list: rows1
                    });
                }
            });
            conn.release();
        });
    });
});

//insert 수행 부분

app.post('/writeAf', function (req, res) {
    let body = req.body;

    let sql = 'INSERT INTO  board(writer,title,context,password,date) VALUES(?,?,?,?,curdate(' +
            '))' ;
    let params = [body.writer, body.title, body.context, body.password];
    poll.getConnection(function (err, conn) {
        conn.query(sql, params, function (err) {
            if (err) 
                console.log('query is not excuted. insert fail...\n' + err);
            else 
                res.redirect('/list');
            }
        );
        conn.release();
    });

});

// delete 부분

app.get('/read/remove/:number', function (req, res) {
    let number = req.params.number;
    let sql = 'delete from board where id =?'
    let params = number;

    poll.getConnection(function (err, conn) {

        conn.query(sql, params, function (err) {
            if (err) 
                console.log('query is not excuted. insert fail...\n' + err);
            else 
                res.redirect('/list'); //오류 미 발생시 /list 돌아간다.
            }
        );

        conn.release();
    });
});

// 수정 버튼 눌렀을 시 원래 내용 띄워주기

app.get('/read/update/:number', function (req, res) {

    let number = req.params.number;
    let sql = 'select * from board where id =?'
    let params = number;

    poll.getConnection(function (err, conn) {

        conn.query(sql, params, function (err, rows) {
            if (err) 
                console.log('query is not excuted. insert fail...\n' + err);
            else 
                res.render("update.ejs", {list: rows});
            }
        );

        conn.release();
    });
});

// update 문 수행

app.post('/updateAf', function (req, res) {
    let body = req.body;

    let sql = 'UPDATE board set title=?,context=? where id =? '
    let params = [body.title, body.context, body.id];
    console.log(JSON.stringify(params));
    poll.getConnection(function (err, conn) {

        conn.query(sql, params, function (err) { // sql를 실행하고 VALUES 으로 params를 보낸다.
            if (err) 
                console.log('query is not excuted. update fail...\n' + err);
            else 
                res.redirect('/list'); //오류 미 발생시 /list 돌아간다.
            }
        );

        conn.release();
    });
});

// -------------------------------------댓글 영역 ---------------------------------------------

// 댓글 입력 시

app.post('/read/sub/insert', function (req, res) {

    let body = req.body;

    let sql = 'INSERT INTO  sboard(id,swriter,scontext,spassword,sdate) VALUES(?,?,?,?,curdat' +
            'e())';
    let params = [id, body.sub_writer, body.sub_context, body.sub_password];

    poll.getConnection(function (err, conn) {

        conn.query(sql, params, function (err) {
            if (err) 
                console.log('query is not excuted. insert fail...\n' + err);
            else 
                res.redirect('/read/' + id); //오류 미 발생시 /list 돌아간다.
            }
        );
        conn.release();
    });
});

// 댓글 수정 시

app.get('/read/sub/update/:sub_number', function (req, res) {

    let sub_number = req.params.sub_number;
    let sql = 'select * from sboard where sid =?'

    poll.getConnection(function (err, conn) {

        conn.query(sql, sub_number, function (err, rows) {
            if (err) 
                console.log('query is not excuted. insert fail...\n' + err);
            else 
                res.render("sub_update.ejs", {sub_list: rows});
            }
        );
        conn.release();
    });

});

// 댓글 수정 시

app.post('/sub_updateAf', function (req, res) {
    let body = req.body;
    console.log("댓글 수정 실행");
    let sql = 'UPDATE sboard set scontext=? where sid =? ';
    let params = [body.sub_context, body.number];
    poll.getConnection(function (err, conn) {

        conn.query(sql, params, function (err) { // sql를 실행하고 VALUES 으로 params를 보낸다.
            if (err) 
                console.log('query is not excuted. update fail...\n' + err);
            else {
                res.render('null.ejs');
                console.log("업데이트 수행 완료")
            }
 
            }
        );

        conn.release();
    });
});

// 댓글 삭제 시

app.get('/read/sub/remove/:number', function (req, res) {
    let sub_id = req.params.number;
    let sql = 'delete from sboard where sid =?'
    console.log("특정 값 : " + sub_id);

    poll.getConnection(function (err, conn) {

        conn.query(sql, sub_id, function (err) {
            if (err) 
                console.log('query is not excuted. insert fail...\n' + err);
            else 
                res.redirect('/read/' + id); //오류 미 발생시 /list 돌아간다.
            }
        );

        conn.release();
    });
});

app.listen(port, () => console.log('Server is running on port 3000...')); //3000포트로 응답한다.