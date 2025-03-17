package com.ipeasa.dtos.product

import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailDtoC (
    val id : String,
    val quantity : Int
)