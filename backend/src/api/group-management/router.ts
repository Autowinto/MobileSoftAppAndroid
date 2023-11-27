import express from "express";
import { createGroup } from "./create-group";
import { addMember } from "./add-member";
import { deleteGroup } from "./delete-group";
import { removeMember } from "./remove-member";
import { getMembers } from "./get-members";
import { getGroup } from "./get-group";
import { editGroup } from "./edit-group";

const router = express.Router();

//group operations
router.post('/', createGroup);
router.put('/', editGroup)
router.delete('/', deleteGroup);
router.get('/:id', getGroup);

//members operations
router.post('/members', addMember);
router.delete('/members', removeMember);
router.get('/:id/members', getMembers);

export default router;