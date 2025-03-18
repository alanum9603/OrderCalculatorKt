package com.ipeasa.utils

import com.github.f4b6a3.uuid.UuidCreator
import com.ipeasa.exceptions.InvalidUuidException
import java.util.UUID

class UuidService {

    fun generateUuidV6() : UUID {
        return UuidCreator.getTimeOrdered()
    }

    fun toValidUuid(uuidString : String) : UUID {
        try {
            return UUID.fromString(uuidString)
        } catch (ex : IllegalArgumentException) {
            throw InvalidUuidException(message = "El ID proporcionado no tiene un formato UUID válido: $uuidString")
        }
    }
}