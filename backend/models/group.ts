import { DataTypes } from 'sequelize';
import { sequelize } from '../src/initialize';
import { group } from 'console';

const GroupTable = sequelize.define(
    "grouptable", {
    id: {
        type: DataTypes.STRING,
        primaryKey: true
    },
    name: {
        type: DataTypes.STRING,
        allowNull: false,
    },
    description: {
        type: DataTypes.STRING,
        allowNull: true,
    },
    totalExpense: {
        type: DataTypes.INTEGER,
        allowNull: false,
    },
    ownerId: {
        type: DataTypes.STRING,
        allowNull: false,
    },
}
);


export default GroupTable;