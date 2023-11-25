import { Application, Request, Response } from 'express';
import { OpenApi, Types, textPlain } from 'ts-openapi';
import { Group } from '../../groups/setup-groups';

export async function deleteGroup(req: Request, res: Response) {

    try {
        const { id, name } = req.body;

        if (id == null || name == null) {
            throw "Error creating group, no group name or user id was provided";
        }

        await Group.findByPk(id).then((group) => {
            if (group == null) {
                throw "Group not found"
            } else {
                if (group.dataValues.name != name) {
                    throw "Group name does not match"
                } else {
                    Group.destroy({
                        where: { id: id }
                    }).catch((err) => {
                        throw err
                    })
                    res.status(200).send("Group Deleted succesfully")
                }
            }
        }).catch((err) => {
            throw err
        })

    } catch (error) {
        console.log(error)
        res.status(400).send(error)
    }

}

export function initDeleteGroup(app: Application, openApi: OpenApi) {
    app.delete('/', deleteGroup)

    const commonProperties = {
        id: Types.String({
            description: "Group ID",
            maxLength: 100,
            required: true,
        }),
        name: Types.String({
            description: "Name of Group",
            required: true,
        }),
    };

    openApi.addPath(
        "/groups",
        {
            delete: {
                summary: "Delete group",
                description: "This operation deletes a group",
                operationId: "post-deletegroup-op",
                requestSchema: {
                    headers: {
                        id: Types.String({
                            description: "Group ID",
                            required: true,
                            example: "b710e129-4e2c-4448-b605-73b18d297bae",
                        }),
                        name: Types.Uuid({
                            description: "Group name",
                            required: true,
                            example: "Bjarkes gruppe",
                        }),
                    },
                    body: Types.Object({
                        description: "Group data to create",
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


