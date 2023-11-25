import express from "express";
import { createGroup } from "./create-group";
import { addMember } from "./add-member";
import { deleteGroup } from "./delete-group";
import { removeMember } from "./remove-member";
import { getMembers } from "./get-members";

const router = express.Router();

//group operations
router.post('/', createGroup);
router.delete('/', deleteGroup);

//members operations
router.post('/members/', addMember);
router.delete('/members/', removeMember);
router.get('/members/', getMembers);

export default router;