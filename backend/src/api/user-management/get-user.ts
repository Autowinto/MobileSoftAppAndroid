import { Application, Request, Response } from 'express';
import { OpenApi, Types } from 'ts-openapi';
import { User } from '../../groups/setup-groups';

export async function getUser(req: Request, res: Response) {
    try {
        const id = req.params.id;
        console.log(id);
        User.findOne({ where: { id: id } }).then((user) => {
            res.send(user);
        })
    } catch (error) {
        console.log(error);
        res.status(400).send(error);
    }
};

export async function getAllUsers(req: Request, res: Response) {
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
    app.get('/:id/user', getUser);
    openApi.addPath(
        '/users/{id}/user',
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
                    params: {
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
    );
}

