package com.huntams.network.model

import kotlinx.serialization.Serializable

@Serializable
data class PageDataResponse<T>(
    val results: List<T>,
    val info: ApiInfoPage,
)