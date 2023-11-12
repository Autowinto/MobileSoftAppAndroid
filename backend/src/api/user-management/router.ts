import express from "express";
import { getUser } from "./getUser";
import { createUser } from "./createUser";

const router = express.Router();

router.get('/get_user', getUser);
router.post('/create_user', createUser);

export default router;