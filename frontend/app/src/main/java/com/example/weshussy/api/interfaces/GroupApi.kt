package com.example.weshussy.api.interfaces

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
    @POST("group/")
    fun createGroup(@Body group: GroupCreateRequestBody): Response<Object>

    data class GroupDeleteRequestBody (
        val id: String
    )
    @DELETE("group/")
    fun deleteGroup(@Body requestBody: GroupDeleteRequestBody): Response<Object>

    data class GroupMemberCreateRequestBody (
        val name: String,
        val userId: String
    )
    @POST("group/members/")
    fun addGroupMember(@Body requestBody: GroupMemberCreateRequestBody): Response<Object>

    data class GroupMemberDeleteRequestBody (
        val id: String
    )
    @DELETE("group/members/")
    fun removeMember(@Body requestBody: GroupMemberDeleteRequestBody): Response<Object>

    @GET("group/members/")
    fun getMembers(@Query("groupId") groupId: String): Response<List<User>>
}