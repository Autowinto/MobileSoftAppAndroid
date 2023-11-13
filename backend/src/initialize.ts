import config from './config/config'
const mysql = require('mysql2/promise');
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
    await connection.query(`CREATE DATABASE IF NOT EXISTS \`${database}\`;`);
}
import User from '../models/user';
import Group from '../models/group';
import UserGroup from '../models/usergroup'
Group.belongsToMany(User, {through: UserGroup, foreignKey: 'groupId'});
User.belongsTo(Group, {foreignKey: 'groupId'});
User.belongsToMany(Group, {through: UserGroup, foreignKey: 'userId'});
sequelize.sync()

export {User, Group, UserGroup}