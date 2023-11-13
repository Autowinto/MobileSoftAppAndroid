import { DataTypes} from 'sequelize';
import { sequelize } from '../src/initialize';

const Group = sequelize.define(
    "group", {
        id: {
            type: DataTypes.INTEGER,
            primaryKey: true
        },
        name: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        moneyAmount: {
            type: DataTypes.INTEGER,
            allowNull: false,
        }

    }
);


export default Group;