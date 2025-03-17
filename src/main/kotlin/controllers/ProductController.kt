package com.ipeasa.controllers

import com.ipeasa.services.ProductService
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.productRoutes(productService: ProductService) {
    route("/product") {
        get {
            val pageSize : Int? = call.request.queryParameters["pageSize"]?.trim()?.toIntOrNull()
            val page : Long?    = call.request.queryParameters["page"]?.trim()?.toLongOrNull()
            if (pageSize !== null && page !== null) {
                call.respond(productService.readAllProducts(pageSize, page))
            }
        }
    }
}