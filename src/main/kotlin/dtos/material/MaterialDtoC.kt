package com.ipeasa.dtos.material

import kotlinx.serialization.Serializable

@Serializable
data class MaterialDtoC(
    val name : String,
    val price : Double,
    val currency : String,
    val unit : String
)
