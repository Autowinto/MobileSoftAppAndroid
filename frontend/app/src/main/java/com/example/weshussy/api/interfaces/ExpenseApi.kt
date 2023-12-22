package com.example.weshussy.api.interfaces

import com.example.weshussy.api.data.Expense
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.Response

interface ExpenseApi {

    data class ExpenseCreateRequestBody(
        val userId: String,
        val groupId: String,
        val amount: String,
        val name: String
    )

    // Create a new expense
    @POST("expenses/")
    suspend fun createExpense(@Body expense: ExpenseCreateRequestBody): Response<Any>

    // Edit an existing expense
    @PUT("expenses/")
    suspend fun editExpense(@Body expense: Expense): Response<Expense>

    // Get details of an expense
    @GET("expenses/")
    suspend fun showExpense(): Response<Expense>

    // Delete an expense
    @DELETE("expenses/")
    suspend fun deleteExpense(): Response<Unit>

    // Get expense history
    @GET("expenses/history")
    suspend fun getExpenseHistory(): Response<List<Expense>>

    // Get all expenses
    @GET("expenses/expenses")
    suspend fun getAllExpenses(): Response<List<Expense>>
}