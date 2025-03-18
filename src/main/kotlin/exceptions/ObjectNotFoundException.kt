package com.ipeasa.exceptions

class ObjectNotFoundException(
    message: String = "objeto"
) : CustomException(
    error = "No encontrado",
    message = "No se encontraron coincidencias con el $message proporcionado"
)
