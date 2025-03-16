package com.ipeasa

import com.ipeasa.controllers.materialRoutes
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

fun main() {
    // io.ktor.server.netty.EngineMain.main(args)
    embeddedServer(Netty, port = 8080) {
        module()
    }.start(wait = true)
}

fun Application.module() {
    configureContentNegotiation()
    configureExceptions()
    configureDatabase()
    configureKoin()
    configureRouting()
}
