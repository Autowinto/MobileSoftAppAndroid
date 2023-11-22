import {DataTypes} from 'sequelize';
import { sequelize } from '../src/initialize';

const UserGroup = sequelize.define(
    'userGroup', {
        userId: {
            type: DataTypes.STRING,
            primaryKey: true,
          },
          groupId: {
            type: DataTypes.STRING,
            primaryKey: true,
          },
    },
    {
        timestamps: false,
    }
);
export default UserGroup;