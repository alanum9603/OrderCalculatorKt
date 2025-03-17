package com.ipeasa.ddds

import kotlinx.serialization.Serializable

@Serializable
data class ProductDetail(
    val material: String,
    val quantity: Int
)