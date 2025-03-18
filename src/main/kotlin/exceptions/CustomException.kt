package com.ipeasa.exceptions

import java.util.Date

open class CustomException(
    private val error : String,
    private val date: Date,
    message : String
) : RuntimeException(message) {

    constructor(error: String, message: String) : this(error, Date(), message)

    fun toExceptionRequest() : ExceptionResponse {
        return ExceptionResponse(
            error,
            date.toString(),
            message!!
        )
    }
}
