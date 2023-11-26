import { Application, Request, Response } from 'express';
import { OpenApi, Types } from 'ts-openapi';
import { User } from '../../groups/setup-groups';

export async function getUser(req: Request, res: Response) {
    try {
        const id = req.params.id;
        console.log(id);
        User.findOne({ where: { id: id } }).then((user) => {
            if (user) {
                res.status(200).send(user);
            }
            else
            res.status(404).send('User does not exist');
            return;
            
        })
    } catch (error) {
        console.log(error);
        res.status(400).send(error);
    }
};

export async function getAllUsers(req: Request, res: Response) {
    try {
    User.findAll().then((users) => {
        if(users) {
        res.status(200).send(users);
        }
        else
        res.status(404).send('No users found');
    })
} catch (error) {
    throw error;
}
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
                                            id: {
                                                type: 'string',
                                                description: "Users id",
                                                maxLength: 100,
                                              },
                                              firstName: {
                                                type: 'string',
                                                description: "Users first name",
                                                maxLength: 100,
                                                
                                              },
                                              lastName: {
                                                type: 'string',
                                                description: "Users last name",
                                                maxLength: 100,
                                                
                                              },
                                              phoneNmb:{
                                                type: 'string',
                                                description: "Users phone number",
                                                maxLength: 100,
                                                
                                              },
                                              email: {
                                                type: 'string',
                                                description: "Users email",
                                                maxLength: 100,
                                                
                                              },
                                              password: {
                                                type: 'string',
                                                description: "Users password",
                                                maxLength: 100,
                                              },
                                              enableNotifs: {
                                                type: 'boolean',
                                                description: "Users notification settings", 
                                              },
                                        },

                                    }
                                }
                            }
                        }
                    },
                    404: {
                        description: 'Not Found',
                        content: {
                            'text/plain': {
                                schema: {
                                    type: 'array',
                                    description: 'No users found',
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
    app.get('/:id/users', getUser);
    openApi.addPath(
        '/api/users/:id',
        {
            get: {
                summary: 'Get User',
                description: 'This operation gets a User',
                operationId: 'get-User-op',
                requestSchema: {
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
                                        id: {
                                            type: 'string',
                                            description: "Users id",
                                            maxLength: 100,
                                          },
                                          firstName: {
                                            type: 'string',
                                            description: "Users first name",
                                            maxLength: 100,
                                            
                                          },
                                          lastName: {
                                            type: 'string',
                                            description: "Users last name",
                                            maxLength: 100,
                                            
                                          },
                                          phoneNmb:{
                                            type: 'string',
                                            description: "Users phone number",
                                            maxLength: 100,
                                            
                                          },
                                          email: {
                                            type: 'string',
                                            description: "Users email",
                                            maxLength: 100,
                                            
                                          },
                                          password: {
                                            type: 'string',
                                            description: "Users password",
                                            maxLength: 100,
                                          },
                                          enableNotifs: {
                                            type: 'boolean',
                                            description: "Users notification settings", 
                                          },
                                    },

                                }
                            }
                        }
                    },
                    404: {
                        description: 'Not Found',
                        content: {
                            'text/plain': {
                                schema: {
                                    type: 'array',
                                    description: 'User not found',
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

