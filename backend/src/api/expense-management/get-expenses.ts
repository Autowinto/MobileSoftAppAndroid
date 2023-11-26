import { Application, Request, Response, response } from 'express';
import { OpenApi, Types, textPlain, Schema } from 'ts-openapi';
import { Expense, User, Group } from '../../expenses/setup-expenses';


export async function getAllExpenses(req: Request, res: Response) {
    try {
        const expenses = await Expense.findAll();

        if (expenses.length === 0) {
            return res.status(404).send("No expenses found");
        }

        res.status(200).json(expenses);
    } catch (error) {
        console.log(error);
        res.status(500).send("Error retrieving expenses: " + error);
    }
}

export function initGetAllExpenses(app: Application, openApi: OpenApi) {
    app.get('/expenses', getAllExpenses); 
    
    openApi.addPath(
        "/expenses",
        {
            get: {
                summary: "Get All Expenses",
                description: "This operation retrieves all expenses",
                operationId: "get-all-expenses",
                tags: ["Expense Operations"],
                responses: {
                    201: textPlain("Expenses retrieved"),
                    404: textPlain("No expenses found"),
                    500: textPlain("Internal Server Error")
                },
            },
        },
        true
    );
    
}
