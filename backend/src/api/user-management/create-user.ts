import { Application, Request, Response } from 'express';
import { OpenApi, Types, textPlain, Schema } from 'ts-openapi';
import { User, Group, UserGroup } from '../../groups/setup-groups';
import * as moment from 'moment';
import { v4 as uuidv4 } from 'uuid';

export async function createUser(req: Request, res: Response) {
    const user = req.body;
    const uid = uuidv4();

    User.create({
        id: uid,
        name: user.name,
        email: user.email,
        password: user.password,
        created_at: moment.now(),
        updated_at: moment.now()
    }).then(() => {
        res.status(200).send("User added successfully")
    }).catch((err) => {
        console.log(err)
        const error = err.erros[0];
        if (user.name == null || user.email == null || user.password == null) {
            res.status(400).send("Error creating user, bad syntax")
        }
    })
}

export function initCreateUser(app: Application, openApi: OpenApi) {
    app.post('/', createUser)

    const commonProperties = {
        name: Types.String({
            description: "User name",
            maxLength: 100,
            required: true,
        }),
        type: Types.StringEnum({
            description: "User type",
            values: ["Platinum", "Gold", "Silver", "Bronze"],
            required: true,
        }),
    };


    openApi.addPath(
        "/",
        {
            post: {
                summary: "Create User",
                description: "This operation creates a new User",
                operationId: "post-User-op",
                requestSchema: {
                    headers: {
                        requestId: Types.Uuid({
                            description: "Request ID",
                            required: false,
                            example: "b710e129-4e2c-4448-b605-73b18d297bae",
                        }),
                    },
                    body: Types.Object({
                        description: "User data to create",
                        properties: commonProperties,
                    }),
                },
                tags: ["User Operations"],
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


