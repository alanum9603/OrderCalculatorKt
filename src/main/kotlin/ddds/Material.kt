package com.ipeasa.ddds

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Material(
    val materialId : String,
    val name : String,
    val price : Double,
    val currency : String,
    val unit : String
)