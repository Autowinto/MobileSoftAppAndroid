import User from '../../models/user';
import Group from '../../models/group';
import UserGroup from '../../models/usergroup';
import { sequelize } from '../initialize';

Group.belongsToMany(User, {through: UserGroup, foreignKey: 'groupId'});
User.belongsTo(Group, {foreignKey: 'groupId'});
User.belongsToMany(Group, {through: UserGroup, foreignKey: 'userId'});
sequelize.sync();

export {User, Group, UserGroup}