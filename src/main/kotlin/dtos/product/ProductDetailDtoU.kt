package com.ipeasa.dtos.product

import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailDtoU(
    val id : Long? = null,
    val materialId : String,
    val quantity : Int
)
