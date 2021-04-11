let mysql = require('mysql');
let db_info = {
    host: 'us-cdbr-east-03.cleardb.com',
    port: '3306',
    user: 'bdf1860f130679',
    password: '18e60bc8',
    database: 'heroku_d21d4848ee15928',
    connectionLimit: 20
};


module.exports.poll = function(){
  return mysql.createPool(db_info);
};


