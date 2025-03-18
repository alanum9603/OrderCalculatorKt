package com.ipeasa.exceptions

import kotlinx.serialization.Serializable

@Serializable
data class ExceptionResponse(
    val error: String,
    val date: String,
    val message: String
)