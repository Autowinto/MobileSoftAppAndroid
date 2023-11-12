import dotenv from 'dotenv';

dotenv.config();

const MYSQL_HOST = process.env.MYSQL_HOST || 'localhost';
const MYSQL_DATABASE = process.env.MYSQL_DATABASE || 'db';
const MYSQL_USER = process.env.MYSQL_USER || 'user';
const MYSQL_PASSWORD = process.env.MYSQL_PASSWORD || 'admin';

const MYSQL = {
    host: MYSQL_HOST,
    database: MYSQL_DATABASE,
    user: MYSQL_USER,
    password: MYSQL_PASSWORD,
}

const SERVER_HOSTNAME = process.env.HOST || 'localhost';
const SERVER_PORT = process.env.PORT || 8081;

const SERVER = {
    hostname: SERVER_HOSTNAME,
    port: SERVER_PORT,
}

const config = {
    mysql: MYSQL,
    server: SERVER,
};

export default config;
