package com.ipeasa.ddds

import kotlinx.serialization.Serializable

@Serializable
data class OrderProduct (
    val product: ProductAndDetail,
    val quantity : Double
)