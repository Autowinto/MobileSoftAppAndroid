import { Application, Request, Response } from 'express';
import { OpenApi, Types } from 'ts-openapi';
import { User, UserGroup, Group } from '../../groups/setup-groups';

export async function getGroupsByUser(req: Request, res: Response) {
    try {
        const id = req.params.id;
        console.log(id);
        if (id == null) {
            throw "Error getting user, no user id was provided";
        }

        const userGroups = await UserGroup.findAll({ where: { userId: id } });

        const promiseArray = userGroups.map(async (group) => {
            return new Promise(async (resolve, reject) => {
                const groupData = await Group.findByPk(group.dataValues.groupId);
                resolve(groupData);
            });
        });


        await Promise.all(promiseArray).then((values) => {
            res.status(200).send(values);
        });
    } catch (error) {
        console.log(error);
        res.status(400).send(error);
    }
};

export function initGetGroupsByUser(app: Application, openApi: OpenApi) {
    app.get('/:id/groups', getGroupsByUser);

    openApi.addPath(
        '/users/:id/groups',
        {
            get: {
                summary: 'Get All Groups of User',
                description: 'This operation gets all Groups of a User',
                operationId: 'get-All-Groups-of-User-op',
                requestSchema: {
                    params: {
                        id: Types.String({
                            description: 'User ID',
                            required: true,
                            example: 'b710e129-4e2c-4448-b605-73b18d297bae',
                        })
                    },
                },
                tags: ['User Operations'],
                responses: {
                    200: {
                        description: 'OK',
                        content: {
                            'application/json': {
                                schema: {
                                    type: 'array',
                                    description: 'Groups of User',
                                    items: {
                                        type: 'object',
                                        description: 'Group',
                                        properties: {
                                            id: {
                                                type: 'string',
                                                description: 'Group id',
                                            },
                                            name: {
                                                type: 'string',
                                                description: 'Group name',
                                            },
                                            totalExpense: {
                                                type: 'number',
                                                description: 'Total expense of group',
                                            },
                                            ownerId: {
                                                type: 'string',
                                                description: 'Owner id',
                                            },
                                        }
                                    }
                                },
                            }

                        }
                    }
                }
            }
        },
        true
    )
};