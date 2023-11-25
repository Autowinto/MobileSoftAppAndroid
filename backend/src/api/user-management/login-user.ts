import { Application, Request, Response } from "express";
import { getUser } from "./get-user";
import { User } from "../../groups/setup-groups";
import bcrypt from "bcrypt";

export async function login(req: Request, res: Response) {
  try {
    const { email, password } = req.body as { email: string; password: string };
    console.log(password);

    const user = await User.findOne({ where: { email } });
    if (user) {
      const userPassword = user.getDataValue("password");
      const isPasswordValid = await bcrypt.compare(password, userPassword);
      if (isPasswordValid) {
        res.send(user);
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
