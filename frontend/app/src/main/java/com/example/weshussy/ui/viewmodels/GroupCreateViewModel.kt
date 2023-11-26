package com.example.weshussy.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.weshussy.api.RetrofitClient
import com.example.weshussy.api.interfaces.GroupApi.GroupCreateRequestBody
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GroupCreateUiState {
    var name: String by mutableStateOf("")
    var description: String by mutableStateOf("")
}

class GroupCreateViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(GroupCreateUiState())
    val uiState: StateFlow<GroupCreateUiState> = _uiState.asStateFlow()
    suspend fun createGroup(name: String, userId: String) {
        RetrofitClient().groupApi.createGroup(GroupCreateRequestBody(name, userId))
    }
}