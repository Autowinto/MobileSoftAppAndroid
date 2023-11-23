import express from "express";
import { createGroup } from "./create-group";
import { addMember } from "./add-member";

const router = express.Router();

router.post('/', createGroup);
router.post('/add_member', addMember);

export default router;