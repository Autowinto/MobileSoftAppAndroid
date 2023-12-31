import { Application, Request, Response } from "express";
import { OpenApi, Types, textPlain, Schema } from "ts-openapi";
import { v4 as uuidv4 } from "uuid";
import { Expense, User, Group } from "../../expenses/setup-expenses";

export async function createExpense(req: Request, res: Response) {
  try {
    const { userId, groupId, amount, name } = req.body;

    console.log(req.body);
    const expenseId = uuidv4();

    if (userId == null || groupId == null || name == null || amount == null) {
      throw "Error creating expense, missing amount, group ID or user ID";
    }
    Expense.create({
      id: expenseId,
      amount,
      name,
      groupId: groupId,
      userId: userId,
    }).catch((err) => {
      throw err;
    });

    const group = await Group.findByPk(groupId);
    if (!group) {
      throw "Group not found";
    }

    const updatedTotal =
      parseFloat(group.dataValues.totalExpense) + parseFloat(amount);
    console.log(updatedTotal);
    await Group.update(
      { totalExpense: updatedTotal },
      { where: { id: groupId } }
    );

    res.status(201).send("Expense created successfully");
  } catch (error) {
    console.log(error);
    res.status(400).send(error);
  }
}

export function initCreateExpense(app: Application, openApi: OpenApi) {
  app.post("/", createExpense);

  const commonProperties = {
    amount: Types.Number({
      description: "Amount",
      required: true,
    }),
    name: Types.String({
      description: "Name",
      required: true,
    }),
    groupId: Types.String({
      description: "Group ID",
      required: true,
    }),
    userId: Types.String({
      description: "User ID",
      required: true,
    }),
  };

  openApi.addPath(
    "/expense",
    {
      post: {
        summary: "Create Expense",
        description: "This operation creates a new Expense in a group",
        operationId: "post-Expense-op",
        requestSchema: {
          body: Types.Object({
            description: "Expense data to create",
            properties: commonProperties,
          }),
        },
        tags: ["Expense Operations"],
        responses: {
          201: textPlain("Created"),
          400: textPlain("Bad Request"),
          401: textPlain("Unauthorized"),
        },
      },
    },
    true
  );
}
