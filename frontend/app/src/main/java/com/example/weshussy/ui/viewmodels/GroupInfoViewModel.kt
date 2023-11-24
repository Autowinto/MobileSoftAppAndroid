package com.example.weshussy.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weshussy.api.RetrofitClient
import com.example.weshussy.api.interfaces.GroupApi.GroupCreateRequestBody

class GroupInfoViewModel(): ViewModel() {
    suspend fun createGroup(groupName: String, userId: String) {
        val request = GroupCreateRequestBody(name = groupName, userId = userId)
        RetrofitClient().groupApi.createGroup(request)
    }
}