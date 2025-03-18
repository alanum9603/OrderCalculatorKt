package com.ipeasa.controllers

import com.ipeasa.ddds.Product
import com.ipeasa.ddds.ProductAndDetail
import com.ipeasa.dtos.product.ProductDtoC
import com.ipeasa.exceptions.InvalidUuidException
import com.ipeasa.exceptions.ObjectNotFoundException
import com.ipeasa.services.ProductService
import io.ktor.http.*
import io.ktor.server.request.*
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

        get("/search-by-name") {
            val name : String?  = call.request.queryParameters["name"]      ?.trim()
            val pageSize : Int? = call.request.queryParameters["pageSize"]  ?.trim()?.toIntOrNull()
            val page : Long?    = call.request.queryParameters["page"]      ?.trim()?.toLongOrNull()

            if (!name.isNullOrEmpty() && pageSize !== null && page !== null) {
                val productList : List<Product> = productService.readProductsByName(name, pageSize, page)

                if (productList.isNotEmpty()) {
                    call.respond(productList)
                } else {
                    throw ObjectNotFoundException(message = "nombre")
                }
            } else {
                call.respond(HttpStatusCode.BadRequest, "Debe ingresar un nombre válido")
            }
        }

        get("/search-by-id") {
            val id : String? = call.request.queryParameters["id"]?.trim()

            if (!id.isNullOrEmpty()) {
                val product : ProductAndDetail? = productService.readProductById(id)

                if (product !== null) {
                    call.respond(product)
                } else {
                    throw InvalidUuidException(message = "Producto con id $id no encontrado")
                }
            } else {
                throw InvalidUuidException(message = "El id esta vacío")
            }
        }

        post {
            val productAndDetail = productService.createProduct(call.receive<ProductDtoC>())

            println("hola")
            print(productAndDetail.toString())

            if (productAndDetail !== null) {
                call.respond(HttpStatusCode.OK, productAndDetail)
            }
        }

        put {

        }

        delete {
            val id = call.request.queryParameters["id"]?.trim()

            if (!id.isNullOrEmpty()) {
                val product = productService.deleteProduct(id)
                if (product !== null) {
                    call.respond(HttpStatusCode.OK, product)
                } else {
                    throw ObjectNotFoundException("product con id $id")
                }
            } else {
                throw InvalidUuidException(message = "El id esta vacío")
            }
        }
    }
}