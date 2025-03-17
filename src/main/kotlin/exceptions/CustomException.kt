package com.ipeasa.exceptions

import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class ExceptionRequest(
    val error: String,
    val date: String,
    val message: String
) {
}

open class CustomException(
    private val error : String,
    private val date: Date,
    message : String
) : RuntimeException(message) {

    constructor(error: String, message: String) : this(error, Date(), message)

    fun toExceptionRequest() : ExceptionRequest {
        return ExceptionRequest(
            error,
            date.toString(),
            message!!
        )
    }
}

class InvalidUuidException(
    message: String = "el ID proporcionado es inválido"
) : CustomException(
    error = "ID inválido",
    message = message
)

class NotFoundException(
    message: String = "objeto"
) : CustomException(
    error = "No encontrado",
    message = "No se encontraron coincidencias con el $message proporcionado"
)

