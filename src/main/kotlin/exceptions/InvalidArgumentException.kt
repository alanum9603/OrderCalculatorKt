package com.ipeasa.exceptions

class InvalidArgumentException(
    message: String = "argumento"
) : CustomException(
    error = "Argumento inválido",
    message = "el $message proporcionado es inválido"
)
