import { DataTypes } from 'sequelize';
import { sequelize } from '../src/initialize';

const ExpenseHistory = sequelize.define(
    'expenseHistory', {
    id: {
        type: DataTypes.STRING,
        primaryKey: true,
    },
    expenseId: {
        type: DataTypes.STRING,
    },
    changes: {
        type: DataTypes.JSON,
    }
},
);
export default ExpenseHistory;