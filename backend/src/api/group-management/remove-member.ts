import { Application, Request, Response } from 'express';
import { OpenApi, Types, textPlain, Schema } from 'ts-openapi';
import { Group, UserGroup } from '../../groups/setup-groups';
import { v4 as uuidv4 } from 'uuid';

export async function removeMember(req: Request, res: Response) {

    try {
        const { group_name: groupName, user_id: userId } = req.body;
        const groupId = uuidv4();

        if (groupName == null || userId == null) {
            throw "Error creating group, no group name or user id was provided";
        }

        Group.create({
            id: groupId,
            name: groupName,
            totalExpense: 0,
            ownerId: userId
        }).catch((err) => {
            throw err
        })

        UserGroup.create({
            userId,
            groupId: groupId,
            expenses: []
        }).catch((err) => {
            throw err
        })

        res.status(201).send("Group created succesfully")
    } catch (error) {
        console.log(error)
        res.status(400).send(error)
    }

}

export function initRemoveMember(app: Application, openApi: OpenApi) {
    app.delete('/members/', removeMember)

    const commonProperties = {
        group_name: Types.String({
            description: "Group Name",
            maxLength: 100,
            required: true,
        }),
        user_id: Types.String({
            description: "User ID",
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
                        group_name: Types.String({
                            description: "Name of Group",
                            required: true,
                            example: "Bjarkes gruppe",
                        }),
                        user_id: Types.Uuid({
                            description: "User ID, the user creating the group",
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


