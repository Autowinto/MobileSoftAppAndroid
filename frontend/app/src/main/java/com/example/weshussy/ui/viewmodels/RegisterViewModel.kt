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
        println("Creating user!")
        val response = RetrofitClient().userApi.createUser(
            UserApi.CreateUserRequestBody(
                firstName,
                lastName,
                email,
                phoneNmb = phoneNumber,
                password,
                enableNotifs = enableNotifications
            )
        )
        if (response.isSuccessful) println("We made a user!")

    }
}