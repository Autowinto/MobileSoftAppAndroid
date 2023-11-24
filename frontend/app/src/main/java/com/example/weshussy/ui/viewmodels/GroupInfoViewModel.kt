package com.example.weshussy.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weshussy.api.RetrofitClient
import com.example.weshussy.api.interfaces.GroupCreationRequest

class GroupInfoViewModel(): ViewModel() {
    suspend fun createGroup(groupName: String, userId: String) {
        val request = GroupCreationRequest(group_name = groupName,  user_id = userId)
        RetrofitClient().groupApi.createGroup(request)
    }
}