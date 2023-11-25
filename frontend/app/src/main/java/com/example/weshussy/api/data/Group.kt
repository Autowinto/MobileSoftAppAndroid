package com.example.weshussy.api.data

import com.fasterxml.jackson.annotation.JsonProperty

data class Group(
    @JsonProperty("id") val id: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("totalExpense") val totalExpenses: Int,
    @JsonProperty("ownerId") val ownerId: Int,
    @JsonProperty("createdAt") val createdAt: String,
    @JsonProperty("updatedAt") val updatedAt: String
)
