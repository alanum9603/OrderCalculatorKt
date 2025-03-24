package com.ipeasa.ddds

import kotlinx.serialization.Serializable

@Serializable
data class OrderProduct (
    val id : Long,
    val productId : Long,
    val name : String,
    val price : Double,
    val currency : String,
    val quantity : Double,
    val materials : List<OrderProductMaterial>
)