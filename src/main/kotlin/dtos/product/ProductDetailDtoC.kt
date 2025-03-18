package com.ipeasa.dtos.product

import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailDtoC (
    val materialId : String,
    val quantity : Double
)