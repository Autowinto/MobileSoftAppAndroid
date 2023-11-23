import User from '../../models/user';
import Group from '../../models/group';
import Expense from '../../models/expense';
import ExpenseHistory from '../../models/expenseHistory';
import { sequelize } from '../initialize';

// User-Expense one-to-many relationship
User.hasMany(Expense, { foreignKey: 'userId' });
Expense.belongsTo(User, { foreignKey: 'userId' });

// Group-Expense one-to-many relationship
Group.hasMany(Expense, { foreignKey: 'groupId' });
Expense.belongsTo(Group, { foreignKey: 'groupId' });

Expense.hasMany(ExpenseHistory, {foreignKey: 'expenseId'})
ExpenseHistory.belongsTo(Expense, { foreignKey: 'expenseId' });
sequelize.sync();

export { User, Group, Expense, ExpenseHistory}