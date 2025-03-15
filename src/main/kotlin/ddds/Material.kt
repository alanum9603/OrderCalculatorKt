package com.ipeasa.ddds

import java.util.UUID

data class Material(
    val materialId : UUID,
    val name : String,
    val price : Double,
    val currency : String,
    val unit : String
)