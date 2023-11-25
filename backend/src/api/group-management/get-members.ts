import { Application, Request, Response } from 'express';
import { OpenApi, Types, textPlain, Schema } from 'ts-openapi';
import { Group, User, UserGroup } from '../../groups/setup-groups';
import { v4 as uuidv4 } from 'uuid';
import { Model } from 'sequelize';

export type Member = {
    userId: string,
    groupId: string,
}
export async function getMembers(req: Request, res: Response) {

    try {
        const { groupId } = req.body;

        if (groupId == null) {
            throw "Error creating group, no group name or user id was provided";
        }

        const members = await UserGroup.findAll({
            where: { groupId: groupId }
        })

        const promiseArray = members.map(async (member) => {
            return new Promise(async (resolve, reject) => {
                const user = await User.findByPk(member.dataValues.userId);
                delete user.dataValues.password;
                resolve(user)
            })
        })

        await Promise.all(promiseArray).then((values) => {
            res.status(200).send(values)
        })
    } catch (error) {
        console.log(error)
        res.status(400).send(error)
    }

}

export function initGetMembers(app: Application, openApi: OpenApi) {
    app.get('/members', getMembers)

    openApi.addPath(
        "/group/members",
        {
            get: {
                summary: "Get Members of Group",
                description: "This operation gets all members of a group",
                operationId: "get-getmembers-op",
                requestSchema: {
                    headers: {
                        groupdId: Types.String({
                            description: "Group ID",
                            required: true,
                            example: "b710e129-4e2c-4448-b605-73b18d297bae",
                        }),
                    },
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


