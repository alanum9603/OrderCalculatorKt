package com.ipeasa.ddds

import kotlinx.serialization.Serializable

@Serializable
data class ProductDetail(
    val material: Material,
    val quantity: Int
)