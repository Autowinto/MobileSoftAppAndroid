import { Application, Request, Response } from 'express';
import { OpenApi, Types, textPlain, Schema } from 'ts-openapi';
import {Group } from '../../initialize';

export async function createGroup(req: Request, res: Response) {
    const u = req.body;
    Group.create({
        id: u.id,
        name: u.name,
        moneyAmount: u.moneyAmount
    })
    res.send("Group added")
}

export function initCreateGroup(app: Application, openApi: OpenApi) {
    app.post('/createGroup', createGroup)

    const commonProperties = {
        name: Types.String({
            description: "User name",
            maxLength: 100,
            required: true,
        }),
        type: Types.StringEnum({
            description: "User type",
            values: ["Platinum", "Gold", "Silver", "Bronze"],
            required: true,
        }),
    };


    openApi.addPath(
        "/createGroup",
        {
            post: {
                summary: "Create User",
                description: "This operation creates a new User",
                operationId: "post-Group-op",
                requestSchema: {
                    headers: {
                        requestId: Types.Uuid({
                            description: "Request ID",
                            required: false,
                            example: "b710e129-4e2c-4448-b605-73b18d297bae",
                        }),
                    },
                    body: Types.Object({
                        description: "User data to create",
                        properties: commonProperties,
                    }),
                },
                tags: ["User Operations"],
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


