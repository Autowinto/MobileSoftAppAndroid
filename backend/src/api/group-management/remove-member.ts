import { Application, Request, Response } from 'express';
import { OpenApi, Types, textPlain, Schema } from 'ts-openapi';
import { Group, UserGroup } from '../../groups/setup-groups';
import { v4 as uuidv4 } from 'uuid';

export async function removeMember(req: Request, res: Response) {

    try {
        const { groupId, userId } = req.body;

        if (groupId == null || userId == null) {
            throw "Error removing member, no group id or user id was provided";
        }

        await UserGroup.destroy({
            where: { groupId: groupId, userId: userId }
        }).catch((err) => {
            throw err
        })

        res.status(200).send("Member removed succesfully")
    } catch (error) {
        console.log(error)
        res.status(400).send(error)
    }

}

export function initRemoveMember(app: Application, openApi: OpenApi) {
    app.delete('/members', removeMember)

    const commonProperties = {
        groupdId: Types.String({
            description: "Group ID",
            required: true,
        }),
        userId: Types.String({
            description: "User ID",
            required: true,
        }),
    };


    openApi.addPath(
        "/group/members",
        {
            delete: {
                summary: "Remove Member from Group",
                description: "This operation removes a member from a group",
                operationId: "delete-removemember-op",
                requestSchema: {
                    headers: {
                        groupdId: Types.String({
                            description: "Group ID, the group to remove the member from",
                            required: true,
                            example: "b710e129-4e2c-4448-b605-73b18d297bae",
                        }),
                        userId: Types.Uuid({
                            description: "User ID, the user to remove from the group",
                            required: true,
                            example: "b710e129-4e2c-4448-b605-73b18d297bae",
                        }),
                    },
                    body: Types.Object({
                        description: "Group ID and User ID",
                        properties: commonProperties,
                    }),
                },
                tags: ["Group Operations"],
                responses: {
                    200: textPlain("OK"),
                    400: textPlain("Bad Request"),
                    401: textPlain("Unauthorized"),
                },
            },
        },
        true
    );
}


