package com.example.weshussy.api

import com.example.weshussy.api.interfaces.ExampleApi

class ExampleUsage {
    fun example() {
        val retrofit = RetrofitClient.getClient()
        val exampleApi = retrofit.create(ExampleApi::class.java)

        val response = exampleApi.getExamples().execute();
    }
}