import { DataTypes } from 'sequelize';
import { sequelize } from '../src/initialize';

const User = sequelize.define(
    "user", {
    id: {
        type: DataTypes.STRING,
        primaryKey: true,
    },
    firstName: {
        type: DataTypes.STRING,
        allowNull: false,
    },
    lastName: {
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