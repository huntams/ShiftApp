package com.huntams.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiCoordinate(
    val latitude: String,
    val longitude: String,
)