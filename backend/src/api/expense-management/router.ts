import express from "express";
import { createExpense } from "./create-expense";
import {editExpense} from "./edit-expense";
import { showExpense } from "./show-expense";
import { deleteExpense } from "./delete-expense";
import { getExpenseHistory } from "./history-expense";
import { getAllExpenses } from "./get-expenses";

const router = express.Router();

//group operations
router.post('/', createExpense);
router.put('/', editExpense);
router.get('/', showExpense);
router.delete('/', deleteExpense);
router.get('/history', getExpenseHistory);
router.get('/expenses', getAllExpenses);


export default router;