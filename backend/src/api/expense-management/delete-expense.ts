import { Application, Request, Response, response } from 'express';
import { OpenApi, Types, textPlain, Schema } from 'ts-openapi';
import { Expense, User, Group } from '../../expenses/setup-expenses';

export async function deleteExpense(req:Request, res: Response) {
    try{
        const {expenseId} = req.body;

        if(!expenseId){
            throw "Expense ID is required"
        }

        const result = await Expense.destroy({where: { id: expenseId}});

        if (result === 0){
            throw "No Expense found with the provided ID"
        }

        res.status(200).send("Expense deleted successfully");
    } catch (error) {
        console.log(error);
        res.status(400).send("Error deleting expense: " + error);
    }
}    

export function initDeleteExpense(app: Application, openApi: OpenApi) {
    app.put('/', deleteExpense); 

    const commonProperties = {
        expenseId: Types.String({
            description: "Expense ID",
            required: true,
        }),
    }

    openApi.addPath(
        "/",
        {
            put: {
                summary: "Delete Expense",
                description: "Deletes an existing Expense",
                operationId: "delete-Expense-op",
                requestSchema: {
                    body: Types.Object({
                        description: "Expense delete data",
                        properties: commonProperties
                    }),
                },
                tags: ["Expense Operations"],
                responses: {
                    200: textPlain("Expense Deleted"),
                    400: textPlain("Bad Request"),
                    401: textPlain("Unauthorized"),
                },
            },
        },
        true
    );
}