package com.example.weshussy.api.interfaces

import com.example.weshussy.api.data.Group
import com.example.weshussy.api.data.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import java.lang.invoke.TypeDescriptor

interface GroupApi {

    // GROUPS MANAGEMENT
    data class GroupCreateRequestBody (
        val name: String,
        val userId: String,
        val description: String
    )

    data class GroupUpdateRequestBody (
        val id: String,
        val name: String,
        val userId: String,
        val description: String
    )

    data class GroupDeleteRequestBody (
        val id: String
    )

    @POST("groups/")
    suspend fun createGroup(@Body requestBody: GroupCreateRequestBody): Response<Object>

    @PUT("groups/")
    suspend fun updateGroup(@Body requestBody: GroupUpdateRequestBody): Response<String>

    @GET("groups/{id}")
    suspend fun getGroupById(@Path("id") id: String): Response<Group>

    @DELETE("groups/")
    suspend fun deleteGroup(@Body requestBody: GroupDeleteRequestBody): Response<String>


    // GROUP MEMBER MANAGEMENT
    data class GroupMemberCreateRequestBody (
        val name: String,
        val userId: String
    )

    data class GroupMemberDeleteRequestBody (
        val id: String
    )

    @POST("groups/members/")
    suspend fun addGroupMember(@Body requestBody: GroupMemberCreateRequestBody): Response<String>

    @DELETE("groups/members/")
    suspend fun removeMember(@Body requestBody: GroupMemberDeleteRequestBody): Response<String>

    @GET("groups/members/")
    suspend fun getMembers(@Query("groupId") groupId: String): Response<List<User>>
}