package com.coderipper.hsma.models

import java.sql.Date

data class AddressEntity(
    val id: Int,
    val street: String,
    val number: Int,
    val city: String,
    val state: Date,
    val colony: String,
    val country: String,
    val latitude: String,
    val longitude: String
)