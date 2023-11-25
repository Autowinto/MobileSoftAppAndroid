import express from "express";
import { getAllUsers, getUser } from "./get-user";
import { createUser } from "./create-user";
import { updateUser } from "./update-user";
import { login } from "./login-user";
import { getGroupsByUser } from "./get-user-groups";

const router = express.Router();

router.get('/user', getUser);
router.get('/users', getAllUsers);
router.post('/', createUser);
router.put('/', updateUser);
router.post('/login', login);
router.get('user/:id/groups', getGroupsByUser)

export default router;