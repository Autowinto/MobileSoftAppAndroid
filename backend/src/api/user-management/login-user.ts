import {Application, Request, Response } from "express";
import { getUser } from "./get-user";
import { User } from "../../groups/setup-groups";

export async function login(req: Request, res: Response) {
    const { email, password } = req.body as { email: string, password: string };
    const user = User.findOne({ where: { email, password } }).then((user) => {
        res.send(user);
    });
}