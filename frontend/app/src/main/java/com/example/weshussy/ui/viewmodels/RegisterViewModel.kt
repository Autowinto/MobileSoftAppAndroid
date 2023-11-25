package com.example.weshussy.ui.viewmodels

import com.example.weshussy.api.RetrofitClient
import com.example.weshussy.api.interfaces.UserApi

class RegisterViewModel {
    suspend fun createUser(
        firstName: String,
        lastName: String,
        email: String,
        phoneNumber: String,
        password: String,
        enableNotifications: Boolean
    ) {
        RetrofitClient().userApi.createUser(
            UserApi.CreateUserRequestBody(
                firstName,
                lastName,
                email,
                phoneNmb = phoneNumber,
                password,
                enableNotifs = enableNotifications
            )
        )
    }
}