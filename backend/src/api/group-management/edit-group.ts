import { Application, Request, Response } from 'express';
import { OpenApi, Types, textPlain, Schema } from 'ts-openapi';
import { Group, UserGroup } from '../../groups/setup-groups';
import { v4 as uuidv4 } from 'uuid';

export async function editGroup(req: Request, res: Response) {

    try {
        const { id, name, ownerId, description, totalExpense} = req.body;

        if (name == null || description == null) {
            throw "Error creating group, no group name or user id was provided";
        }

        const group = await Group.findByPk(id)

        group.update({name, description, totalExpense}).catch((e) => {
          console.error(e)
        })

        res.status(201).send({message: "Group updated succesfully"})
    } catch (error) {
        console.log(error)
        res.status(400).send(error)
    }

}


