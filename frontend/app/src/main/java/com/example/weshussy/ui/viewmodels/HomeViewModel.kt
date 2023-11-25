package com.example.weshussy.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.weshussy.api.RetrofitClient
import com.example.weshussy.api.data.Group

class HomeViewModel: ViewModel() {
    suspend fun getGroupsForUser(): List<Group> {
        val response = RetrofitClient().userApi.getGroupsByUser("321321")
        println("response")
        println(response)
        return response.body()!!
    }
}