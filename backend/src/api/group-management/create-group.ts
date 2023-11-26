import { Application, Request, Response } from 'express';
import { OpenApi, Types, textPlain, Schema } from 'ts-openapi';
import { Group, UserGroup } from '../../groups/setup-groups';
import { v4 as uuidv4 } from 'uuid';

export async function createGroup(req: Request, res: Response) {

    try {
        const { name, userId, description} = req.body;
        const groupId = uuidv4();

        if (name == null || userId == null) {
            throw "Error creating group, no group name or user id was provided";
        }

        Group.create({
            id: groupId,
            name,
            description,
            totalExpense: 0,
            ownerId: userId
        }).catch((err) => {
            throw err
        });

        UserGroup.create({
            userId,
            groupId: groupId,
            expenses: []
        }).catch((err) => {
            throw err
        });

        res.status(201).send({message: "Group created succesfully"})
    } catch (error) {
        console.log(error)
        res.status(400).send(error)
    }

}

export function initCreateGroup(app: Application, openApi: OpenApi) {
    app.post('/', createGroup)

    const commonProperties = {
        name: Types.String({
            description: "Name",
            maxLength: 100,
            required: true,
        }),
        userId: Types.String({
            description: "User ID",
            required: true,
        }),
    };


    openApi.addPath(
        "/",
        {
            post: {
                summary: "Create Group",
                description: "This operation creates a new Group",
                operationId: "post-Group-op",
                requestSchema: {
                    headers: {
                        name: Types.String({
                            description: "Name of Group",
                            required: true,
                            example: "Bjarkes gruppe",
                        }),
                        
                        userId: Types.Uuid({
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


