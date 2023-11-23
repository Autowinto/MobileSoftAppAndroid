package com.example.weshussy.api

import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object RetrofitClient {
    const val BASE_URL = "127.0.0.1:8001/api"

    fun getClient(): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(JacksonConverterFactory.create()).build();
}


