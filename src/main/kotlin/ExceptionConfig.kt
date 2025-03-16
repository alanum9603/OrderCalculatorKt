package com.ipeasa

import com.ipeasa.exceptions.InvalidUuidException
import com.ipeasa.exceptions.NotFoundException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureExceptions() {
    install(StatusPages) {
        exception<InvalidUuidException> { call, cause ->
            call.respond(HttpStatusCode.BadRequest, cause.toExceptionRequest())
        }

        exception<NotFoundException> { call, cause ->
            call.respond(HttpStatusCode.BadRequest, cause.toExceptionRequest())
        }
    }
}