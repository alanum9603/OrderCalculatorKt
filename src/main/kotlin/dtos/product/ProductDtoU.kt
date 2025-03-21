package com.ipeasa.dtos.product

import kotlinx.serialization.Serializable

@Serializable
data class ProductDtoU (
    val id : String,
    val name : String,
    val price : Double,
    val currency: String,
    val materials : List<ProductDetailDtoU>
)
