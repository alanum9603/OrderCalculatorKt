package com.ipeasa.exceptions

class AlreadyExistsException(
    message: String = "objeto"
) : CustomException(
    error = "el objeto ya existe en la base de datos",
    message = "el $message ya existe en la base de datos"
)