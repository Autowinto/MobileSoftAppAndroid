import { Application, Request, Response } from "express";
import { OpenApi, Types, textPlain, Schema } from "ts-openapi";
import { User, Group, UserGroup } from "../../groups/setup-groups";
import { Op } from "sequelize";
import * as moment from "moment";
import { v4 as uuidv4 } from "uuid";
import bcrypt from "bcrypt";

export async function createUser(req: Request, res: Response) {
  const user = req.body;
  const uid = uuidv4();

  try {
    const exist = await phoneEmailCheck(user.phoneNmb, user.email);
    if (exist) {
      res.status(400).send("User already exists");
      return;
    }
    const hashedPassword = await hasher(user.password);

    User.create({
      id: uid,
      first_name: user.first_name,
      last_name: user.last_name,
      email: user.email,
      phoneNmb: user.phoneNmb,
      password: hashedPassword,
      enableNotifs: user.enableNotifs,
    })
      .then(() => {
        res.status(200).send("User added successfully");
      })
      .catch((err) => {
        console.log(err);
        const error = err.erros[0];
        res.status(400).send(error.message);
      });
  } catch (err) {
    console.log(err);
    res.status(400).send(err.message);
  }
}
async function hasher(password: string) {
  try {
    const salt = await bcrypt.genSalt(10);

    // Hash the password with the salt
    const hashedPassword = await bcrypt.hash(password, salt);
    return hashedPassword;
  } catch (err) {
    console.log(err);
    throw err;
  }
}
async function phoneEmailCheck(phoneNmb: string, email: string) {
  const existingUser = await User.findOne({
    where: {
      [Op.or]: [{ email: email }, { phoneNmb: phoneNmb }],
    },
  });
  if (existingUser) {
    return true;
  } else return false;
}

export function initCreateUser(app: Application, openApi: OpenApi) {
  app.post("/", createUser);

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
        "/users",
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
