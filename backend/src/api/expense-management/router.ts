import express from "express";
import { createExpense } from "./create-expense";

const router = express.Router();

//group operations
router.post('/', createExpense);


export default router;