package com.coderipper.hsma.models

data class RoomEntity(
    val id: Int,
    val number: Int,
    val price: String,
    val capacity: Int,
    val hotelId: Int,
    val typeId: Int,
    val reservationId: Int
)