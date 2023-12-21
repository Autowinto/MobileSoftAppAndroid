package com.example.weshussy.api.data

import com.fasterxml.jackson.annotation.JsonProperty

data class Expense(
    @JsonProperty("id")
    val id: String,

    @JsonProperty("name")
    val name: String,
    
    @JsonProperty("userId")
    val userId: String,

    @JsonProperty("groupId")
    val groupId: String,

    @JsonProperty("amount")
    val amount: Int
)