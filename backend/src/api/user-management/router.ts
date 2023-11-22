import express from "express";
import { getAllUsers, getUser } from "./getUser";
import { createUser } from "./createUser";
import { updateUser } from "./updateUser";

const router = express.Router();

router.get('/user', getUser);
router.get('/users', getAllUsers);
router.post('/user', createUser);
router.put('/user', updateUser)

export default router;