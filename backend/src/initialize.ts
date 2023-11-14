import config from './config/config'
import mysql     from 'mysql2'

export async function initialize() {
    const {host, user, password, database} = config.mysql
    console.log(config.mysql)
    const port = 3306;
    console.log("WOOOOH")
    const connection = mysql.createConnection({ host, port, user, password });
    // const connection = await mysql.createConnection({ host, port, user, password });
    connection.query(`CREATE DATABASE IF NOT EXISTS \`${database}\`;`);
}
