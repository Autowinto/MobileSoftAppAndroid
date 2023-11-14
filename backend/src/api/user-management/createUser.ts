import { Application, Request, Response } from 'express';
import { OpenApi, Types, textPlain, Schema } from 'ts-openapi';
import User from '../../../models/user';

export async function createUser(req: Request, res: Response) {
    const u = req.body;
    console.log("WOOOH",req.body)
    User.create({
        id: u.id,
        name: u.name,
        email: u.email,
        password: u.password,
        created_at: u.created_at,
        updated_at: u.updated_at
    })
    res.send("User added")
}

export function initCreateUser(app: Application, openApi: OpenApi) {
    app.post('/create_user', createUser)

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
        "/create_user",
        {
            post: {
                summary: "Create User",
                description: "This operation creates a new User",
                operationId: "post-User-op",
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


