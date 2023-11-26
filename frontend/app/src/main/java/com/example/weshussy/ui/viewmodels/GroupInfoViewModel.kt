package com.example.weshussy.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weshussy.api.RetrofitClient
import com.example.weshussy.api.interfaces.GroupApi.GroupCreateRequestBody

class GroupInfoViewModel(): ViewModel() {
    private var groupId = ""

    fun setGroupId(groupId: String) {
        this.groupId = groupId
    }
    fun getGroupId(): String {
        return this.groupId
    }
}