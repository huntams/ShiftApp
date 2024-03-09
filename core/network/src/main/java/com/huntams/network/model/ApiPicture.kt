package com.huntams.network.model

import android.media.ThumbnailUtils
import kotlinx.serialization.Serializable

@Serializable
data class ApiPicture(
    val large: String,
    val medium: String,
    val thumbnail: String,
)