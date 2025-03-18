package com.ipeasa

import com.ipeasa.controllers.materialRoutes
import com.ipeasa.controllers.productRoutes
import com.ipeasa.exceptions.InvalidRouteException
import com.ipeasa.services.MaterialService
import com.ipeasa.services.ProductService
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val materialService by inject<MaterialService>()
    val productService by inject<ProductService>()

    routing {
        route("/api") {
            materialRoutes(materialService)
            productRoutes(productService)
        }

        route("{...}") {
            handle {
                throw InvalidRouteException()
            }
        }
    }
}
