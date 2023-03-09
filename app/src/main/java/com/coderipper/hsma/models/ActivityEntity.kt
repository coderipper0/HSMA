package com.coderipper.hsma.models

import java.sql.Date

data class ActivityEntity(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val startDateTime: Date,
    val duration: Int,
    val hotelId: Int
)