import config from './config/config'
const mysql = require('mysql2/promise');
import {Sequelize} from 'sequelize';

export async function initialize() {
    const {host, user, password, database} = config.mysql
    const port = 3306;
    const connection = await mysql.createConnection({ host, port, user, password });
    await connection.query(`CREATE DATABASE IF NOT EXISTS \`${database}\`;`);
}
