package com.huntams.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiUser(
    val name: ApiUserName,
    val location: ApiLocation,
    val email: String,
    val dob: ApiDate,
    val phone: String,
    val cell: String,
    val id: ApiId,
    val picture: ApiPicture
)
