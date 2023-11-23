import express from "express";
import { createExpense } from "./create-expense";
import {editExpense} from "./edit-expense";
import { showExpense } from "./show-expense";
import { deleteExpense } from "./delete-expense";
import { getExpenseHistory } from "./history-expense";

const router = express.Router();

//group operations
router.post('/', createExpense);
router.put('/', editExpense);
router.get('/', showExpense);
router.delete('/', deleteExpense);
router.get('/history', getExpenseHistory)


export default router;