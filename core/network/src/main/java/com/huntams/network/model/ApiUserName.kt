package com.huntams.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiUserName(
    val title: String,
    val first: String,
    val last: String,
)
