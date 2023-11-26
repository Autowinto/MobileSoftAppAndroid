import { Application, Request, Response } from 'express';
import { OpenApi, Types, textPlain, Schema } from 'ts-openapi';
import { Group, User, UserGroup } from '../../groups/setup-groups';
import { v4 as uuidv4 } from 'uuid';

export async function addMember(req: Request, res: Response) {

    try {
        const user = await User.findByPk(req.body.userId);
        const group = await Group.findByPk(req.body.groupId);

        if (user == null || group == null) {
            throw "Error adding member to group, user or group does not exist";
        }

        await UserGroup.create({
            userId: user.dataValues.id,
            groupId: group.dataValues.id,
        }).catch((err) => {
            throw err
        });

        res.status(201).send(user.dataValues.name + " was added to group: " + group.dataValues.name);
    } catch (error) {
        console.log(error)
        res.status(400).send(error)
    }

}

export function initAddMember(app: Application, openApi: OpenApi) {
    app.post('/members', addMember);

    const commonProperties = {
        userId: Types.String({
            description: "User ID",
            maxLength: 100,
            required: true,
        }),
        groupId: Types.String({
            description: "Group ID",
            required: true,
        }),
    };


    openApi.addPath(
        "/group/members",
        {
            post: {
                summary: "Add Member to Group",
                description: "This operation adds a member to a group",
                operationId: "post-addmember-op",
                requestSchema: {
                    headers: {
                        userId: Types.Uuid({
                            description: "User ID, the user joining the group",
                            required: true,
                            example: "b710e129-4e2c-4448-b605-73b18d297bae",
                        }),
                        groupId: Types.Uuid({
                            description: "Group ID, the grup the user is joining",
                            required: true,
                            example: "b710e129-4e2c-4448-b605-73b18d297bae",
                        }),

                    },
                    body: Types.Object({
                        description: "Group data to create",
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


