package com.huntams.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiDate(
    val date: String,
    val age: Int,
)
