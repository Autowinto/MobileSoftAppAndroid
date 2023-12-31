import { Application, Request, Response, response } from 'express';
import { OpenApi, Types, textPlain, Schema } from 'ts-openapi';
import { Expense, ExpenseHistory } from '../../expenses/setup-expenses';
import { v4 as uuidv4 } from 'uuid';
import { where } from 'sequelize';

export async function editExpense(req: Request, res: Response) {
    try {
        const { expenseId, newAmount } = req.body;
        const expenseHistoryId = uuidv4();

        if (newAmount === undefined) {
            throw "No new amount provided";
        }


        if (!expenseId) {
            throw "Expense ID is required";
        }

        

        await Expense.update({ amount: newAmount }, { where: { id: expenseId } });

        const expense = await Expense.findByPk(expenseId);
        await ExpenseHistory.create({
            id: expenseHistoryId,
            expenseId: expenseId,
            changes: {
                oldAmount: expense.dataValues.amount, // Assuming 'amount' is the field you're updating
                newAmount: newAmount
            }});

        res.status(200).send("Expense amount updated successfully");
    } catch (error) {
        console.log(error);
        res.status(400).send("Error updating expense: " + error);
    }
}

export function initEditExpense(app: Application, openApi: OpenApi) {
    app.put('/', editExpense); 

    const commonProperties = {
        amount: Types.Number({
            description: "Amount",
            required: false,
        }),
        groupId: Types.String({
            description: "Group ID",
            required: false,
        }),
        userId: Types.String({
            description: "User ID",
            required: false,
        }),
        expenseId: Types.String({
            description: "Expense ID",
            required: true,
        }),
    }

    openApi.addPath(
        "/",
        {
            put: {
                summary: "Update Expense",
                description: "Updates an existing Expense",
                operationId: "put-Expense-op",
                requestSchema: {
                    body: Types.Object({
                        description: "Expense update data",
                        properties: commonProperties
                    }),
                },
                tags: ["Expense Operations"],
                responses: {
                    200: textPlain("Expense Updated"),
                    400: textPlain("Bad Request"),
                    401: textPlain("Unauthorized"),
                },
            },
        },
        true
    );
}