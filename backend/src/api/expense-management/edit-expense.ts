import { Application, Request, Response } from 'express';
import { OpenApi, Types, textPlain, Schema } from 'ts-openapi';
import { Expense, User, Group } from '../../expenses/setup-expenses';

export async function editExpense(req: Request, res: Response){
    try{
        const {expenseId, newAmount, newGroupId, newUserId} = req.body;

        if(!newAmount && !newGroupId && !newUserId){
            throw "No new information provided";
        }

        const expense = await Expense.findByPk(expenseId);
        if(!expense){
            throw "Expense not found";
        }

        if(newAmount) expense.dataValues.amount = newAmount;
        if(newGroupId) expense.dataValues.groupId = newGroupId;
        if(newUserId) expense.dataValues.userId = newUserId;

        expense.save();

        res.status(200).send("Expense Updated")
    }
    catch(error){
        console.log(error);
        res.status(400).send(error);
    }
}

export function initEditExpense(app: Application, openApi: OpenApi) {
    app.put('/expense', editExpense); 

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
        "/expense",
        {
            put: {
                summary: "Update Expense",
                description: "Updates an existing Expense",
                operationId: "put-Expense-op",
                requestSchema: {
                    body: Types.Object({
                        description: "Expense update data",
                        properties: {
                            expenseId: Types.String({ required: true }),
                            newAmount: Types.Number({ required: false }),
                            newGroupId: Types.String({ required: false }),
                            newUserId: Types.String({ required: false }),
                        },
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