package com.example.weshussy.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.weshussy.api.RetrofitClient
import com.example.weshussy.api.data.Group

class HomeViewModel: ViewModel() {
    suspend fun getGroupsForUser(userId: String): List<Group> {
        val response = RetrofitClient().userApi.getGroupsByUser(userId)
        println("response")
        println(response)
        return response.body()!!
    }
}