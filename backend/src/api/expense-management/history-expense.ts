import { Request, Response } from 'express';
import { OpenApi, Types, textPlain, Schema } from 'ts-openapi';
import { ExpenseHistory } from '../../expenses/setup-expenses'; // Adjust the import path and model name as needed
import { Application } from 'express-serve-static-core';


export async function getExpenseHistory(req: Request, res: Response) {
    try {
        const { expenseId } = req.body;

        if (!expenseId) {
            throw "Expense ID is required";
        }


        const history = await ExpenseHistory.findAll({
            where: { expenseId: expenseId },
            order: [['updatedAt', 'DESC']] // Assuming you have a timestamp for updates
        });

        if (history.length === 0) {
            res.status(404).send("No history found for the specified expense");
        } else {
            res.status(200).json(history);
        }
    } catch (error) {
        console.log(error);
        res.status(400).send("Error retrieving expense history: " + error);
    }
}

export function initGetExpenseHistory(app: Application, openApi: OpenApi){
    app.get('/history', getExpenseHistory);

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
                summary: "Get Expense History",
                description: "Get History of an existing Expense",
                operationId: "get-Expense-op",
                requestSchema: {
                    body: Types.Object({
                        description: "Expense history data",
                        properties: commonProperties
                    }),
                },
                tags: ["Expense Operations"],
                responses: {
                    200: textPlain("ExpenseHistory Recieved"),
                    400: textPlain("Bad Request"),
                    401: textPlain("Unauthorized"),
                },
            },
        },
        true
    );
}
