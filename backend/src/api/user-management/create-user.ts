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
console.log(user)
    User.create({
        id: uid,
        firstName: user.firstName,
        lastName: user.lastName,
        email: user.email,
        phoneNmb: user.phoneNmb,
        password: hashedPassword,
        enableNotifs: user.enableNotifs,
    }).then(() => {
        res.status(201).send("User created successfully");
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
    id: Types.String({
      description: "Users id",
      maxLength: 100,
      required: true,
    }),
    firstName: Types.String({
      description: "Users first name",
      maxLength: 100,
      required: true,
    }),
    lastName: Types.String({
      description: "Users last name",
      maxLength: 100,
      required: true,
    }),
    phoneNmb: Types.String({
      description: "Users phone number",
      maxLength: 100,
      required: true,
    }),
    email: Types.String({
      description: "Users email",
      maxLength: 100,
      required: true,
    }),
    password: Types.String({
      description: "Users password",
      maxLength: 100,
      required: true,
    }),
    enableNotifs: Types.Boolean({
      description: "Users notification settings",
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
                    body: Types.Object({
                        description: "User data to create",
                        properties: commonProperties,
                    }),
                },
                tags: ["User Operations"],
                responses: {
                    201: textPlain("Created"),
                    400: textPlain("Bad Request"),
                },
            },
      },
    true
  );
}
