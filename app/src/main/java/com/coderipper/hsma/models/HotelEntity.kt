package com.coderipper.hsma.models

data class HotelEntity(
    val id: Int,
    val name: String,
    val phone: String,
    val clientId: Int,
    val addressId: Int
)