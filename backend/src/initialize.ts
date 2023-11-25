import config from './config/config'
import * as mysql from 'mysql2/promise'
import { Sequelize } from 'sequelize';
import { RowDataPacket } from 'mysql2';

export let sequelize: Sequelize = null;

export async function initialize() {
    return new Promise(async (resolve, reject) => {
        const { host, user, password, database } = config.mysql
        const port = 3306;
        const connection = await mysql.createConnection({ host, port, user, password });

        // Check if database exists

        const [databases] = await connection.query('SHOW DATABASES;');
        const databaseExists = (databases as RowDataPacket[]).some((db: any) => db.Database === database);

        if (!databaseExists) {
            // Create database if it doesn't exist
            await connection.query(`CREATE DATABASE IF NOT EXISTS \`${database}\`;`);
            console.log(`Database ${database} created!`);
        }

        // Initialize Sequelize
        sequelize = new Sequelize(database, user, password, {
            host: host,
            port: port,
            dialect: "mysql",
        })
        resolve("Database initialized!");
    })
}