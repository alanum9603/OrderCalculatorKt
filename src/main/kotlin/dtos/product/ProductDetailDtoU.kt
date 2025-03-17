package com.ipeasa.dtos.product

import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailDtoU(
    val id : String,
    val quantity : Int
)
