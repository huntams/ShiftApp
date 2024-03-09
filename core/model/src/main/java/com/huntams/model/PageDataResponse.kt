package com.huntams.model

data class PageDataResponse<T>(
    val results: List<T>,
    val info: InfoPage,
)