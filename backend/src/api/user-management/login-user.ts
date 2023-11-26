import { Application, Request, Response } from "express";
import { getUser } from "./get-user";
import { User } from "../../groups/setup-groups";
import bcrypt from "bcrypt";
import { OpenApi, Types, textPlain } from "ts-openapi";

export async function login(req: Request, res: Response) {
  try {
    const { email, password } = req.body as { email: string; password: string };
    console.log(password);

    const user = await User.findOne({ where: { email } });
    if (user) {
      const userPassword = user.getDataValue("password");
      const isPasswordValid = await bcrypt.compare(password, userPassword);
      if (isPasswordValid) {
        res.status(200).send(user);
      } else {
        res.status(401).send("Invalid password");
      }
    } else {
      res.status(404).send("User not found");
    }
  } catch (error) {
    res.status(400).send(error);
  }
}



export function initLogin(app: Application, openApi : OpenApi) {
  const commonProperties = {
    email: Types.String({
      description: "Email",
      maxLength: 100,
      required: true,
    }),
    password: Types.String({
      description: "Password",
      maxLength: 100,
      required: true,
    }),
  };

  app.post("/login", login);
  openApi.addPath("/login", {
    post: {
      summary: "Login",
      description: "This operation logs in a user",
      operationId: "login-op",
      requestSchema: {
        body: 
          Types.Object({
            description: "User data to log in",
            properties: commonProperties,
        }),
      },
      tags: ["User Operations"],
      responses: {
        200: {
          description: "OK",
          content: {
            "application/json": {
              schema: {
                type: "object",
                description: "User",
                properties: {
                  id: {
                    type: "string",
                    description: "User ID",
                  },
                  firstName: {
                    type: "string",
                    description: "First Name",
                  },
                  lastName: {
                    type: "string",
                    description: "Last Name",
                  },
                  email: {
                    type: "string",
                    description: "Email",
                  },
                  phoneNmb: {
                    type: "string",
                    description: "Phone Number",
                  },
                  enableNotifs: {
                    type: "boolean",
                    description: "Enable Notifications",
                  },
                  createdAt: {
                    type: "string",
                    description: "Created At",
                  },
                  updatedAt: {
                    type: "string",
                    description: "Updated At",
                  },
                },
              },
            },
          },
        },
        400: textPlain("Bad Request"),
        401: textPlain("Unauthorized"),
        404: textPlain("User not found"),
      },
    },
  },
  true,);
}
