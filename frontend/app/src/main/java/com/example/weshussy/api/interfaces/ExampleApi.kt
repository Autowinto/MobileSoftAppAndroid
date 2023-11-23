package com.example.weshussy.api.interfaces

import com.example.weshussy.api.data.Example
import retrofit2.Call
import retrofit2.http.GET


interface ExampleApi {
    @GET("examples/{id}")
    fun getExampleById(): Call<Example>

    @GET("examples")
    fun getExamples(): Call<List<Example>>
}