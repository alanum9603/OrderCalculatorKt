package com.ipeasa.exceptions

class InvalidRouteException(
) : CustomException(
    error = "Ruta inválida",
    message = "La ruta proporcionada es inválida"
)

