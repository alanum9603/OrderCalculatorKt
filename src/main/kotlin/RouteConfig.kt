package com.ipeasa

import com.ipeasa.controllers.materialRoutes
import com.ipeasa.services.MaterialService
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val materialService by inject<MaterialService>()

    routing {
        route("/api") {
            materialRoutes(materialService)
        }
    }
}
