package com.coderipper.hsma.session

import java.sql.Date

data class GuestEntity(
    val id: Int,
    val name: String,
    val patronymic: String,
    val matronymic: String,
    val birth: Date,
    val phone: String,
    val emergencyPhone: String,
    val addressId: Int
)