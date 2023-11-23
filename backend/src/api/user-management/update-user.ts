import { Application, Request, Response } from 'express';
import { OpenApi, Types, textPlain, Schema } from 'ts-openapi';
import { User, Group, UserGroup } from '../../groups/setup-groups';
import * as moment from 'moment';
import { v4 as uuidv4 } from 'uuid';
import { where } from 'sequelize';
import * as UserType from '../../../shared_models/user'

export async function updateUser(req: Request, res: Response) {
    const { name, email, password, id } = req.body
    console.log(id)
    User.update({
        name,
        email,
        password,
        updated_at: moment.now()
    },
        { where: { id: id } }
    ).then((response) => {
        res.status(200).send('User updated succesfully')
    }).catch((err) => {
        res.status(400).send('User could not be updated')
    })
}

export async function initUpdateUser(app: Application, openApi: OpenApi) {
    app.post('/', updateUser)

    const commonProperties = {
        name: Types.String({
            description: "Name",
            maxLength: 100,
        }),
        email: Types.String({
            description: "Email",
        }),
        password: Types.String({
            description: "Password",
            maxLength: 30,
        })
    };


    openApi.addPath(
        "/",
        {
            post: {
                summary: "Update User",
                description: "This operation updates a User",
                operationId: "post-update-the-user-op",
                requestSchema: {
                    headers: {
                        id: Types.Uuid({
                            description: "Request ID",
                            required: false,
                            example: "b710e129-4e2c-4448-b605-73b18d297bae",
                        }),
                    },
                    body: Types.Object({
                        description: "User data to update",
                        properties: commonProperties,
                    }),
                },
                tags: ["User Operations"],
                responses: {
                    201: textPlain("Updated"),
                    400: textPlain("Bad Request"),
                    401: textPlain("Unauthorized"),
                },
            },
        },
        true
    );
}


