package com.huntams.model




data class User(
    val name: UserName,
    val location: Location,
    val email: String,
    val dob: Date,
    val phone: String,
    val cell: String,
    val userId: UserId,
    val picture: Picture,
    val infoPage: InfoPage
)