package com.example.weshussy.api.interfaces

import com.example.weshussy.api.data.Group
import com.example.weshussy.api.data.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface GroupApi {
    data class GroupCreateRequestBody (
        val name: String,
        val userId: String
    )
    @POST("groups/")
    suspend fun createGroup(@Body requestBody: GroupCreateRequestBody): Response<String>

    data class GroupDeleteRequestBody (
        val id: String
    )
    @DELETE("groups/")
    suspend fun deleteGroup(@Body requestBody: GroupDeleteRequestBody): Response<String>

    data class GroupMemberCreateRequestBody (
        val name: String,
        val userId: String
    )
    @POST("groups/members/")
    suspend fun addGroupMember(@Body requestBody: GroupMemberCreateRequestBody): Response<String>

    data class GroupMemberDeleteRequestBody (
        val id: String
    )
    @DELETE("groups/members/")
    suspend fun removeMember(@Body requestBody: GroupMemberDeleteRequestBody): Response<String>

    @GET("groups/members/")
    suspend fun getMembers(@Query("groupId") groupId: String): Response<List<User>>
}