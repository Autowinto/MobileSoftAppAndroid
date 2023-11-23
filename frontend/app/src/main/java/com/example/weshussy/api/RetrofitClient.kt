package com.example.weshussy.api

import com.example.weshussy.api.interfaces.GroupApi
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class RetrofitClient {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("127.0.0.1:8001/api")
            .addConverterFactory(JacksonConverterFactory.create()).build();
    }
    val groupApi: GroupApi by lazy {
        retrofit.create(GroupApi::class.java)
    }
}


