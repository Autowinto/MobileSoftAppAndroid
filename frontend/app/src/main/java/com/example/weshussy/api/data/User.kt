package com.example.weshussy.api.data

import com.fasterxml.jackson.annotation.JsonProperty

data class User(
    @JsonProperty("id") val id: String,
    @JsonProperty("first_name") val firstName: String,
    @JsonProperty("last_name") val lastName: String,
    @JsonProperty("email") val email: String,
    @JsonProperty("phoneNmb") val phoneNmb: String,
    @JsonProperty("password") val password: String,
    @JsonProperty("enableNotifs") val enableNotifs: Boolean,
    @JsonProperty("createdAt") val createdAt: String,
    @JsonProperty("updatedAt") val updatedAt: String,
)