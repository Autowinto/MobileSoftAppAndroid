package com.example.weshussy.ui

import com.example.weshussy.api.data.User

object UserSession {
    private var user: User? = null

    fun setUser(user: User) {
        this.user = user
    }

    fun getUser(): User? {
        return this.user
    }
}