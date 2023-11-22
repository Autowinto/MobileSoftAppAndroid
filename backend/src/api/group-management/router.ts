import express from "express";
import { createGroup } from "./createGroup";

const router = express.Router();

router.post('/', createGroup);

export default router;