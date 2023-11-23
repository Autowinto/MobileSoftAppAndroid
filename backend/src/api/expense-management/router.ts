import express from "express";
import { createExpense } from "./create-expense";
import {editExpense} from "./edit-expense";

const router = express.Router();

//group operations
router.post('/', createExpense);
router.put('/', editExpense);


export default router;