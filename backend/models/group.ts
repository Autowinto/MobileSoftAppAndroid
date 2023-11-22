import { DataTypes } from 'sequelize';
import { sequelize } from '../src/initialize';

const Group = sequelize.define(
    "group", {
    id: {
        type: DataTypes.STRING,
        primaryKey: true
    },
    user_ids: {
        type: DataTypes.STRING,
        allowNull: false,
    },
    name: {
        type: DataTypes.STRING,
        allowNull: false,
    },
    totalExpense: {
        type: DataTypes.INTEGER,
        allowNull: false,
    }
}
);


export default Group;