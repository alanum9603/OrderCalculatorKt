package com.ipeasa

import com.ipeasa.exceptions.InvalidArgumentException
import com.ipeasa.exceptions.InvalidRouteException
import com.ipeasa.exceptions.InvalidUuidException
import com.ipeasa.exceptions.ObjectNotFoundException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureExceptions() {
    install(StatusPages) {
        exception<InvalidUuidException> { call, cause ->
            call.respond(HttpStatusCode.BadRequest, cause.toExceptionRequest())
        }

        exception<InvalidArgumentException> { call, cause ->
            call.respond(HttpStatusCode.BadRequest, cause.toExceptionRequest())
        }

        exception<ObjectNotFoundException> { call, cause ->
            call.respond(HttpStatusCode.BadRequest, cause.toExceptionRequest())
        }

        exception<InvalidRouteException> { call, cause ->
            call.respond(HttpStatusCode.NotFound,   cause.toExceptionRequest())
        }
    }
}