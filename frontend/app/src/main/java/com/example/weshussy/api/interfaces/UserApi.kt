package com.example.weshussy.api.interfaces

import com.example.weshussy.api.data.Group
import com.example.weshussy.api.data.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {
    @GET("users")
    suspend fun getUser(@Query("userId") userId: String): Response<User>

    @GET("users/")
    suspend fun getAllUsers(): Response<List<User>>

    data class CreateUserRequestBody (
        val firstName: String,
        val lastName: String,
        val email: String,
        val phoneNmb: String,
        val password: String,
        val enableNotifs: Boolean
    )
    data class UpdateUserRequestBody(
        val id: String,
        val firstName: String,
        val lastName: String,
        val email: String,
        val phoneNmb: String,
        val enableNotifs: Boolean
    )
    @POST("users/")
    suspend fun createUser(@Body requestBody: CreateUserRequestBody): Response<String>

    @PUT("users/")
    suspend fun updateUser(@Body requestBody: UpdateUserRequestBody): Response<String>

    data class UserLoginRequestBody (
        val email: String,
        val password: String
    )
    @POST("users/login")
    suspend fun loginUser(@Body requestBody: UserLoginRequestBody): Response<User>

    @GET("users/{id}/groups")
    suspend fun getGroupsByUser(@Path("id") userId: String): Response<List<Group>>
}