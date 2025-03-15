package com.ipeasa.controllers

import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.materialRoutes() {

    val materialService

    route("/material") {
        get {
            val materialList = materialService.readAllMaterials()
            call.respondText { "Hello world" }
        }
    }

}