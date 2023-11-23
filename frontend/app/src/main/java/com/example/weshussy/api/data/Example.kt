package com.example.weshussy.api.data

import com.fasterxml.jackson.annotation.JsonProperty

data class Example(
    @JsonProperty("id") val id: Long,
    @JsonProperty("name") val name: String

)