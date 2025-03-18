package com.ipeasa.exceptions


class InvalidUuidException(
    message: String = "el ID proporcionado es inválido"
) : CustomException(
    error = "ID inválido",
    message = message
)