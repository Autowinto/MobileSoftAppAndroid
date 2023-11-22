import { DataTypes } from 'sequelize';
import { sequelize } from '../src/initialize';

const Group = sequelize.define(
    "group", {
    id: {
        type: DataTypes.STRING,
        primaryKey: true
    },
    name: {
        type: DataTypes.STRING,
        allowNull: false,
    },
    expenses: {
        type: DataTypes.INTEGER,
        allowNull: false,
    }
}
);


export default Group;