package com.huntams.model


data class Location(
    val street: Street,
    val coordinates: Coordinate,
    val city: String,
    val state: String,
    val country: String,
){
    fun streetStringBuilder():String{
        return "$country,$state,$city,${street.name},${street.number} (${coordinates.latitude},${coordinates.longitude})"
    }
}
