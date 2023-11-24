package com.example.weshussy.api.interfaces

import com.example.weshussy.api.data.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface UserApi {
    @GET("user/")
    fun getUser(@Query("userId") userId: String): Response<User>

    @GET("users/")
    fun getAllUsers(): Response<List<User>>

    @POST("user/")
    fun createUser(@Body user: User): Response<Object>

    @PUT("user/")
    fun updateUser(@Body user: User): Response<Object>

    @POST("user/login")
    fun loginUser(@Body user: User): Response<Object>
}