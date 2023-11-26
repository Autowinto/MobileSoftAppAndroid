package com.example.weshussy.ui.viewmodels

import com.example.weshussy.api.RetrofitClient
import com.example.weshussy.api.interfaces.UserApi

class ProfileInfoViewModel {
    suspend fun updateUser(
        id: String,
        firstName: String,
        lastName: String,
        email: String,
        phoneNumber: String,
        enableNotifications: Boolean
    ) {
        RetrofitClient().userApi.updateUser(
            UserApi.UpdateUserRequestBody(
            id,
            firstName,
            lastName,
            email,
            phoneNumber,
            enableNotifications
            )
        )
    }
}