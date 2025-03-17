package com.ipeasa

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    // io.ktor.server.netty.EngineMain.main(args)
    embeddedServer(Netty, port = 8080) {
        module()
    }.start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureExceptions()
    configureDatabase()
    configureKoin()
    configureRouting()
}
