package com.ipeasa.ddds

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id : String? = null,
    val name : String,
    val price : Double,
    val currency: String
)
