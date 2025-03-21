package com.ipeasa.dtos.product

import kotlinx.serialization.Serializable

@Serializable
data class ProductDtoC (
    val name : String,
    val price : Double,
    val currency : String,
    val materials : List<ProductDetailDtoC>
)