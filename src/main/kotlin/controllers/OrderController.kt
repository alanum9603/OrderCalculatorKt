package com.ipeasa.controllers

import com.ipeasa.services.OrderService
import io.ktor.server.application.*
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.orderRoutes(
    orderService: OrderService
) {
    route("/order/search-by-ruc") {
        get {  }
    }
}