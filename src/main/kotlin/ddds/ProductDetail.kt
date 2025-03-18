package com.ipeasa.ddds

import kotlinx.serialization.Serializable

@Serializable
data class ProductDetail(
    val id: Long? = null,
    val material: Material,
    val quantity: Double
)