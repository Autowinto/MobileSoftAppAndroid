import express from "express";
import { createGroup } from "./create-group";

const router = express.Router();

router.post('/', createGroup);

export default router;