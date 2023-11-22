import { Application, Request, Response } from 'express';
import { OpenApi, Types, textPlain, Schema } from 'ts-openapi';
import { Group } from '../../groups/setup-groups';

export async function createGroup(req: Request, res: Response) {
    const user = req.body;
    Group.create({
        id: user.id,
        name: user.name,
        expenses: 0,
    })
    res.send("Group added")
}

export function initCreateGroup(app: Application, openApi: OpenApi) {
    app.post('/createGroup', createGroup)

    const commonProperties = {
        name: Types.String({
            description: "Group Name",
            maxLength: 100,
            required: true,
        }),
        type: Types.String({
            description: "User type",
            required: true,
        }),
    };


    openApi.addPath(
        "/create_group",
        {
            post: {
                summary: "Create Group",
                description: "This operation creates a new Group",
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
                tags: ["Group Operations"],
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


