package com.example.weshussy.api.interfaces

import com.example.weshussy.api.data.Group
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


data class GroupCreationRequest (
    val group_name: String,
    val user_id: String
)
interface GroupApi {
    @POST("group")
    suspend fun createGroup(@Body request: GroupCreationRequest): Response<Object>
}