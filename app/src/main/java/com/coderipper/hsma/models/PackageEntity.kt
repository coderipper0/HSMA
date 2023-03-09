package com.coderipper.hsma.models

import java.sql.Date

data class PackageEntity(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val qtyRooms: Int,
    val typeId: Int,
    val hotelId: Int
)