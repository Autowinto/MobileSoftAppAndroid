import { Application, Request, Response } from 'express';
import { OpenApi, Types, textPlain, Schema } from 'ts-openapi';
import {User, Group, UserGroup } from '../../initialize';

export async function createUser(req: Request, res: Response) {
    const u = req.body;
    User.create({
        id: u.id,
        name: u.name,
        email: u.email,
        password: u.password,
    })
    res.send("User added")
}

export async function addToGroup(req: Request, res: Response) {
    const user = await User.findByPk(req.body.userId);
    const group = await Group.findByPk(req.body.groupId);
    await UserGroup.create({
        userId: user.dataValues.id,
        groupId: group.dataValues.id
    });
    res.send(user.dataValues.name + " was added to group: " + group.dataValues.name );
}

export function initAddToGroup(app: Application, openApi: OpenApi) {
    app.put('/group/add', addToGroup)

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
        "/group/add",
        {
            post: {
                summary: "Create User",
                description: "This operation creates a new User",
                operationId: "post-addgroup-op",
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


