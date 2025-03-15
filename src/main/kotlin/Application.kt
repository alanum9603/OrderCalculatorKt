package com.ipeasa

import com.ipeasa.controllers.materialRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    DatabaseConfig.init()
    KoinConfig.init()
    configureRouting()
}
