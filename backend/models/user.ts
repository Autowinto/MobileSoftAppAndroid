import { DataTypes } from 'sequelize';
import { sequelize } from '../src/initialize';
export interface UserType {
    id: number;
    name: string;
    email: string;
    password: string;
}

const User = sequelize.define(
    "user", {
    id: {
        type: DataTypes.STRING,
        primaryKey: true,
    },
    first_name: {
        type: DataTypes.STRING,
        allowNull: false,
    },
    last_name: {
        type: DataTypes.STRING,
        allowNull: false,
    },
    email: {
        type: DataTypes.STRING,
        allowNull: false,
    },
    phoneNmb: {
        type: DataTypes.STRING,
        allowNull: false,
    },
    password: {
        type: DataTypes.STRING,
        allowNull: false,
    },
    enableNotifs: {
        type: DataTypes.BOOLEAN,
        allowNull: false,
    },
}
);

export default User;