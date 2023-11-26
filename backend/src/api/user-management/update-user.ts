import { Application, Request, Response } from 'express';
import { OpenApi, Types, textPlain, Schema } from 'ts-openapi';
import { User, Group, UserGroup } from '../../groups/setup-groups';
import { getUser } from './get-user';
import * as moment from 'moment';
import { v4 as uuidv4 } from 'uuid';
import { where } from 'sequelize';
import * as UserType from '../../../shared_models/user'

export async function updateUser(req: Request, res: Response) {
    const user = req.body;

    User.findOne({ where: { id: user.id } }).then((response) => {
        if (response) {
            putUser(user, res);
        } else {
            res.status(400).send('User does not exist');
        }
    });
}

async function putUser(user: UserType.user, res: Response) {
    try {
        User.update({
            first_name: user.first_name,
            last_name: user.last_name,
            email: user.email,
            password: user.password,
            phoneNmb: user.phoneNmb,
            enableNotifs: user.enableNotifs,
            updated_at: moment.now()
        },
            { where: { id: user.id } }
        ).then(() => {
            res.status(200).send('User updated succesfully')
        }).catch(() => {
            res.status(400).send('User could not be updated')
        })
    } catch (err) {
        res.status(400).send(err.message);
    }
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
        "/users",
        {
            post: {
                summary: "Update User",
                description: "This operation updates a User",
                operationId: "post-update-user-op",
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


