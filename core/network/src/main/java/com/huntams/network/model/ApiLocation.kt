package com.huntams.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiLocation(
    val street: ApiStreet,
    val coordinates: ApiCoordinate,
    val city: String,
    val state: String,
    val country: String,
)
