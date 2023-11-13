import {DataTypes} from 'sequelize';
import { sequelize } from '../src/initialize';

const UserGroup = sequelize.define(
    'userGroup', {
        userId: {
            type: DataTypes.INTEGER,
            primaryKey: true,
          },
          groupId: {
            type: DataTypes.INTEGER,
            primaryKey: true,
          },
    },
    {
        timestamps: false,
    }
);
export default UserGroup;