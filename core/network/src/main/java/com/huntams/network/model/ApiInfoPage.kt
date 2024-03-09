package com.huntams.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiInfoPage(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String,
)
