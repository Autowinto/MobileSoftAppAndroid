import { DataTypes } from 'sequelize';
import { sequelize } from '../src/initialize';

const Expense = sequelize.define(
    'expense', {
    id: {
        type: DataTypes.STRING,
        primaryKey: true,
    },
    userId: {
        type: DataTypes.STRING,
    },
    groupId: {
        type: DataTypes.STRING,
    },
    amount: {
        type: DataTypes.INTEGER,
        allowNull: false,
    },
},
);
export default Expense;