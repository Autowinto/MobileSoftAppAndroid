import express from "express";
import { getAllUsers, getUser } from "./getUser";
import { createUser } from "./createUser";

const router = express.Router();

router.get('/get_user', getUser);
router.get('/get_all_users', getAllUsers);
router.post('/create_user', createUser);

export default router;