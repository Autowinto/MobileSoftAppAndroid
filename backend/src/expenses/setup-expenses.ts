import User from '../../models/user';
import Group from '../../models/group';
import Expense from '../../models/expense';
import { sequelize } from '../initialize';

// User-Expense one-to-many relationship
User.hasMany(Expense, { foreignKey: 'userId' });
Expense.belongsTo(User, { foreignKey: 'userId' });

// Group-Expense one-to-many relationship
Group.hasMany(Expense, { foreignKey: 'groupId' });
Expense.belongsTo(Group, { foreignKey: 'groupId' });
sequelize.sync();

export { User, Group, Expense}