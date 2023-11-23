import { Application, Request, Response } from 'express';
import { OpenApi, Types } from 'ts-openapi';
import { User } from '../../groups/setup-groups';

export async function getUser(req: Request, res: Response) {
    // const { id } = req.query;
    // const user = await db.user.findOne({ where: { id } });
    // res.json(user);

    // I just want to see if this works
    res.send({ "name": "John" });
}

export async function getAllUsers(res: Response) {
    const users = User.findAll().then((users) => {
        res.json(users);
    })
};

export function initGetAllUser(app: Application, openApi: OpenApi) {
    app.get('/users', getAllUsers);

    openApi.addPath(
        '/users',
        {
            get: {
                summary: 'Get All Users',
                description: 'This operation gets all Users',
                operationId: 'get-All-Users-op',
                requestSchema: {
                    headers: {
                        requestId: Types.String({
                            description: 'Request ID',
                            required: false,
                            example: 'b710e129-4e2c-4448-b605-73b18d297bae'
                        })
                    }
                },
                tags: ['User Operations'],
                responses: {
                    200: {
                        description: 'OK',
                        content: {
                            'application/json': {
                                schema: {
                                    type: 'array',
                                    description: 'User data',
                                    items: {
                                        type: 'object',
                                        description: 'User',
                                        properties: {
                                            name: {
                                                type: 'string',
                                                description: 'User name',
                                                maxLength: 100,
                                            },
                                            type: {
                                                type: 'array',
                                                description: 'User type',
                                                example: ['Platinum', 'Gold', 'Silver', 'Bronze'],
                                            }
                                        },

                                    }
                                }
                            }
                        }
                    }
                }
            }
        },
        true
    )
}

export function initGetUser(app: Application, openApi: OpenApi) {
    app.get('/user', getUser)


    openApi.addPath(
        '/user',
        {
            get: {
                summary: 'Get User',
                description: 'This operation gets a User',
                operationId: 'get-User-op',
                requestSchema: {
                    headers: {
                        requestId: Types.String({
                            description: 'Request ID',
                            required: false,
                            example: 'b710e129-4e2c-4448-b605-73b18d297bae'
                        })
                    },
                    query: {
                        id: Types.Uuid({
                            description: 'User ID',
                            required: true,
                            example: 'b710e129-4e2c-4448-b605-73b18d297bae'
                        })
                    }
                },
                tags: ['User Operations'],
                responses: {
                    200: {
                        description: 'OK',
                        content: {
                            'application/json': {
                                schema: {
                                    type: 'object',
                                    description: 'User data',
                                    properties: {
                                        name: {
                                            type: 'string',
                                            description: 'User name',
                                            maxLength: 100,
                                        },
                                        type: {
                                            type: 'array',
                                            description: 'User type',
                                            example: ['Platinum', 'Gold', 'Silver', 'Bronze'],
                                        }
                                    },

                                }
                            }
                        }
                    }
                }
            }
        },
        true
    )
}