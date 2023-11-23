import { DataTypes } from 'sequelize';
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
  expenses: {
    type: DataTypes.STRING,
    get: function () {
      return JSON.parse(this.getDataValue('expenses'));
    },
    set: function (val) {
      this.setDataValue('expenses', JSON.stringify(val));
    },
  }
},
  {
    timestamps: false,
  }
);
export default UserGroup;