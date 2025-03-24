package com.ipeasa.ddds

import kotlinx.serialization.Serializable

@Serializable
data class OrderProductMaterial(
    val id : Long,
    val materialId : Long,
    val name : String,
    val price : Double,
    val currency : String,
    val unit : String,
    val quantity : Double
)
