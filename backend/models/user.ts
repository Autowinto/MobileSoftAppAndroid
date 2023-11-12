import {Sequelize, DataTypes} from 'sequelize';
import config from '../src/config/config';
export interface User {
    id: number;
    name: string;
    email: string;
    password: string;
    created_at: string;
    updated_at: string;
}

const sequelize = new Sequelize(config.mysql.database, config.mysql.user, config.mysql.password, {
    host: config.mysql.host,
    port: 3306,
    dialect: "mysql",
});

const User = sequelize.define(
    "user",{
        id: {
            type: DataTypes.INTEGER,
            primaryKey: true,
        },
        name: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        email: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        password: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        created_at: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        updated_at: {
            type: DataTypes.STRING,
            allowNull: false,
        }
    }
);
(async () => {
    await sequelize.sync({ force: true });
  })();
export default User;