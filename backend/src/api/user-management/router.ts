import express from "express";
import { getAllUsers, getUser } from "./get-user";
import { createUser } from "./create-user";
import { updateUser } from "./update-user";
import { login } from "./login-user";

const router = express.Router();

router.get('/user', getUser);
router.get('/users', getAllUsers);
router.post('/', createUser);
router.put('/', updateUser);
router.post('/login', login);

export default router;