import { Application, Request, Response, response } from 'express';
import { OpenApi, Types, textPlain, Schema } from 'ts-openapi';
import { Expense, User, Group } from '../../expenses/setup-expenses';

export async function showExpense(req: Request, res: Response){
    try{
        const { expenseId } = req.body;

        if(!expenseId){
            throw "Expense ID is required"
        }

        const expense = await Expense.findByPk(expenseId);

        if(!expense){
            res.status(404).send("Expense not found");
        }else {
            res.status(200).json(expense);
        }
    }catch(error){
        console.log(error);
        res.status(400).send("Error retrieving expense: " + error);
    }
} 

export function initGetExpenseDetails(app: Application, openApi: OpenApi){
   app.get('/', showExpense)
   
   const commonProperties = {
    expenseId: Types.String({
        description: "ExpenseID",
        required: true
    })
};

   openApi.addPath(
    "/",
    {
        post: {
            summary: "Update User",
            description: "This operation updates a User",
            operationId: "post-update-user-op",
            requestSchema: {
                headers: {
                    id: Types.Uuid({
                        description: "Request ID",
                        required: false,
                        example: "b710e129-4e2c-4448-b605-73b18d297bae",
                    }),
                },
                body: Types.Object({
                    description: "User data to update",
                    properties: commonProperties,
                }),
            },
            tags: ["User Operations"],
            responses: {
                201: textPlain("Updated"),
                400: textPlain("Bad Request"),
                401: textPlain("Unauthorized"),
            },
        },
    },
    true
);
}