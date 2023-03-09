package com.coderipper.hsma.models

data class ReportEntity(
    val id: Int,
    val name: String,
    val description: String,
    val reportTypeId: Int,
    val guestId: Int,
    val hotelId: Int
)