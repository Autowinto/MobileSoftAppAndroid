package com.example.weshussy.api.interfaces

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ExpenseApi {
    @POST("expense/")
    fun createExpense(): Response<Object>
}