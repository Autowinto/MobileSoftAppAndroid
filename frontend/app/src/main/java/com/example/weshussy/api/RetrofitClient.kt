package com.example.weshussy.api

import com.example.weshussy.api.interfaces.ExpenseApi
import com.example.weshussy.api.interfaces.GroupApi
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class RetrofitClient {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("http://10.0.2.2:8081/api/")
            .addConverterFactory(JacksonConverterFactory.create()).build();
    }
    val expenseApi: ExpenseApi by lazy {
        retrofit.create(ExpenseApi::class.java)
    }
    val groupApi: GroupApi by lazy {
        retrofit.create(GroupApi::class.java)
    }
}


