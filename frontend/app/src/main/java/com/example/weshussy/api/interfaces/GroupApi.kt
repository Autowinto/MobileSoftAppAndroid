package com.example.weshussy.api.interfaces

import com.example.weshussy.api.data.Group
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface GroupApi {
    @POST("group")
    suspend fun createGroup(@Body group: Group): Call<Group>
}