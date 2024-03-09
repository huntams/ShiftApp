package com.huntams.model


data class UserName(
    val title: String,
    val first: String,
    val last: String,
){
    fun nameBuilder(): String{
        return "$title $first $last"
    }
}
