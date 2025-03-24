package com.ipeasa.ddds

import kotlinx.serialization.Serializable

@Serializable
data class Order(
    val id : String? = null,
    val ruc : String,
    val date : String,
    val total : Double,
    val currency : String,
    val exchange : Double,
    val address : String,
)
