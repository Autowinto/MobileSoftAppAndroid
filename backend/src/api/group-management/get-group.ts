import { Application, Request, Response } from 'express';
import { OpenApi, Types, textPlain, Schema } from 'ts-openapi';
import { Group } from '../../groups/setup-groups';

export async function getGroup(req: Request, res: Response) {

    try {
        const { id } = req.params;

        if (id == null) {
            throw "Error getting group, no group id was provided";
        }

        const group = await Group.findByPk(id)

        res.status(200).send(group)

    } catch (error) {
        console.log(error)
        res.status(400).send(error)
    }

}

export function initGetGroup(app: Application, openApi: OpenApi) {
    app.get('/:id', getGroup)

    openApi.addPath(
        "/groups/{id}",
        {
            get: {
                summary: "Get a Group",
                description: "This operation gets a group by id",
                operationId: "get-getgroup-op",
                requestSchema: {
                    headers: {
                        groupId: Types.String({
                            description: "Group ID",
                            required: true,
                            example: "b710e129-4e2c-4448-b605-73b18d297bae",
                        }),
                    },
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


