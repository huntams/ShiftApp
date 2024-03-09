package com.huntams.network.model

import kotlinx.serialization.Serializable


@Serializable
data class ApiStreet(
    val number: Int,
    val name: String,
)