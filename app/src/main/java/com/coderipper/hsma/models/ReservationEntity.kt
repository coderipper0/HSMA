package com.coderipper.hsma.models

import java.sql.Date

data class ReservationEntity(
    val id: Int,
    val startDate: Date,
    val endDate: Date,
    val isActive: Boolean,
    val total: String,
    val guestId: Int,
    val packageId: Int
)