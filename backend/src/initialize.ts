import config from './config/config'
import * as mysql from 'mysql2/promise'
import {Sequelize} from 'sequelize';



export const sequelize = new Sequelize(config.mysql.database, config.mysql.user, config.mysql.password, {
    host: config.mysql.host,
    port: 3306,
    dialect: "mysql",
});
export async function initialize() {
    const {host, user, password, database} = config.mysql
    const port = 3306;
    const connection = await mysql.createConnection({ host, port, user, password });
    await connection.query(`CREATE DATABASE IF NOT EXISTS \`${database}\`;`).then(() => {

    })
}

