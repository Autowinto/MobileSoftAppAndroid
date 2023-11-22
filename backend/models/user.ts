import {DataTypes} from 'sequelize';
import { sequelize } from '../src/initialize';
export interface User {
    id: number;
    name: string;
    email: string;
    password: string;
}

const User = sequelize.define(
    "user",{
        id: {
            type: DataTypes.STRING,
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
        created_at: { //Unix timestamp
            type: DataTypes.STRING,
            allowNull: false,
        },
        updated_at: { //Unix timestamp
            type: DataTypes.STRING,
            allowNull: false,
        }
    }
);

export default User;