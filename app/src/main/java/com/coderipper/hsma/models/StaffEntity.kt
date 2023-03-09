package com.coderipper.hsma.models

import java.sql.Date

data class StaffEntity(
    val id: Int,
    val name: String,
    val patronymic: String,
    val matronymic: String,
    val birth: Date,
    val phone: String,
    val emergency_phone: String,
    val roleId: Int,
    val addressId: Int,
    val hotelId: Int
)