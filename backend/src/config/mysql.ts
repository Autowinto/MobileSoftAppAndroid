import mysql from 'mysql'
import config from './config'

const params = {
    user: config.mysql.user,
    password: config.mysql.password,
    host: config.mysql.host,
    database: config.mysql.database,
}

const Connect = () => new
    Promise<mysql.Connection>((resolve, reject) => {
        const connection = mysql.createConnection(params)

        connection.connect((err) => {
            if (err) {
                reject(err)
                return;
            }

            resolve(connection)
        })
    })

const Query = async (connection: mysql.Connection, query: string) =>
    new Promise((resolve, reject) => {
        connection.query(query, (err, results) => {
            if (err) {
                reject(err)
                return;
            }

            resolve(results)
        })
    })
export { Connect, Query };

